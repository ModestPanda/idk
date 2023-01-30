package com.lby.jdbc.myjdbc;

public class MysqlJDBCImpl implements JDBCInterface{

    @Override
    public Object getConnection() {
        System.out.println("得到mysql得到连接");
        return null;
    }

    @Override
    public void crud() {
        System.out.println("完成mysql的增删改查");
    }

    @Override
    public void close() {
        System.out.println("关闭mysql连接");
    }
}
