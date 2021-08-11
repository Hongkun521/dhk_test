package com.dhk.dao;

import com.dhk.domain.Student;
import com.dhk.utils.MyBatisUtils;
import com.dhk.vo.QueryParam;
import org.apache.ibatis.session.SqlSession;

import java.util.List;
//不需要实现的
public class StudentDaoImpl implements StudentDao{
    @Override
    public List<Student> selectStudents() {


        SqlSession sqlSession = MyBatisUtils.getSqlSession();
        String sql="com.dhk.dao.StudentDao.selectStudents";
        List<Student> studentsList = sqlSession.selectList(sql);
        sqlSession.close();
        return studentsList;

    }

    @Override
    public int insertStudent(Student student) {
        SqlSession sqlSession = MyBatisUtils.getSqlSession();
        String sql="com.dhk.dao.StudentDao.insertStudent";
        int nums = sqlSession.insert(sql,student);
        sqlSession.commit();
        sqlSession.close();
        return nums;
    }

    @Override
    public Student selectStudentById(Integer id) {
        return null;
    }

    @Override
    public List<Student> selectMultiParam(String name, Integer age) {
        return null;
    }

    @Override
    public List<Student> selectMultiParamObject(QueryParam param) {
        return null;
    }

    @Override
    public List<Student> selectMultiParamStudent(Student student) {
        return null;
    }

    @Override
    public List<Student> selectTestLikeOne(String name) {
        return null;
    }

    @Override
    public List<Student> selectTestLikeTwo(String name) {
        return null;
    }

    @Override
    public List<Student> selectStudentIf(String name, int age) {
        return null;
    }

    @Override
    public List<Student> selectStudentWhere(Student student) {
        return null;
    }

    @Override
    public List<Student> selectStudentPageHelper() {
        return null;
    }
}
