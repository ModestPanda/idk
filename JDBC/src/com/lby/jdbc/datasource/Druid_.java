package com.lby.jdbc.datasource;
import com.alibaba.druid.pool.DruidDataSourceFactory;
import org.testng.annotations.Test;
import javax.sql.DataSource;
import java.io.FileInputStream;
import java.sql.Connection;
import java.util.Properties;

/**
 * @author Humble
 * @version 1.0
 * 测试Druid的使用
 */
public class Druid_ {

    @Test
    public void testDruid() throws Exception {
        //1.加入Druid jar包
        //2.加入配置文件，将该文件拷贝到项目的src目录下
        //3.创建一个Properties对象，读取配置文件
        Properties properties = new Properties();
        properties.load(new FileInputStream("src\\druid.properties"));
        //4.创建一个指定参数的数据库连接池
        DataSource dataSource = DruidDataSourceFactory.createDataSource(properties);

        long start = System.currentTimeMillis();
        for(int i=0;i<5000000;i++) {
            Connection connection = dataSource.getConnection();
            //System.out.println("连接成功");
            connection.close();
        }
        long end = System.currentTimeMillis();
        System.out.println("druid连接池操作5000000次耗时="+(end-start));
        //druid连接池操作5000000次耗时=624,而c3p0第二种方式同样连接5000000次耗时=6049
    }
}
