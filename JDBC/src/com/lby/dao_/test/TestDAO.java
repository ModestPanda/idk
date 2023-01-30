package com.lby.dao_.test;

import com.lby.dao_.damain.Actor;
import com.lby.dao_.dao.ActorDAO;
import org.testng.annotations.Test;

import java.sql.SQLException;
import java.util.List;

/**
 * @author Humble
 * @version 1.0
 */
public class TestDAO {

    @Test
    //测试ActorDAO对actor表的crud操作
    public void testActorDAO() throws SQLException {

        ActorDAO actorDAO = new ActorDAO();
        //1.查询
        List<Actor> actors = actorDAO.queryMulti("select * from actor where id>= ?", Actor.class, 1);
        System.out.println("===查询结果===");
        for (Actor actor:actors){
            System.out.println(actor);
        }

        //2.查询单行记录
        Actor actor = actorDAO.querySingle("select * from actor where id = ?", Actor.class, 5);
        System.out.println("===查询单行结果===");
        System.out.println(actor);

        //3.查询单行单列
        Object o = actorDAO.queryScalar("select name from actor where id = ?", 5);
        System.out.println("===查询单行单列===");
        System.out.println(o);

        //4.dml操作   insert,update,delete
        int update = actorDAO.update("insert into actor values(null,?,?,?,?)", "张无忌", "男", "2000-10-10", "129");
        System.out.println(update>0?"执行成功":"执行没有影响到表");

    }
}
