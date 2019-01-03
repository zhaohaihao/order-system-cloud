package com.hilox.order.service;

import com.hilox.order.exception.SellException;
import com.hilox.order.utils.KeyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * 模拟秒杀Service
 * Created by Hilox on 2019/1/2 0002.
 */
@Service
public class SecKillService {

    /**
     * 超时时间 10s
     */
    private static final int TIMEOUT = 10 * 1000;

    @Autowired
    private RedisLockService redisLockService;

    /**
     * 国庆活动，蛋炒饭特价，限量100000份
     */
    public static Map<String, Integer> products;
    public static Map<String, Integer> stock;
    public static Map<String, String> orders;

    static {
        /**
         * 模拟多个表，商品信息表，库存表，秒杀成功订单表
         */
        products = new HashMap<>();
        stock = new HashMap<>();
        orders = new HashMap<>();
        products.put("123456", 100000);
        stock.put("123456", 100000);
    }

    /**
     * 查询秒杀活动特价商品的信息
     * @param productId
     * @return
     */
    public String querySecKillProductInfo(String productId) {
        return queryMap(productId);
    }

    /**
     * 模拟不同用户秒杀同一商品的请求
     * @param productId
     */
    public /*synchronized*/ void orderProductMockDiffUser(String productId) {

        // 加锁
        long time = System.currentTimeMillis() + TIMEOUT;
        boolean isLock = redisLockService.lock(productId, String.valueOf(time));
        if (!isLock) {
            throw new SellException(101, "哎呦喂, 人也太多了, 换个姿势再试试~");
        }

        // 1.查询该商品库存，为0则活动结束。
        int stockNum = stock.get(productId);
        if (stockNum == 0) {
            throw new SellException(100, "活动结束");
        } else {
            // 2.下单(模拟不同用户openid不同)
            orders.put(KeyUtil.genUniqueKey(), productId);
            // 3.减库存
            stockNum = stockNum - 1;
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            stock.put(productId, stockNum);
        }

        // 解锁
        redisLockService.unlock(productId, String.valueOf(time));
    }

    /**
     * 查询秒杀活动特价商品的信息
     * @param productId
     * @return
     */
    private String queryMap(String productId) {
        return "国庆活动，蛋炒饭特价，限量份"
                + products.get(productId)
                + " 还剩：" + stock.get(productId) + " 份"
                + " 该商品成功下单用户数目："
                +  orders.size() +" 人";
    }
}
