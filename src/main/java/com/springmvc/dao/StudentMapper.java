package com.springmvc.dao;

import com.springmvc.model.Student;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface StudentMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Student record);

    int insertSelective(Student record);

    Student selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Student record);

    int updateByPrimaryKey(Student record);

    List<Student> getAllStudent();


    // 分页
    Long getTotalRowCount(@Param("keywords") String keywords);
    List<Student> getStudentByPage(@Param("keywords") String keywords, @Param("startIndex") int startIndex, @Param("pageSize") int pageSize);
    List<Student> getStudentByPage2(@Param("keywords") String keywords);

}