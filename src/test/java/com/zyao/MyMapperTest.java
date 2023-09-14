package com.zyao;

import com.zyao.mapper.sys.SysUserMapper;
import com.zyao.modal.sys.SysUser;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;

import javax.sql.DataSource;

@SpringBootTest
public class MyMapperTest {
    @Autowired
    private DataSource dataSource;

    @Autowired
    private SqlSessionFactory sqlSessionFactory;

    @Bean
    public SqlSessionFactory sqlSessionFactory() throws Exception {
        SqlSessionFactoryBean sessionFactoryBean = new SqlSessionFactoryBean();
        sessionFactoryBean.setDataSource(dataSource);
        return sessionFactoryBean.getObject();
    }

    @Test
    public void testMyMapper() throws Exception {
//        SqlSessionFactory sqlSessionFactory = sqlSessionFactory();
        SqlSession sqlSession = sqlSessionFactory.openSession();
        SysUserMapper sysUserMapper = (SysUserMapper) sqlSession.getMapper(SysUser.class);
        SysUser sysUser = sysUserMapper.selectById(1);
        sqlSession.close();
    }
}