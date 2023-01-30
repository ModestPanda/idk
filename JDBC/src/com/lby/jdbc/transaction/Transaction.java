package com.lby.jdbc.transaction;

import com.lby.jdbc.utils.JDBCUtils;
import org.testng.annotations.Test;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * @author Humble
 * @version 1.0
 * 演示JDBC中如何使用事务
 */
public class Transaction {

    //没有使用事务
    @Test
    public void noTransaction() {

        //操作转账的业务
        //1.得到连接
        Connection connection = null;
        //2.组织一个sql
        String sql = "update account set money = money-100 where id = 600 ";
        String sql2 = "update account set money = money+100 where id = 700 ";
        PreparedStatement preparedStatement = null;
        //3.创建一个PreparedStatement对象
        try {
            connection = JDBCUtils.getConnection();//默认情况下，connection自动提交
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.executeUpdate();//执行第一条sql语句

            int i = 1/0;//抛出异常
            preparedStatement = connection.prepareStatement(sql2);
            preparedStatement.executeUpdate();//执行第二条sql语句


        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            //关闭资源
            JDBCUtils.close(null, preparedStatement, connection);
        }
    }


    //使用事务
    @Test
    public void Transaction() {

        //操作转账的业务
        //1.得到连接
        Connection connection = null;
        //2.组织一个sql
        String sql = "update account set money = money-100 where id = 600 ";
        String sql2 = "update account set money = money+100 where id = 700 ";
        PreparedStatement preparedStatement = null;
        //3.创建一个PreparedStatement对象
        try {
            connection = JDBCUtils.getConnection();//默认情况下，connection自动提交
            //将connection设置为不自动提交
            connection.setAutoCommit(false);
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.executeUpdate();//执行第一条sql语句

            //int i = 1/0;//抛出异常
            preparedStatement = connection.prepareStatement(sql2);
            preparedStatement.executeUpdate();//执行第二条sql语句

            //提交事务
            connection.commit();

        } catch (SQLException e) {
            //这里可以进行回滚，即撤销执行的sql
            //默认回滚到事务开始的状态
            System.out.println("执行发生了异常，撤销执行的sql");
            try {
                connection.rollback();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            e.printStackTrace();
        } finally {
            //关闭资源
            JDBCUtils.close(null, preparedStatement, connection);
        }
    }
}
