package com.zem.reggie.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zem.reggie.entity.OrderComment;
import com.zem.reggie.mapper.OrderCommentMapper;
import com.zem.reggie.service.OrderCommentService;
import org.springframework.stereotype.Service;

@Service
public class OrderCommentServiceImpl extends ServiceImpl<OrderCommentMapper, OrderComment> implements OrderCommentService {
}