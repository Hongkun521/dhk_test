package com.dhk.dao;

import com.dhk.domain.Student;
import com.dhk.vo.QueryParam;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface StudentDao {
    public List<Student> selectStudents();
    public int insertStudent(Student student);
    public Student selectStudentById(Integer id);
    public List<Student> selectMultiParam(@Param("myname") String name,
                                          @Param("myage") Integer age);
    public List<Student> selectMultiParamObject(QueryParam param);
    public List<Student> selectMultiParamStudent(Student student);
    public List<Student> selectTestLikeOne(String name);
    public List<Student> selectTestLikeTwo(String name);
    public List<Student> selectStudentIf(@Param("name") String name,
                                         @Param("age") int age);
    public List<Student> selectStudentWhere(Student student);
    public List<Student> selectStudentPageHelper();
}
