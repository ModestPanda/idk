package com.lby.jdbc.datasource;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.testng.annotations.Test;
import java.beans.PropertyVetoException;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

/**
 * @author Humble
 * @version 1.0
 * 演示C3P0使用
 */
public class C3P0_ {

    //方式1：相关参数，在程序中指定user,url,password等

        @Test
    public void testC3P0_01() throws IOException, PropertyVetoException, SQLException {

            //1,创建一个数据源对象
            ComboPooledDataSource comboPooledDataSource = new ComboPooledDataSource();
            //2.通过配置文件mysql.properties获取相关的信息
            Properties properties = new Properties();
            properties.load(new FileInputStream("src\\mysql.properties"));
            //获取相关的值
            String user = properties.getProperty("user");
            String password = properties.getProperty("password");
            String driver = properties.getProperty("driver");
            String url = properties.getProperty("url");

            //给数据源 comboPooledDataSource 设置相关的参数
            //注意：连接管理是由comboPoolDataSource来管理
            comboPooledDataSource.setDriverClass(driver);
            comboPooledDataSource.setJdbcUrl(url);
            comboPooledDataSource.setUser(user);
            comboPooledDataSource.setPassword(password);

            //设置初始化连接数
            comboPooledDataSource.setInitialPoolSize(10);
            //最大连接数
            comboPooledDataSource.setMaxPoolSize(50);
            //测试连接池的效率，测试对mysql 5000次操作
            long start = System.currentTimeMillis();
            for (int i = 0; i < 5000; i++) {
                Connection connection = comboPooledDataSource.getConnection();
                //System.out.println("连接成功");
                connection.close();
            }
            long end = System.currentTimeMillis();
            System.out.println("C3P0 5000次连接mysql耗时 = "+(end-start));//C3P0 5000次连接mysql耗时 = 325
        }


    //第二种方式使用配置文件模板来完成
    //1.将c3p0提供的c3p0.config.xmL拷贝到src目录下
    //2.该文件指定了连接数据库和连接池的相关参数
    @Test
    public void testC3P0_02()throws SQLException {


        ComboPooledDataSource comboPooledDataSource = new ComboPooledDataSource("lby");
        //测试5000次连接mysql
        long start = System.currentTimeMillis();
        System.out.println("开始执行......");
        for (int i = 0; i < 5000000; i++) {
            Connection connection = comboPooledDataSource.getConnection();
            //System.out.println("连接OK~");
            connection.close();
        }
        long end = System.currentTimeMillis();
        System.out.println("c3p0的第二种方式耗时=" + ( end- start));//c3p0的第二种方式耗时=235
    }
}
