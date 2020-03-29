package cn.com.djin.ssm.service;

import cn.com.djin.ssm.entity.Orders;

/**
 *   订单业务层接口
 */
public interface OrdersService extends BaseService<Orders> {
    public String payOrdersAfter(Orders orders) throws Exception;
}
