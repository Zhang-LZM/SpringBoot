package com.zem.reggie.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zem.reggie.entity.OrderComment;
import com.zem.reggie.common.R;

import java.util.List;

public interface OrderCommentService extends IService<OrderComment> {
    /**
     * 用户提交评论
     */
    R<String> submitComment(OrderComment orderComment);

    /**
     * 用户查看自己的评论
     */
    R<List<OrderComment>> getUserComments(Long userId);

    /**
     * 用户修改自己的评论
     */
    R<String> updateUserComment(OrderComment orderComment);

    /**
     * 用户删除自己的评论
     */
    R<String> deleteUserComment(Long commentId);

    /**
     * 管理端分页查询所有评论
     */
    R<Page<OrderComment>> getPage(int page, int pageSize);

    /**
     * 管理端删除评论
     */
    R<String> deleteComment(Long commentId);

    /**
     * 管理端回复评论
     */
    R<String> replyComment(Long commentId, String replyContent);
}