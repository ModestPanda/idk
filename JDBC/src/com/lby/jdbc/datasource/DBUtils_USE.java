package com.lby.jdbc.datasource;

import com.lby.jdbc.utils.JDBCUtilsByDruid;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.testng.annotations.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * @author Humble
 * @version 1.0
 */
public class DBUtils_USE {

    @Test
    //使用apache-DBUtils 工具类 + druid 完成对表的crud操作
    public void testQueryMany() throws SQLException {//返回结果是多行的情况

        //1.得到连接(druid)
        Connection connection = JDBCUtilsByDruid.getConnection();
        //2.使用DBUtils类和接口，先引入DBUtils的jar包，再加入到本Project
        //3.创建一个QueryRunner对象
        QueryRunner queryRunner = new QueryRunner();
        //4.就可以执行相关的方法，返回ArrayList结果集
        //String sql = "select * from actor where id >= ?";
        String sql="select id,name from actor where id >= ?";
        //注意:该sql语句也可以查询部分列
        //String sql="select id,name from actor where id >= ?";
        /*
            1.query方法就是执行sql语句，得到resultSet----封装到---->ArrayList集合中
            2.返回集合
            3.connection：连接
            4.sql：执行的sql语句
            5.new BeanListHandler<>(Actor.class)：在将ArrayList -> Actor对象 -> 封装到ArrayList
            6.底层使用反射机制去获取Actor类的属性,然后进行封装
            7."1"就是给sql语句中的问号(?)赋值的,可以有多个值,因为是可变参数
            8.底层得到的resultSet,会在query关闭,关闭PreparedStatement
         */
        /**
         * 分析queryRunner.query方法:
         *  public <T> T query(Connection conn, String sql, ResultSetHandler<T> rsh, Object... params) throws SQLException {
         *         PreparedStatement stmt = null;
         *         ResultSet rs = null;
         *         T result = null;
         *
         *         try {
         *             stmt = this.prepareStatement(conn, sql);//创建PreparedStatement
         *             this.fillStatement(stmt, params);//对sql进行 ？ 赋值
         *             rs = this.wrap(stmt.executeQuery());//执行sql，返回resultSet
         *             result = rsh.handle(rs);//返回的resultSet --> ArrayList[result] [使用反射，对传入class对象处理]
         *         } catch (SQLException var33) {
         *             this.rethrow(var33, sql, params);
         *         } finally {
         *             try {
         *                 this.close(rs);//关闭resultSet
         *             } finally {
         *                 this.close((Statement)stmt);//关闭PreparedStatement对象
         *             }
         *         }
         *
         *         return result;
         *     }
         */
        List<Actor> list =
                queryRunner.query(connection, sql, new BeanListHandler<>(Actor.class), 1);
        System.out.println("输出集合的信息");
        for (Actor actor:list){
            System.out.print(actor);
        }
        //释放资源
        JDBCUtilsByDruid.close(null,null,connection);
    }


    @Test
    //演示 apache-dbutils + druid 完成返回的结果是单行记录(单个对象)
    public void testQuerySingle() throws SQLException {
        //1.得到连接(druid)
        Connection connection = JDBCUtilsByDruid.getConnection();
        //2.使用DBUtils类和接口，先引入DBUtils的jar包，再加入到本Project
        //3.创建一个QueryRunner对象
        QueryRunner queryRunner = new QueryRunner();
        //4.就可以执行相关的方法，返回单个对象
        String sql = "select * from actor where id = ?";
        //解读
        //因为我们返回的是单行记录<-->单个对象，使用的 Handler 是 BeanHandler
        Actor actor = queryRunner.query(connection, sql, new BeanHandler<>(Actor.class), 4);
        System.out.println(actor);
        //Actor{id=4,name='周星驰',sex='男',borndate=1990-11-11 00:00:00.0,phone='120'}

        //释放资源
        JDBCUtilsByDruid.close(null,null,connection);
    }



    @Test
    //演示 apache-dbutils + druid 完成查询结果是单行单列，返回的就是Object
    public void testScalar() throws SQLException {
        //1.得到连接(druid)
        Connection connection = JDBCUtilsByDruid.getConnection();
        //2.使用DBUtils类和接口，先引入DBUtils的jar包，再加入到本Project
        //3.创建一个QueryRunner对象
        QueryRunner queryRunner = new QueryRunner();
        //4.就可以执行相关的方法，返回单行单列
        String sql = "select name from actor where id = ?";
        //解读
        //因为返回的是一个对象，使用的Handler就是ScalarHandler
        Object obj = queryRunner.query(connection, sql, new ScalarHandler(), 4);
        System.out.println(obj);//周星驰

        //释放资源
        JDBCUtilsByDruid.close(null,null,connection);
    }



    @Test
    //演示 apache-dbutils + druid 完成dml(update,insert,delete)
    public void testDML() throws SQLException {
        //1.得到连接(druid)
        Connection connection = JDBCUtilsByDruid.getConnection();
        //2.使用DBUtils类和接口，先引入DBUtils的jar包，再加入到本Project
        //3.创建一个QueryRunner对象
        QueryRunner queryRunner = new QueryRunner();
        //4.这里组织sql完成update,insert,delete
        //String sql = "update actor set name = ? where id = ?";
        //String sql = "insert into actor values(null,?,?,?,?)";
        String sql = "delete from actor where id = ?";
        //解读
        //(1) 执行dml操作是 queryRunner.update()
        //(2) 返回的值是受影响的行数(affectedRow：受影响)
        int affectedRow = queryRunner.update(connection, sql, 4);
        System.out.println(affectedRow>0?"执行成功":"执行没有影响列表");

        //释放资源
        JDBCUtilsByDruid.close(null,null,connection);
    }

}
