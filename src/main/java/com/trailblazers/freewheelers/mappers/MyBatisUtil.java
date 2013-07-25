package com.trailblazers.freewheelers.mappers;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.Reader;
import java.sql.SQLException;

public class MyBatisUtil {
    private static SqlSessionFactory factory;
    private static SqlSession sqlSession = null;

    private MyBatisUtil() {
    }

    static {
        Reader reader = null;
        try {
            reader = Resources.getResourceAsReader("mybatis.config.xml");
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
        factory = new SqlSessionFactoryBuilder().build(reader);
    }

    public static SqlSessionFactory getSqlSessionFactory() {
        return factory;
    }

    public static SqlSession getSqlSession() {
        try {
            if (sqlSession == null || sqlSession.getConnection().isClosed())
                sqlSession = factory.openSession();
        } catch (SQLException e) {
            sqlSession = factory.openSession();
        }
        return sqlSession;
    }
}


