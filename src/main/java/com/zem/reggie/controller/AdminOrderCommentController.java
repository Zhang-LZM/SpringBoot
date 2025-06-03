package com.zem.reggie.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zem.reggie.common.BaseContext;
import com.zem.reggie.common.R;
import com.zem.reggie.entity.OrderComment;
import com.zem.reggie.service.OrderCommentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 管理端评论控制器
 */
@Slf4j
@RestController
@RequestMapping("/admin/orderComment")
public class AdminOrderCommentController {

    @Autowired
    private OrderCommentService orderCommentService;

    /**
     * 管理端分页查询所有评论
     */
    @GetMapping("/list")
    public R<Page<OrderComment>> getPage(@RequestParam(defaultValue = "1") int page,
                                         @RequestParam(defaultValue = "10") int pageSize) {
        return orderCommentService.getPage(page, pageSize);
    }

    /**
     * 管理端删除评论
     */
    @DeleteMapping("/{commentId}")
    public R<String> deleteComment(@PathVariable Long commentId) {
        return orderCommentService.deleteComment(commentId);
    }

    /**
     * 管理端回复评论
     */
    @PostMapping("/reply/{commentId}")
    public R<String> replyComment(@PathVariable Long commentId,
                                  @RequestBody String replyContent) {
        return orderCommentService.replyComment(commentId, replyContent);
    }
}
