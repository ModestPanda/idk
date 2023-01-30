package com.lby.jdbc.myjdbc;

public class OracleJDBCImpl implements JDBCInterface{
    @Override
    public Object getConnection() {
        System.out.println("得到oracle的连接");
        return null;
    }

    @Override
    public void crud() {
        System.out.println("完成对oracle的增删改查");
    }

    @Override
    public void close() {
        System.out.println("关闭oracle连接");
    }
}
