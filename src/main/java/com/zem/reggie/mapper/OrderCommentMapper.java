package com.zem.reggie.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zem.reggie.entity.OrderComment;
import org.apache.ibatis.annotations.Mapper;

/**
 * 订单评论数据访问层
 */
@Mapper
public interface OrderCommentMapper extends BaseMapper<OrderComment> {
    // 由于继承了BaseMapper，已经拥有了基本的CRUD方法
    // 如果需要自定义SQL查询，可以在这里添加方法声明
}