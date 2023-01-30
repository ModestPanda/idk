package com.lby.jdbc;

import com.mysql.jdbc.Driver;
import org.testng.annotations.Test;
import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class jdbcConn {

    //方式1
    @Test
    public void connect01() throws SQLException {
        Driver driver = new Driver();
        String url="jdbc:mysql://localhost:3306/lby_db02";
        Properties properties=new Properties();
        //说明：user和password是规定好的，后面的值根据实际情况写
        properties.setProperty("user","root");//用户
        properties.setProperty("password","lby");//密码
        Connection connect = driver.connect(url, properties);
        System.out.println(connect);
    }

    //方式2
    @Test
    public void connect02() throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        //使用反射加载Driver类，动态加载，更加灵活，减少依赖性
        Class<?> aClass = Class.forName("com.mysql.jdbc.Driver");
        Driver driver = (Driver)aClass.newInstance();

        String url = "jdbc:mysql://localhost:3306/lby_db02";
        //将用户名和密码放入到Properties对象
        Properties properties = new Properties();
        //说明：user和password是规定好的，后面的值根据实际情况写
        properties.setProperty("user","root");//用户
        properties.setProperty("password","lby");//密码

        Connection connect = driver.connect(url, properties);
        System.out.println("方式2="+connect);
    }

    //方式3 使用DriverManager替代Driver进行统一管理
    @Test
    public void connect03() throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        //使用反射加载Driver
        Class<?> aClass = Class.forName("com.mysql.jdbc.Driver");
        Driver driver =(Driver) aClass.newInstance();

        //创建url和user和password
        String url = "jdbc:mysql://localhost:3306/lby_db02";
        String user = "root";
        String password = "lby";

        DriverManager.registerDriver(driver);//注册Driver驱动

        Connection connection = DriverManager.getConnection(url, user, password);
        System.out.println("方式3="+connection);
    }

    //方式4 使用Class.forName自动完成注册驱动，简化代码
    //这种方式获取连接是使用的最多的，推荐使用
    @Test
    public void connect04() throws ClassNotFoundException, SQLException {
        //使用反射加载了Driver类
        //在加载Driver类时，自动完成注册
        /*
            源码：1.静态代码块，在类加载时，会执行一次
            2.DriverManager.registerDriver(new Driver());
            3.因此注册driver的工作已经完成
            static {
        try {
            DriverManager.registerDriver(new Driver());
        } catch (SQLException var1) {
            throw new RuntimeException("Can't register driver!");
        }
    }
         */
        Class.forName("com.mysql.jdbc.Driver");

        //创建url和user和password
        String url = "jdbc:mysql://localhost:3306/lby_db02";
        String user = "root";
        String password = "lby";

        Connection connection = DriverManager.getConnection(url, user, password);
        System.out.println("方式4="+connection);
    }

    //方式5，在方式4的基础上改进，增加配置文件，让连接mysql更加灵活
    @Test
    public void connect05() throws IOException, ClassNotFoundException, SQLException {
        //通过Properties对象获取配置文件的信息
        Properties properties = new Properties();
        properties.load(new FileInputStream("src\\mysql.properties"));
        //获取相关的值
        String user = properties.getProperty("user");
        String password = properties.getProperty("password");
        String driver = properties.getProperty("driver");
        String url = properties.getProperty("url");

        Class.forName(driver);

        Connection connection = DriverManager.getConnection(url, user, password);
        System.out.println("方式5="+connection);
    }
}
