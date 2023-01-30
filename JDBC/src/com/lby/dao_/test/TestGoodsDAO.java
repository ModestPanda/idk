package com.lby.dao_.test;

import com.lby.dao_.damain.Goods;
import com.lby.dao_.dao.GoodsDAO;
import org.testng.annotations.Test;

import java.sql.SQLException;
import java.util.List;

/**
 * @author Humble
 * @version 1.0
 */
public class TestGoodsDAO {

    @Test
    public void testGoodsDAO() throws SQLException {
        GoodsDAO goodsDAO = new GoodsDAO();
        List<Goods> goods1 = goodsDAO.queryMulti("select * from goods where id >= ?", Goods.class, 1);
        System.out.println("===查询多行结果===");
        for (Goods goods:goods1){
            System.out.println(goods);
        }

    }
}
