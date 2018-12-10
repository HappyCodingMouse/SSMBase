package com.springmvc.utils;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSyntaxException;
import java.lang.reflect.Type;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class GsonDateDeserializer implements JsonDeserializer<Date> {
    private final DateFormat enUsFormat;
    private final DateFormat localFormat;
    private final DateFormat iso8601Format;
    private final DateFormat format1;
    private final DateFormat format2;

    public GsonDateDeserializer() {
        this.enUsFormat = DateFormat.getDateTimeInstance(2, 2, Locale.US);
        this.localFormat = DateFormat.getDateTimeInstance(2, 2);
        this.iso8601Format = buildIso8601Format();
        this.format1 = buildFormat1();
        this.format2 = buildFormat2();
    }

    private static DateFormat buildIso8601Format() {
        SimpleDateFormat iso8601Format = new SimpleDateFormat("yyyy-MM-dd\'T\'HH:mm:ss\'Z\'", Locale.US);
        iso8601Format.setTimeZone(TimeZone.getTimeZone("UTC"));
        return iso8601Format;
    }

    private static DateFormat buildFormat1() {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    }

    private static DateFormat buildFormat2() {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
    }

    public Date deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        if(!(json instanceof JsonPrimitive)) {
            throw new JsonParseException("The date should be a string value");
        } else {
            Date date = this.deserializeToDate(json.getAsString());
            if(typeOfT == Date.class) {
                return date;
            } else if(typeOfT == Timestamp.class) {
                return new Timestamp(date.getTime());
            } else if(typeOfT == java.sql.Date.class) {
                return new java.sql.Date(date.getTime());
            } else {
                throw new IllegalArgumentException(this.getClass() + " cannot deserialize to " + typeOfT);
            }
        }
    }

    private Date deserializeToDate(String json) {
        try {
            return this.localFormat.parse(json);
        } catch (ParseException var7) {
            try {
                return this.enUsFormat.parse(json);
            } catch (ParseException var6) {
                try {
                    return this.format1.parse(json);
                } catch (ParseException var5) {
                    try {
                        return this.format2.parse(json);
                    } catch (ParseException var4) {
                        try {
                            return this.iso8601Format.parse(json);
                        } catch (ParseException var3) {
                            throw new JsonSyntaxException(json, var3);
                        }
                    }
                }
            }
        }
    }
}

