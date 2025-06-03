package com.zem.reggie.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;
import java.io.Serializable;

@Data
@TableName("order_comment")
public class OrderComment implements Serializable{
    @TableId
    private Long id;
    private Long orderId;
    private Long userId;
    private String content;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
    private String reply; // 回复内容
    private String replyContent;
    private LocalDateTime replyTime;
    private Integer isDeleted;

    // 关联字段，用于前端显示
    @TableField(exist = false)
    private String orderNumber;
    @TableField(exist = false)
    private String userName;
}