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
 *    (batch_task)
 * </p>
 *
 * @author   yww
 * @since   2023-05-28
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName("batch_task")
@Schema(name = "BatchTask")
public class BatchTask implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "ID")
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Integer id;

    @Schema(description = "任务名称")
    @TableField("name")
    private String name;

    @Schema(description = "批次ID")
    @TableField("batch_id")
    private Integer batchId;

    @Schema(description = "任务状态，0是未开始，1是执行中，2是完成，3是异常，4是暂停")
    @TableField("status")
    private Integer status;

}
