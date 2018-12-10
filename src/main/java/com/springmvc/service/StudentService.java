package com.springmvc.service;

import com.github.pagehelper.PageInfo;
import com.springmvc.model.Student;
import com.springmvc.utils.MyPager;

import java.util.List;

/**
 * Created by z674198 on 2018/12/3.
 */
public interface StudentService {
    List<Student> getAllStudent();
    Student selectByPrimaryKey(Integer id);
    int deleteStudent(Integer id);
    int insertStudent(Student student);

    //分页
    Long getTotalRowCount(String keywords);
    MyPager<Student> getStudentByPage(String keywords, int pageIndex, int pageSize);
    PageInfo<Student> getStudentByPage2(String keywords, int pageIndex, int pageSize);
}
