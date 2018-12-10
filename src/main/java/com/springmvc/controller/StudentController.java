package com.springmvc.controller;

import com.springmvc.business.StudentBusiness;
import com.springmvc.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

//commons-logging的用法，这里jcl：commons-logging被转接到了slf4j，可见pom的配置
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.Map;
import com.springmvc.utils.JsonUtil;

/**
 * Created by z674198 on 2018/12/3.
 */
@Controller
@RequestMapping(value="/student")
public class StudentController {

    //commons-logging的用法，这里jcl：commons-logging被转接到了slf4j，可见pom的配置
    private static final Log LOGGER = LogFactory.getLog(StudentController.class);

    @Autowired
    private StudentBusiness studentBusiness;

    @RequestMapping(method = {RequestMethod.POST}, value = "/getAllStudent.json")
    @ResponseBody
    public String getAllStudent(){
        Map<String, Object> result = studentBusiness.getAllStudent();
        return JsonUtil.objectToJsonString(result);
    }

    @RequestMapping(method = {RequestMethod.POST}, value = "/getStudent.json")
    @ResponseBody
    public String getStudent(@RequestParam Integer id){
        LOGGER.info("getStudent id "+ id);
        LOGGER.warn("getStudent id "+ id);
        LOGGER.debug("getStudent id "+ id);
        LOGGER.error("getStudent id "+ id);
        Map<String, Object> result = studentBusiness.selectByPrimaryKey(id);
        return JsonUtil.objectToJsonString(result);
    }


    @RequestMapping(method = {RequestMethod.POST}, value = "/insertStudent.json")
    @ResponseBody
    public String insertStudent(@RequestBody Student student){
        LOGGER.info("insertStudent name: "+ student.getName());
        Map<String, Object> result = studentBusiness.insertStudent(student);
        return JsonUtil.objectToJsonString(result);
    }


    @RequestMapping(method = {RequestMethod.POST}, value = "/deleteStudent.json")
    @ResponseBody
    public String deleteStudent(@RequestParam Integer id){
        LOGGER.info("deleteStudent id "+ id);
        Map<String, Object> result = studentBusiness.deleteStudent(id);
        return JsonUtil.objectToJsonString(result);
    }

    //自己写的分页
    @RequestMapping(method = {RequestMethod.POST}, value = "/getStudentByPage.json")
    @ResponseBody
    public String getStudentByPage(@RequestParam String keywords, @RequestParam int pageIndex, @RequestParam int pageSize){
        LOGGER.info("getStudentByPage");
        Map<String, Object> result = studentBusiness.getStudentByPage(keywords,pageIndex,pageSize);
        return JsonUtil.objectToJsonString(result);
    }

    //使用pagehelper的分页
    @RequestMapping(method = {RequestMethod.POST}, value = "/getStudentByPage2.json")
    @ResponseBody
    public String getStudentByPage2(@RequestParam String keywords, @RequestParam int pageIndex, @RequestParam int pageSize){
        LOGGER.info("getStudentByPage2");
        Map<String, Object> result = studentBusiness.getStudentByPage2(keywords,pageIndex,pageSize);
        return JsonUtil.objectToJsonString(result);
    }

}
