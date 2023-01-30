package com.lby.jdbc.datasource;

import com.lby.jdbc.utils.JDBCUtilsByDruid;
import org.testng.annotations.Test;
import java.sql.*;
import java.util.ArrayList;

/**
 * @author Humble
 * @version 1.0
 */
public class JDBCUtilByDruid_USE {

    @Test
    public void testSelect() throws SQLException {

        System.out.println("使用druid方式完成");
        //1.得到连接
        Connection connection = null;

        //2.组织一个sql
        String sql = "select * from actor where id>=?";
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        //3.创建一个PreparedStatement对象
        try {
            connection = JDBCUtilsByDruid.getConnection();
            System.out.println(connection.getClass());//class com.alibaba.druid.pool.DruidPooledConnection
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,3);
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
            JDBCUtilsByDruid.close(resultSet,preparedStatement,connection);
        }
    }


    //使用土方法来解决ResultSet =封装=> ArrayList
    @Test
    public ArrayList<Actor> testSelectToArrayList() throws SQLException {

        System.out.println("使用druid方式完成");
        //1.得到连接
        Connection connection = null;

        //2.组织一个sql
        String sql = "select * from actor where id>=?";
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        ArrayList<Actor> list = new ArrayList<Actor>();//创建ArrayList对象，存放actor对象
        //3.创建一个PreparedStatement对象
        try {
            connection = JDBCUtilsByDruid.getConnection();
            System.out.println(connection.getClass());//class com.alibaba.druid.pool.DruidPooledConnection
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,3);
            //执行，得到结果集
            resultSet = preparedStatement.executeQuery();
            //遍历该结果集
            while (resultSet.next()){
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String sex = resultSet.getString("sex");
                Date borndate = resultSet.getDate("borndate");
                String phone = resultSet.getString("phone");
                //把得到的resultSet的记录，封装到Actor对象，放入到List集合
                list.add(new Actor(id,name,sex,borndate,phone));
            }

            //System.out.println("list集合数据="+list);
            for (Actor actor:list){
                System.out.println("id="+actor.getId()+"\t"+actor.getName());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            //关闭资源
            JDBCUtilsByDruid.close(resultSet,preparedStatement,connection);
        }

        //因为ArrayList和connection没有任何关联，所以该集合可以反复使用
        return list;
    }
}
