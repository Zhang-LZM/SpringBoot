package com.zem.reggie.controller;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
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
         * 用户提交订单评论
         */
        @PostMapping
        public R<String> submitComment(@RequestBody OrderComment orderComment) {
            orderCommentService.save(orderComment);
            return R.success("评论提交成功");
        }

        /**
         * 管理端查看订单评论
         */
        @GetMapping("/{orderNumber}")
        public R<OrderComment> getCommentByOrderNumber(@PathVariable String orderNumber) {
            log.info("收到订单评论查询请求，订单号：{}", orderNumber);
            OrderComment orderComment = orderCommentService.getOne(new LambdaQueryWrapper<OrderComment>().eq(OrderComment::getOrderNumber, orderNumber));
            return R.success(orderComment);
        }

        /**
         * 管理端删除订单评论
         */
        @DeleteMapping("/{orderNumber}")
        public R<String> deleteCommentByOrderNumber(@PathVariable String orderNumber) {
            orderCommentService.remove(new LambdaQueryWrapper<OrderComment>().eq(OrderComment::getOrderNumber, orderNumber));
            return R.success("评论删除成功");
        }

        /**
         * 管理端回复订单评论
         */
        @PutMapping("/reply/{orderNumber}")
        public R<String> replyComment(@PathVariable String orderNumber, @RequestBody String reply) {
            OrderComment orderComment = orderCommentService.getOne(new LambdaQueryWrapper<OrderComment>().eq(OrderComment::getOrderNumber, orderNumber));
            if (orderComment != null) {
                orderComment.setReply(reply);
                orderCommentService.updateById(orderComment);
                return R.success("评论回复成功");
            }
            return R.error("评论不存在");
        }

        /**
         * 用户端修改订单评论
         */
        @PutMapping("/{orderNumber}")
        public R<String> updateComment(@PathVariable String orderNumber, @RequestBody OrderComment orderComment) {
            OrderComment existingComment = orderCommentService.getOne(new LambdaQueryWrapper<OrderComment>().eq(OrderComment::getOrderNumber, orderNumber));
            if (existingComment != null) {
                existingComment.setContent(orderComment.getContent());
                orderCommentService.updateById(existingComment);
                return R.success("评论修改成功");
            }
            return R.error("评论不存在");
        }

        /**
         * 用户端删除订单评论
         */
        @DeleteMapping("/user/{orderNumber}")
        public R<String> userDeleteComment(@PathVariable String orderNumber) {
            orderCommentService.remove(new LambdaQueryWrapper<OrderComment>().eq(OrderComment::getOrderNumber, orderNumber));
            return R.success("评论删除成功");
        }

        /**
         * 用户端回复订单评论
         */
        @PutMapping("/user/reply/{orderNumber}")
        public R<String> userReplyComment(@PathVariable String orderNumber, @RequestBody String reply) {
            OrderComment orderComment = orderCommentService.getOne(new LambdaQueryWrapper<OrderComment>().eq(OrderComment::getOrderNumber, orderNumber));
            if (orderComment != null) {
                orderComment.setReply(reply);
                orderCommentService.updateById(orderComment);
                return R.success("评论回复成功");
            }
            return R.error("评论不存在");
        }
}


