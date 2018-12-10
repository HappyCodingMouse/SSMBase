package com.springmvc.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.springmvc.dao.StudentMapper;
import com.springmvc.model.Student;
import com.springmvc.utils.MyPager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by z674198 on 2018/12/3.
 */
@Service
public class StudentServiceImpl implements StudentService{

    @Autowired(required = false)
    private StudentMapper studentMapper;

    //slf4j的用法
    private static Logger LOGGER = LoggerFactory.getLogger(StudentServiceImpl.class);

    @Override
    public List<Student> getAllStudent() {
        List<Student> studentList = studentMapper.getAllStudent();
        return studentList == null ? new ArrayList<Student>() : studentList;
    }

    @Override
    public Student selectByPrimaryKey(Integer id) {
        Student student = studentMapper.selectByPrimaryKey(id);
        return student == null ? new Student() : student;
    }

    @Override
    public int deleteStudent(Integer id) {
        return studentMapper.deleteByPrimaryKey(id);

    }

    @Override
    public int insertStudent(Student student) {
        return studentMapper.insert(student);
    }

    @Override
    public Long getTotalRowCount(String keywords) {
        return studentMapper.getTotalRowCount(keywords);
    }


    @Override
    public MyPager<Student> getStudentByPage(String keywords, int pageIndex, int pageSize) {
        int startIndex = (pageIndex - 1) * pageSize;
        List<Student> students = studentMapper.getStudentByPage(keywords,startIndex,pageSize);
        Long totalRowCount = studentMapper.getTotalRowCount(keywords);
        MyPager<Student> page = new MyPager(pageSize,pageIndex,totalRowCount,students);
        return page;
    }


    @Override
    public PageInfo<Student> getStudentByPage2(String keywords, int pageIndex, int pageSize) {
        Page<Student> page = PageHelper.startPage(pageIndex,pageSize,true);
        List<Student> students = studentMapper.getStudentByPage2(keywords);
        PageInfo<Student> pageInfo = new PageInfo<>(students);
        return pageInfo;

    }

}
