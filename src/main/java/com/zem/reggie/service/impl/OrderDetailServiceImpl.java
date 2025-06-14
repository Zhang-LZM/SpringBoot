package com.zem.reggie.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zem.reggie.entity.OrderDetail;
import com.zem.reggie.mapper.OrderDetailMapper;
import com.zem.reggie.service.OrderDetailService;
import org.springframework.stereotype.Service;

/*
    这个是一个Employee模块的service实现类，这个类可以调用EmployeeMapper 完成对数据库增删改查
 */
@Service//在服务器启动的时候，就创建service对象，并且放入容器中，其他地方要使用service只需要从容器中注入即可
public class OrderDetailServiceImpl extends ServiceImpl<OrderDetailMapper, OrderDetail> implements OrderDetailService {
}
