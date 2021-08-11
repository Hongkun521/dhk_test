package com.dhk;

import com.dhk.dao.StudentDao;
import com.dhk.dao.StudentDaoImpl;
import com.dhk.domain.Student;
import com.dhk.utils.MyBatisUtils;
import com.dhk.vo.QueryParam;
import com.github.pagehelper.PageHelper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class TestMybatis {
    @Test
    public void testSelect() throws IOException {
        //使用mybatis的动态代理机制使用sqlSession.getMapper（dao接口）
        //getMapper能获取dao接口对于的实现类对象
        SqlSession sqlSession = MyBatisUtils.getSqlSession();
        StudentDao dao = sqlSession.getMapper(StudentDao.class);
        System.out.println("dao="+dao.getClass().getName());
        List<Student> studentList = dao.selectStudents();
        for(Student stu:studentList){
            System.out.println("学生:"+stu);
        }
        /*
        StudentDao dao=new StudentDaoImpl();
        List<Student> studentList = dao.selectStudents();
        studentList.forEach(student -> System.out.println(student));
        */
    }
    @Test
    public void testSelectById(){
        SqlSession sqlSession = MyBatisUtils.getSqlSession();
        StudentDao dao = sqlSession.getMapper(StudentDao.class);
        Student student = dao.selectStudentById(1004);
        System.out.println(student);
        sqlSession.close();
    }

    @Test
    public void testInsert() throws IOException {
        SqlSession sqlSession = MyBatisUtils.getSqlSession();
        StudentDao dao = sqlSession.getMapper(StudentDao.class);
        Student stu = new Student();
        stu.setId(1005);
        stu.setName("李菲菲");
        stu.setEmail("feifei@sina.com");
        stu.setAge(19);
        int num = dao.insertStudent(stu);
        sqlSession.commit();
        System.out.println("插入成功："+num);
        sqlSession.close();
        /*
        //1.定义mybatis主配置文件的名称，从类路径的根开始（target/classes）
        String config="mybatis.xml";
        //2.读取这个config表示的文件
        InputStream in = Resources.getResourceAsStream(config);
        //3.创建了SQLSessionFactoryBuilder对象
        SqlSessionFactoryBuilder builder=new SqlSessionFactoryBuilder();
        //4.创建SqlSessionFactory对象
        SqlSessionFactory factory=builder.build(in);
        //5.获取sqlSession对象，从sqlsessionFactory中获取
        SqlSession sqlSession = factory.openSession();
        //6.制定要执行的sql语句的标识。sql映射文件的namespace+'.'+标签id值
        String sql="com.dhk.dao.StudentDao.insertStudent";
        //7.执行sql语句，通过sqlId找到语句
        Student stu=new Student();
        stu.setName("飞扬");
        stu.setId(1003);
        stu.setEmail("feiyang@qq.com");
        stu.setAge(20);
        int nums = sqlSession.insert(sql,stu);
        sqlSession.commit();
        //8.输出结果
        System.out.println("插入操作执行成功："+nums);
        //9.关闭sqlSession对象
        sqlSession.close();
        */
    }

    @Test
    public void testselectMultiParam(){
        SqlSession sqlSession = MyBatisUtils.getSqlSession();
        StudentDao dao = sqlSession.getMapper(StudentDao.class);
        List<Student> studentList = dao.selectMultiParam("王sir", 22);
        for(Student stu:studentList){
            System.out.println("学生="+stu);
        }
        sqlSession.close();
    }

    @Test
    public void testselectLikeOne(){
        SqlSession sqlSession = MyBatisUtils.getSqlSession();
        StudentDao dao = sqlSession.getMapper(StudentDao.class);
        List<Student> studentList = dao.selectTestLikeOne("%扬%");
        for(Student stu:studentList){
            System.out.println("学生="+stu);
        }
        sqlSession.close();
    }

    @Test
    public void testselectLikeTwo(){
        SqlSession sqlSession = MyBatisUtils.getSqlSession();
        StudentDao dao = sqlSession.getMapper(StudentDao.class);
        List<Student> studentList = dao.selectTestLikeTwo("宅");
        for(Student stu:studentList){
            System.out.println("学生="+stu);
        }
        sqlSession.close();
    }

    @Test
    public void testselectStudentIf(){
        SqlSession sqlSession = MyBatisUtils.getSqlSession();
        StudentDao dao = sqlSession.getMapper(StudentDao.class);
        List<Student> studentList = dao.selectStudentIf("王sir", 21);
        for(Student stu:studentList){
            System.out.println("学生="+stu);
        }
        sqlSession.close();
    }

    @Test
    public void testselectStudentWhere(){
        SqlSession sqlSession = MyBatisUtils.getSqlSession();
        StudentDao dao = sqlSession.getMapper(StudentDao.class);
        Student student=new Student();
        student.setName("程序猿");
        student.setAge(25);
        List<Student> studentList = dao.selectStudentWhere(student);
        for(Student stu:studentList){
            System.out.println("学生="+stu);
        }
        sqlSession.close();
    }

    @Test
    public void testselectStudentPageHelp(){
        SqlSession sqlSession = MyBatisUtils.getSqlSession();
        StudentDao dao = sqlSession.getMapper(StudentDao.class);
        PageHelper.startPage(1,3);
        List<Student> studentList = dao.selectStudentPageHelper();
        studentList.forEach(student -> System.out.println(student));
        sqlSession.close();
    }

    @Test
    public void testselectMultiParamObj(){
        SqlSession sqlSession = MyBatisUtils.getSqlSession();
        StudentDao dao = sqlSession.getMapper(StudentDao.class);
        QueryParam queryParam=new QueryParam();
        queryParam.setParamName("%飞扬%");
        queryParam.setParamAge(19);
        List<Student> studentList = dao.selectMultiParamObject(queryParam);
        for(Student stu:studentList){
            System.out.println("学生="+stu);
        }
        sqlSession.close();
    }

    @Test
    public void testselectMultiParamStudent(){
        SqlSession sqlSession = MyBatisUtils.getSqlSession();
        StudentDao dao = sqlSession.getMapper(StudentDao.class);
        Student student=new Student();
        student.setName("飞扬");
        student.setAge(19);
        List<Student> studentList = dao.selectMultiParamStudent(student);
        for(Student stu:studentList){
            System.out.println("学生="+stu);
        }
        sqlSession.close();
    }
}
