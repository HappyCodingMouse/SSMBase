//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.springmvc.utils;

//import utils.GsonDateDeserializer;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

public class JsonUtil {
    public static final String DATE_FORMAT_YYYYMMDDHHMMSS = "yyyy-MM-dd HH:mm:ss";
    private static final String TIMEZONE_BEIJING = "GMT+08:00";
    private static final String TIMESTAMP_FORMAT_YYYY_MM_DD_HH_MM_SS_SSS = "yyyy-MM-dd HH:mm:ss.SSS";
    private static final ThreadLocal<SimpleDateFormat> TIMESTAMP_FORMAT = new ThreadLocal() {
        protected SimpleDateFormat initialValue() {
            return JsonUtil.getTimestampFormat();
        }
    };

    public JsonUtil() {
    }

    public static JsonObject stringToJsonObject(String string) {
        return (new JsonParser()).parse(string).getAsJsonObject();
    }

    public static <T> T stringToClassOfT(String string, Class<T> classOfT) {
        return createGson().fromJson(string, classOfT);
    }

    public static <T> T stringToClassOfTDefaultDateFormat(String string, Class<T> classOfT) {
        return createGsonDefaultDateFormat().fromJson(string, classOfT);
    }

    public static <T> T stringToClassOfTSetDateFormat(String string, Class<T> classOfT, String dateFormat) {
        return createGsonSetDateFormat(dateFormat).fromJson(string, classOfT);
    }

    public static <T> T stringToTypeOfT(String string, Type typeOfT) {
        return createGson().fromJson(string, typeOfT);
    }

    public static <T> T stringToTypeOfTDefaultDateFormat(String string, Type typeOfT) {
        return createGsonDefaultDateFormat().fromJson(string, typeOfT);
    }

    public static <T> T stringToTypeOfTSetDateFormat(String string, Type typeOfT, String dateFormat) {
        return createGsonSetDateFormat(dateFormat).fromJson(string, typeOfT);
    }

    public static <T> List<T> stringToList(String string) {
        return (List)stringToTypeOfT(string, (new TypeToken() {
        }).getType());
    }

    public static <K, V> Map<K, V> stringToMap(String string) {
        return (Map)stringToTypeOfT(string, (new TypeToken() {
        }).getType());
    }

    public static <T> T jsonElementToTypeOfT(JsonElement json, Type typeOfT) {
        return createGson().fromJson(json, typeOfT);
    }

    public static <T> List<T> jsonElementToList(JsonElement json) {
        return (List)jsonElementToTypeOfT(json, (new TypeToken() {
        }).getType());
    }

    public static <K, V> Map<K, V> jsonElementToMap(JsonElement json) {
        return (Map)jsonElementToTypeOfT(json, (new TypeToken() {
        }).getType());
    }

    public static JsonElement objectToJsonElement(Object obj) {
        return createGson().toJsonTree(obj);
    }

    public static JsonElement objectToJsonElement(Object obj, Type typeOfT) {
        return createGson().toJsonTree(obj, typeOfT);
    }

    public static String objectToJsonString(Object obj) {
        return createGsonSetDateFormat("yyyy-MM-dd HH:mm:ss").toJson(obj);
    }

    public static String objectToJsonStringSetDateFormat(Object obj, String dateFormat) {
        return objectToJsonString(obj, (new GsonBuilder()).setDateFormat(dateFormat));
    }

    public static String objectToJsonStringDisableHtmlEscaping(Object obj) {
        return objectToJsonString(obj, (new GsonBuilder()).disableHtmlEscaping());
    }

    public static String objectToJsonString(Object obj, GsonBuilder gsonBuilder) {
        return gsonBuilder.create().toJson(obj);
    }

    public static String getAsString(JsonObject jo, String name) {
        JsonElement je = jo.get(name);
        return je != null && !je.isJsonNull()?je.getAsString():null;
    }

    public static Timestamp getAsTimestamp(JsonObject jo, String name) throws ParseException {
        String str = getAsString(jo, name);
        return str == null?null:stringToTimestamp(str);
    }

    private static SimpleDateFormat getTimestampFormat() {
        TimeZone timeZoneBeijing = TimeZone.getTimeZone("GMT+08:00");
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        format.setTimeZone(timeZoneBeijing);
        return format;
    }

    public static Timestamp stringToTimestamp(String str) throws ParseException {
        return new Timestamp(((SimpleDateFormat)TIMESTAMP_FORMAT.get()).parse(str).getTime());
    }

    public static Integer getAsInt(JsonObject jo, String name) {
        JsonElement je = jo.get(name);
        return je != null && !je.isJsonNull()?Integer.valueOf(je.getAsInt()):null;
    }

    public static Long getAsLong(JsonObject jo, String name) {
        JsonElement je = jo.get(name);
        return je != null && !je.isJsonNull()?Long.valueOf(je.getAsLong()):null;
    }

    public static Boolean getAsBoolean(JsonObject jo, String name) {
        JsonElement je = jo.get(name);
        return je != null && !je.isJsonNull()?Boolean.valueOf(je.getAsBoolean()):null;
    }

    public static JsonObject getAsJsonObject(JsonObject jo, String name) {
        JsonElement je = jo.get(name);
        return je != null && !je.isJsonNull()?je.getAsJsonObject():null;
    }

    public static String[] getAsStringArray(JsonObject jo, String name) {
        JsonElement je = jo.get(name);
        if(je != null && !je.isJsonNull()) {
            JsonArray ja = je.getAsJsonArray();
            if(ja.size() != 0) {
                String[] strs = new String[ja.size()];

                for(int i = 0; i < ja.size(); ++i) {
                    strs[i] = ja.get(i).getAsString();
                }

                return strs;
            }
        }

        return null;
    }

    private static Gson createGson() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(Date.class, new GsonDateDeserializer());
        return gsonBuilder.create();
    }

    private static Gson createGsonDefaultDateFormat() {
        return (new GsonBuilder()).setDateFormat("yyyy-MM-dd HH:mm:ss").create();
    }

    private static Gson createGsonSetDateFormat(String dateFormat) {
        return (new GsonBuilder()).setDateFormat(dateFormat).create();
    }
}
