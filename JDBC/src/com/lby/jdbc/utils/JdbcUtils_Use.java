package com.lby.jdbc.utils;

import org.testng.annotations.Test;
import java.sql.*;

/**
 * @author Humble
 * @version 1.0
 * 该类演示如何使用JDBCUtils工具类，完成dml语句与select语句
 */
public class JdbcUtils_Use {
    public static void main(String[] args) {

    }
    @Test
    public void testSelect(){
        //1.得到连接
        Connection connection = null;

        //2.组织一个sql
        String sql = "select * from actor where id=?";
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        //3.创建一个PreparedStatement对象
        try {
            connection = JDBCUtils.getConnection();
            System.out.println(connection.getClass());//class com.mysql.jdbc.JDBC4Connection
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,4);
            //执行，得到结果集
           resultSet = preparedStatement.executeQuery();
            //遍历该结果集
            while (resultSet.next()){
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String sex = resultSet.getString("sex");
                Date borndate = resultSet.getDate("borndate");
                String phone = resultSet.getString("phone");
                System.out.println(id+"\t"+name+"\t"+sex+"\t"+borndate+"\t"+phone);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            //关闭资源
            JDBCUtils.close(resultSet,preparedStatement,connection);
        }
    }

    @Test
     public void testDml(){

         //1.得到连接
         Connection connection = null;

         //2.组织一个sql
         String sql = "update actor set name = ? where id = ? ";
         PreparedStatement preparedStatement = null;
         //3.创建一个PreparedStatement对象
         try {
             connection = JDBCUtils.getConnection();
             preparedStatement = connection.prepareStatement(sql);
             //给占位符赋值
             preparedStatement.setString(1,"周星驰");
             preparedStatement.setInt(2,4);
             //执行
             preparedStatement.executeUpdate();
         } catch (SQLException e) {
             e.printStackTrace();
         }finally {
            //关闭资源
              JDBCUtils.close(null,preparedStatement,connection);
         }
     }
}
