package com.yww.demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * <p>
 *    (batch)
 * </p>
 *
 * @author   yww
 * @since   2023-05-28
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName("batch")
@Schema(name = "Batch")
public class Batch implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @Schema(description = "批次名称")
    @TableField("name")
    private String name;

    @Schema(description = "批次状态，0是未开始，1是执行中，2是完成，3是异常，4是暂停")
    @TableField("status")
    private Integer status;

}
