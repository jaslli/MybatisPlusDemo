package com.yww.demo.entity;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * (user)用户信息实体类
 * </p>
 *
 * @Author yww
 * @Date 2022-10-21
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName("user")
@Schema(name = "User", description = "用户信息实体类")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "数据ID")
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private String id;

    @Schema(description = "用户名")
    @TableField("username")
    private String username;

    @Schema(description = "密码")
    @TableField("password")
    private String password;

    @Schema(description = "用户昵称")
    @TableField("nickname")
    private String nickname;

    @Schema(description = "用户头像地址")
    @TableField("avatar")
    private String avatar;

    @Schema(description = "用户邮箱地址")
    @TableField("email")
    private String email;

    @Schema(description = "性别,0是女|1是男")
    @TableField("sex")
    private Boolean sex;

    @Schema(description = "电话号码")
    @TableField("phone")
    private String phone;

    @Schema(description = "账号状态,0禁用|1正常")
    @TableField("status")
    private Boolean status;

    @Schema(description = "创建时间")
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @Schema(description = "创建人")
    @TableField(value = "create_by", fill = FieldFill.INSERT)
    private String createBy;

    @Schema(description = "更新时间")
    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    @Schema(description = "更新人")
    @TableField(value = "update_by", fill = FieldFill.INSERT_UPDATE)
    private String updateBy;

}
