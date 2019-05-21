package com.zp.helloworld;

import com.zp.helloworld.dao.EmployeeMapper;
import com.zp.helloworld.entity.Employee;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

/**
 * Created by peng.zhang
 * description: 1. SqlSession 代表和数据库的一次会话,用完必须关闭
 * 2. SqlSession 和 connection 一样都是非线程安全的,每次使用都要去获取新对象
 * 3. mapper 接口没有实现类,但 mybatis 会为这个接口生成一个代理对象
 * 4. 两个重要配置文件:
 * mybatis的全局配置文件,包含数据库连接池信息,事务管理器信息等系统运行环境信息
 * sql映射文件,保存了每一个sql语句的映射信息
 * Time: 2019-04-28 23:41
 */
public class start {


    private SqlSessionFactory getSqlSessionFactory() throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        return new SqlSessionFactoryBuilder().build(inputStream);
    }


    /**
     * 老版本的使用方法
     * 1.根据全局配置文件创建 SqlSessionFactory 对象
     * 2.编写sql映射文件
     * 3.将sql映射文件注册在全局配置文件中
     * 4.获取SqlSession,使用sql唯一标志执行sql语句
     *
     * @throws IOException IO
     */
    @Test
    public void gettingStart() throws IOException {
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            Employee employee = sqlSession.selectOne("com.zp.helloworld.dao.EmployeeMapper.getEmpById", 1);
            System.out.println(employee);
        } finally {
            sqlSession.close();
        }

    }

    /**
     * 新版本的使用方法,接口式编程
     *
     * @throws IOException IO
     */
    @Test
    public void getEmpByMapper() throws IOException {
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
        SqlSession sqlSession = sqlSessionFactory.openSession();

        try {
//            获取接口的实现类对象
//            会为接口自动生成一个代理对象,代理对象去执行增删改查方法
            EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
            Employee employee = mapper.getEmpById(1);
            System.out.println(mapper.getClass());
            System.out.println(employee);
        } finally {
            sqlSession.close();
        }
    }

    /**
     * 1. mybatis 允许增删改查定义以下类型返回值
     *      Integer,Long,Boolean,void
     * 2. 需要手动提交数据
     *      sqlSessionFactory.openSession() --> 手动提交
     *      sqlSessionFactory.openSession(true) --> 自动提交
     * @throws IOException IO
     */
    @Test
    public void updateEmpById() throws IOException {
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
        SqlSession sqlSession = sqlSessionFactory.openSession();

        try {
            EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
            Employee employee = new Employee(1, "peng.zhang", "18582692008@163.com", "1");
            boolean b = mapper.updateEmpById(employee);
            System.out.println(b);

//            手动提交数据
            sqlSession.commit();
        } finally {
            sqlSession.close();
        }
    }


    /**
     * 插入数据后,获取自增主键
     * @throws IOException IO
     */
    @Test
    public void addEmp() throws IOException {
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
        SqlSession sqlSession = sqlSessionFactory.openSession();

        try {
            EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
            Employee employee = new Employee( "yue.yuan", "13992365953@163.com", "1");
            Long b = mapper.addEmp(employee);
            System.out.println(b);

//            手动提交数据
            sqlSession.commit();
        } finally {
            sqlSession.close();
        }
    }

    /**
     * 插入数据后,获取自增主键
     * @throws IOException IO
     */
    @Test
    public void getEmpByLastNameLikeReturnMap() throws IOException {
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
        SqlSession sqlSession = sqlSessionFactory.openSession();

        try {
            EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
            Map<Integer, Employee> peng = mapper.getEmpByLastNameLikeReturnMap("yuan");
            for (Integer s : peng.keySet()) {
                System.out.println(s + ": " + peng.get(s));
            }

            Map<String, Object> map = mapper.getEmpByIdReturnMap(1);
            for (String s : map.keySet()) {
                System.out.println(s + ": " + map.get(s));
            }
        } finally {
            sqlSession.close();
        }
    }
}
