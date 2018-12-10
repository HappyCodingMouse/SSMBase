package com.springmvc.business;

import com.springmvc.model.Student;

import java.util.Map;

/**
 * Created by z674198 on 2018/12/3.
 */
public interface StudentBusiness {
    Map<String, Object> getAllStudent();
    Map<String, Object> selectByPrimaryKey(Integer id);
    Map<String, Object> insertStudent(Student student);
    Map<String, Object> deleteStudent(Integer id);

    Map<String, Object> getStudentByPage(String keywords, int pageIndex, int pageSize);
    Map<String, Object> getStudentByPage2(String keywords, int pageIndex, int pageSize);
}
