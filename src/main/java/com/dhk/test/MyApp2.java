package com.dhk.test;

import com.dhk.domain.Student;
import com.dhk.utils.MyBatisUtils;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class MyApp2 {
    public static void main(String[] args) throws IOException {


        SqlSession sqlSession = MyBatisUtils.getSqlSession();
        //6.(重要)制定要执行的sql语句的标识。sql映射文件的namespace+'.'+标签id值
        String sql="com.dhk.dao.StudentDao"+"."+"selectStudents";
        //7.(重要)执行sql语句，通过sqlId找到语句
        List<Student> studentList = sqlSession.selectList(sql);
        //8.输出结果
        studentList.forEach(student -> System.out.println(student));
        //9.关闭sqlSession对象
        sqlSession.close();
    }
}
