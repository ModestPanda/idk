package com.lby.jdbc.preparedstatement;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;
import java.util.Scanner;

/**
 * @author Humble
 * @version 1.0
 * 演示PreparedStatement使用
 */
public class PreparedStatement {
    public static void main(String[] args) throws IOException, ClassNotFoundException, SQLException {
        //让用户输入管理员名和密码
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入管理员的名字");//next()：当检测到空格或者单引号就会结束
        String admin_name=scanner.nextLine();//如果希望看到SQL注入，这里需要nextLine
        System.out.println("请输入管理员的密码");
        String admin_pwd=scanner.nextLine();
        //通过Properties对象获取配置文件的信息
        Properties properties=new Properties();
        properties.load(new FileInputStream("src\\mysql.properties"));
        //获取相关的值
        String user=properties.getProperty("user");
        String password=properties.getProperty("password");
        String driver=properties.getProperty("driver");
        String url=properties.getProperty("url");
        //1.注册驱动
        Class.forName(driver);
        //2.建立连接
        Connection connection = DriverManager.getConnection(url, user, password);
        //3.得到PreparedStatement对象
        //3.1 组织Sql语句，Sql语句的 ? 相当于占位符
        String sql = "select name,pwd from admin where name = ? and pwd= ? ";
        //3.2 preparedStatement对象实现了PreparedStatement接口的实现类对象
        java.sql.PreparedStatement preparedStatement = connection.prepareStatement(sql);
        //3.3 给 ? 赋值
        preparedStatement.setString(1,admin_name);
        preparedStatement.setString(2,admin_pwd);
        //4.执行select语句使用executeQuery
        //如果执行的是dml(update,insert,delete) executeUpdate()
        //这里执行executeQuery,不要在写sql
        ResultSet resultSet = preparedStatement.executeQuery();
        if(resultSet.next()){//如果查询到一条记录，则说明该管理员存在
            System.out.println("恭喜，登录成功");
        }else{
            System.out.println("抱歉，登录失败");
        }
        //关闭连接
        resultSet.close();
        preparedStatement.close();
        connection.close();
    }
}
