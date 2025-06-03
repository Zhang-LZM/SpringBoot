package com.zem.reggie.controller;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.zem.reggie.common.R;
import com.zem.reggie.entity.OrderComment;
import com.zem.reggie.service.OrderCommentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
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
    @GetMapping("/{orderId}")
    public R<OrderComment> getCommentByOrderId(@PathVariable Long orderId) {
        log.info("收到订单评论查询请求，订单ID：{}", orderId);
        OrderComment orderComment = orderCommentService.getOne(new LambdaQueryWrapper<OrderComment>().eq(OrderComment::getOrderId, orderId));
        return R.success(orderComment);
    }
}