package com.lby.jdbc.preparedstatement;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.Scanner;

/**
 * @author Humble
 * @version 1.0
 */
public class PreparedStatementDML {
    public static void main(String[] args) throws SQLException, ClassNotFoundException, IOException {
        //让用户输入管理员名和密码
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入管理员的名字");//next()：当检测到空格或者单引号就会结束
        String admin_name=scanner.nextLine();//如果希望看到SQL注入，这里需要nextLine
        //System.out.println("请输入管理员的新密码");
        //String admin_pwd=scanner.nextLine();
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
        //String sql = "insert into admin values(?,?)";
        //String sql = "update admin set pwd = ? where name = ? ";
        String sql = "delete from admin where name = ?";
                //3.2 preparedStatement对象实现了PreparedStatement接口的实现类对象
        java.sql.PreparedStatement preparedStatement = connection.prepareStatement(sql);
        //3.3 给 ? 赋值
        preparedStatement.setString(1,admin_name);
        //preparedStatement.setString(2,admin_name);
        //4.执行dml语句使用executeUpdate
        int rows = preparedStatement.executeUpdate();
        System.out.println(rows>0?"执行成功":"执行失败");
        //关闭连接
        preparedStatement.close();
        connection.close();
    }
}
