package com.zp.helloworld;

import com.zp.helloworld.entity.Employee;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by peng.zhang
 * description:
 * Time: 2019-04-28 23:41
 */
public class start {

    /**
     * 1.根据全局配置文件创建 SqlSessionFactory 对象
     * 2.编写sql映射文件
     * 3.将sql映射文件注册在全局配置文件中
     * 4.获取SqlSession,使用sql唯一标志执行sql语句
     * @throws IOException IO
     */
    @Test
    public void gettingStart() throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory =
                new SqlSessionFactoryBuilder().build(inputStream);

        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            Employee employee = sqlSession.selectOne("com.zp.helloworld.entity.EmployeeMapper.selectEmp",1);
            System.out.println(employee);
        } finally {
            sqlSession.close();
        }


    }
}
