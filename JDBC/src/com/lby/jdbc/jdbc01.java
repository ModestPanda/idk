package com.lby.jdbc;

import com.mysql.jdbc.Driver;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class jdbc01 {
    public static void main(String[] args) throws SQLException {
        //前置工作：在1项目下创建一个文件夹比如libs
        //将mysql.jar拷贝到该目录下，点击add to library....
        //加入到项目中
        //1.注册驱动
        Driver driver = new Driver();

        //2.得到连接
        //解读
        /*
            (1)jdbc::mysql:// 规定好来表示协议的，通过jdbc的方式连接mysql
            (2)localhost: 主机，可以是ip地址
            (3)3306 表示mysql监听的端口
            (4)lby_db02 连接到mysql dbms的哪个数据库
            (5)mysql的连接本质就是前面学到过的socket连接
         */
        String url="jdbc:mysql://localhost:3306/lby_db02";
        //将用户名和密码放入到Properties的对象中
        Properties properties=new Properties();
        //说明：user和password是规定好的，后面的值根据实际情况写
        properties.setProperty("user","root");//用户
        properties.setProperty("password","lby");//密码
        Connection connect = driver.connect(url, properties);
        //3.执行sql
        //String sql="insert into actor values(null,'李白','男','1000-11-11','110')";
        //String sql="update actor set name='杜甫' where id=1";
        String sql="delete from actor where id=1";
        //创建一个Statement对象
        //Statement用于执行静态SQL语句并返回其生成的结果的对象
        Statement statement = connect.createStatement();
        int rows = statement.executeUpdate(sql);//如果是dml语句，返回的就是影响的行数

        System.out.println(rows>0?"成功":"失败");
        //4.关闭连接资源
        statement.close();
        connect.close();
    }
}
