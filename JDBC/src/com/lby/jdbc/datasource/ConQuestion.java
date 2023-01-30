package com.lby.jdbc.datasource;

import com.lby.jdbc.utils.JDBCUtils;
import org.testng.annotations.Test;
import java.sql.Connection;

/**
 * @author Humble
 * @version 1.0
 */
public class ConQuestion {

    //传统方法连接mysql 5000次
    @Test
    public void testCon(){

        long start = System.currentTimeMillis();
        for(int i=0;i<5000;i++){
            //使用传统的jdbc方式，得到连接
            Connection connection = JDBCUtils.getConnection();
            //做一些工作，比如得到PreparedStatement，发送sql
            //..........
            //关闭
            JDBCUtils.close(null,null,connection);
        }
        long end = System.currentTimeMillis();
        System.out.println("传统方式5000次 耗时 = "+(end-start));//传统方式5000次 耗时 = 4818
    }
}
