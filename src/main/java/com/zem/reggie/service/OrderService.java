package com.zem.reggie.service;
/*
    mybatisplus管得很宽，明明是持久层框架，但是他把service也给管了
 */
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zem.reggie.dto.OrdersDto;
import com.zem.reggie.entity.Orders;

public interface OrderService extends IService<Orders> {

    void submit(Orders orders);

    Page<OrdersDto> pageOrdersAndOrdersDetail(Integer page, Integer pageSize);

    void dispatchOrder(Long orderId);

    void completeOrder(Long orderId);
}