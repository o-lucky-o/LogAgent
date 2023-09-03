/*
 * Copyright (c) Huawei Technologies Co., Ltd. 2021-2021. All rights reserved.
 */
package rbt.log.agent.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

/**
 * Entity for error_log: 后端接受的实体类
 *
 * @author c50033056
 * @since 2023-08-29
 */
@Data
@Builder
@AllArgsConstructor
public class ErrorLog {
    /**
     * 批次
     */
    private String batchId;

    /**
     * 文件名
     */
    private String file;

    /**
     * 错误日志时间
     */
    private String dataTime;

    /**
     * 级别： 0：ERROR 1:WARNING 2：
     */
    private int logLevel;

    /**
     * 线程
     */
    private String thread;

    /**
     * 类和方法
     */
    private String classAndMethod;

    /**
     * 日志ID
     */
    private String logId;

    /**
     * 事务ID
     */
    private String transanctionId;

    /**
     * 详情
     */
    private String message;

    /**
     * 状态：0：未确认 1：确认没问题（白名单） 2：确认有问题
     */
    private Integer status;

    /**
     * 确认人：工号+姓名
     */
    private String confirmedBy;
}