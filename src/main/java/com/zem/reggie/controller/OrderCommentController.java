package com.zem.reggie.controller;

import com.zem.reggie.common.BaseContext;
import com.zem.reggie.common.R;
import com.zem.reggie.entity.OrderComment;
import com.zem.reggie.service.OrderCommentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/orderComment")
public class OrderCommentController {

    @Autowired
    private OrderCommentService orderCommentService;

    /**
     * 用户提交评论
     */
    @PostMapping
    public R<String> submitComment(@RequestBody OrderComment orderComment) {
        // 设置当前用户ID
        Long userId = BaseContext.getCurrentId();
        orderComment.setUserId(userId);

        return orderCommentService.submitComment(orderComment);
    }

    /**
     * 用户查看自己的评论
     */
    @GetMapping("/user")
    public R<List<OrderComment>> getUserComments() {
        Long userId = BaseContext.getCurrentId();
        return orderCommentService.getUserComments(userId);
    }

    /**
     * 用户修改自己的评论
     */
    @PutMapping
    public R<String> updateUserComment(@RequestBody OrderComment orderComment) {
        // 设置当前用户ID
        Long userId = BaseContext.getCurrentId();
        orderComment.setUserId(userId);

        return orderCommentService.updateUserComment(orderComment);
    }

    /**
     * 用户删除自己的评论
     */
    @DeleteMapping("/{commentId}")
    public R<String> deleteUserComment(@PathVariable Long commentId) {
        return orderCommentService.deleteUserComment(commentId);
    }
}

