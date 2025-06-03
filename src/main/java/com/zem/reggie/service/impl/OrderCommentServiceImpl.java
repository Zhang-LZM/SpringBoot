package com.zem.reggie.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zem.reggie.common.BaseContext;
import com.zem.reggie.common.R;
import com.zem.reggie.entity.OrderComment;
import com.zem.reggie.entity.User;
import com.zem.reggie.mapper.OrderCommentMapper;
import com.zem.reggie.service.OrderCommentService;
import com.zem.reggie.service.OrderService;
import com.zem.reggie.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;


import com.zem.reggie.entity.Orders; // 导入Order类

@Service
public class OrderCommentServiceImpl extends ServiceImpl<OrderCommentMapper, OrderComment> implements OrderCommentService {

    @Autowired
    private OrderService orderService;

    @Autowired
    private UserService userService;

    @Override
    @Transactional
    public R<String> submitComment(OrderComment orderComment) {
        // 检查订单是否存在且状态为已完成
        Orders order = orderService.getById(orderComment.getOrderId());
        if (order == null || order.getStatus() != 4) {
            return R.error("订单不存在或未完成，不能评论");
        }

        // 检查用户是否已评论过该订单
        LambdaQueryWrapper<OrderComment> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(OrderComment::getOrderId, orderComment.getOrderId())
                .eq(OrderComment::getUserId, orderComment.getUserId())
                .eq(OrderComment::getIsDeleted, 0);
        if (this.count(queryWrapper) > 0) {
            return R.error("您已评论过该订单，不能重复评论");
        }

        // 设置评论时间
        orderComment.setCreateTime(LocalDateTime.now());
        orderComment.setIsDeleted(0);

        this.save(orderComment);
        return R.success("评论提交成功");
    }

    @Override
    public R<List<OrderComment>> getUserComments(Long userId) {
        LambdaQueryWrapper<OrderComment> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(OrderComment::getUserId, userId)
                .eq(OrderComment::getIsDeleted, 0)
                .orderByDesc(OrderComment::getCreateTime);

        List<OrderComment> comments = this.list(queryWrapper);

        // 关联订单编号和用户名
        for (OrderComment comment : comments) {
            Orders order = orderService.getById(comment.getOrderId());
            if (order != null) {
                comment.setOrderNumber(order.getNumber());
            }

            User user = userService.getById(comment.getUserId());
            if (user != null) {
                comment.setUserName(user.getName());
            }
        }

        return R.success(comments);
    }

    @Override
    @Transactional
    public R<String> updateUserComment(OrderComment orderComment) {
        // 检查评论是否存在且属于当前用户
        OrderComment comment = this.getById(orderComment.getId());
        if (comment == null || !comment.getUserId().equals(orderComment.getUserId()) || comment.getIsDeleted() == 1) {
            return R.error("评论不存在或无权限修改");
        }

        // 更新评论内容和时间
        comment.setContent(orderComment.getContent());
        comment.setUpdateTime(LocalDateTime.now());

        this.updateById(comment);
        return R.success("评论修改成功");
    }

    @Override
    @Transactional
    public R<String> deleteUserComment(Long commentId) {
        // 检查评论是否存在且属于当前用户
        OrderComment comment = this.getById(commentId);
        if (comment == null || comment.getIsDeleted() == 1) {
            return R.error("评论不存在");
        }

        // 检查是否是当前用户的评论
        Long currentUserId = BaseContext.getCurrentId();
        if (!comment.getUserId().equals(currentUserId)) {
            return R.error("无权限删除此评论");
        }

        // 逻辑删除评论
        comment.setIsDeleted(1);
        this.updateById(comment);
        return R.success("评论删除成功");
    }

    @Override
    public R<Page<OrderComment>> getPage(int page, int pageSize) {
        // 分页查询
        Page<OrderComment> pageInfo = new Page<>(page, pageSize);

        LambdaQueryWrapper<OrderComment> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(OrderComment::getIsDeleted, 0)
                .orderByDesc(OrderComment::getCreateTime);

        this.page(pageInfo, queryWrapper);

        // 关联订单编号和用户名
        for (OrderComment comment : pageInfo.getRecords()) {
            Orders order = orderService.getById(comment.getOrderId());
            if (order != null) {
                comment.setOrderNumber(order.getNumber());
            }

            User user = userService.getById(comment.getUserId());
            if (user != null) {
                comment.setUserName(user.getName());
            }
        }

        return R.success(pageInfo);
    }

    @Override
    @Transactional
    public R<String> deleteComment(Long commentId) {
        // 检查评论是否存在
        OrderComment comment = this.getById(commentId);
        if (comment == null || comment.getIsDeleted() == 1) {
            return R.error("评论不存在");
        }

        // 逻辑删除评论
        comment.setIsDeleted(1);
        this.updateById(comment);
        return R.success("评论删除成功");
    }

    @Override
    @Transactional
    public R<String> replyComment(Long commentId, String replyContent) {
        // 检查评论是否存在
        OrderComment comment = this.getById(commentId);
        if (comment == null || comment.getIsDeleted() == 1) {
            return R.error("评论不存在");
        }

        // 设置回复内容和时间
        comment.setReplyContent(replyContent);
        comment.setReplyTime(LocalDateTime.now());

        this.updateById(comment);
        return R.success("评论回复成功");
    }
}