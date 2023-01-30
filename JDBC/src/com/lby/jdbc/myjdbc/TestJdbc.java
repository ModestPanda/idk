package com.lby.jdbc.myjdbc;

public class TestJdbc {
    public static void main(String[] args) {
//        完成对mysql的操作
        JDBCInterface jdbcInterface=new MysqlJDBCImpl();
        jdbcInterface.getConnection();//通过接口来调用实现类[动态绑定]
        jdbcInterface.crud();
        jdbcInterface.close();

//        完成对oracle的操作
        System.out.println("======================");
        jdbcInterface=new OracleJDBCImpl();
        jdbcInterface.getConnection();//通过接口来调用实现类[动态绑定]
        jdbcInterface.crud();
        jdbcInterface.close();
    }
}
