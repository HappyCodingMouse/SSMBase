package com.springmvc.business;

import com.springmvc.model.Student;
import com.springmvc.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

//commons-logging的用法
//import org.apache.commons.logging.Log;
//import org.apache.commons.logging.LogFactory;

//slf4j的用法
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by z674198 on 2018/12/3.
 */
@Component
public class StudentBusinessImpl implements StudentBusiness {
    //commons-logging的用法
    //private static final Log LOGGER = LogFactory.getLog(StudentBusinessImpl.class);

    //slf4j的用法
    private static Logger LOGGER = LoggerFactory.getLogger(StudentBusinessImpl.class);

    @Autowired
    private StudentService studentService;

    @Override
    public Map<String, Object> getAllStudent() {
        try {
            Map<String, Object> result = new HashMap<String, Object>();

            result.put("student", studentService.getAllStudent());
            result.put("respCode", "1000");
            result.put("respMsg", "Successfully fetched students.");
            LOGGER.info("Successfully fetched students.");
            LOGGER.warn("Successfully fetched students.");
            LOGGER.debug("Successfully fetched students.");
            LOGGER.error("Successfully fetched students.");
            return result;
        } catch (Exception e) {
            LOGGER.error("Error getting all student", e);
            Map<String, Object> failResult = new HashMap<String, Object>();
            failResult.put("respCode", "1001");
            failResult.put("respMsg", "Error getting all students");
            LOGGER.info("Error fetched students.");
            LOGGER.warn("Error fetched students.");
            LOGGER.debug("Error fetched students.");
            LOGGER.error("Error fetched students.");
            return failResult;
        }
    }

    @Override
    public Map<String, Object> selectByPrimaryKey(Integer id) {
        try {
            Map<String, Object> result = new HashMap<String, Object>();
            result.put("student", studentService.selectByPrimaryKey(id));
            result.put("respCode", "1000");
            result.put("respMsg", "Successfully fetched students.");
            return result;
        } catch (Exception e) {
            LOGGER.error("Error getting all student", e);
            Map<String, Object> failResult = new HashMap<String, Object>();
            failResult.put("respCode", "1001");
            failResult.put("respMsg", "Error getting student");
            return failResult;
        }
    }

    @Override
    public Map<String, Object> insertStudent(Student student) {
        try {
            Map<String, Object> result = new HashMap<String, Object>();
            result.put("student", student);
            result.put("impact record num", studentService.insertStudent(student));
            result.put("respCode", "1000");
            result.put("respMsg", "Successfully insert students.");
            return result;
        } catch (Exception e) {
            LOGGER.error("Error insert student", e);
            Map<String, Object> failResult = new HashMap<String, Object>();
            failResult.put("respCode", "1001");
            failResult.put("respMsg", "Error insert student");
            return failResult;
        }
    }

    @Override
    public Map<String, Object> deleteStudent(Integer id) {
        try {
            Map<String, Object> result = new HashMap<String, Object>();
            result.put("impact record num", studentService.deleteStudent(id));
            result.put("studentId", id);
            result.put("respCode", "1000");
            result.put("respMsg", "Successfully delete students.");
            return result;
        } catch (Exception e) {
            LOGGER.error("Error delete student", e);
            Map<String, Object> failResult = new HashMap<String, Object>();
            failResult.put("respCode", "1001");
            failResult.put("respMsg", "Error delete student");
            return failResult;
        }
    }

    @Override
    public Map<String, Object> getStudentByPage(String keywords, int pageIndex, int pageSize) {
        try {
            Map<String, Object> result = new HashMap<String, Object>();

            result.put("MyPager", studentService.getStudentByPage(keywords,pageIndex,pageSize));
            result.put("respCode", "1000");
            result.put("respMsg", "Successfully fetched students.");
            return result;
        } catch (Exception e) {
            LOGGER.error("Error getting student", e);
            Map<String, Object> failResult = new HashMap<String, Object>();
            failResult.put("respCode", "1001");
            failResult.put("respMsg", "Error getting students");
            return failResult;
        }
    }

    @Override
    public Map<String, Object> getStudentByPage2(String keywords, int pageIndex, int pageSize) {
        try {
            Map<String, Object> result = new HashMap<String, Object>();

            result.put("pageInfo", studentService.getStudentByPage2(keywords,pageIndex,pageSize));
            result.put("respCode", "1000");
            result.put("respMsg", "Successfully fetched students.");
            return result;
        } catch (Exception e) {
            LOGGER.error("Error getting student", e);
            Map<String, Object> failResult = new HashMap<String, Object>();
            failResult.put("respCode", "1001");
            failResult.put("respMsg", "Error getting  students");
            return failResult;
        }
    }

}
