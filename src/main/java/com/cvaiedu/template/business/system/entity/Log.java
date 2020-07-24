package com.cvaiedu.template.business.system.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * jg_log
 *
 * @author
 */
@Data
@TableName("framework_log")
@Accessors(chain = true)
@NoArgsConstructor
public class Log implements Serializable {
    /**
     * 主键
     */
    private Long id;

    /**
     * 所属机构id
     */
    private Long cjyOrganizationId;

    /**
     * 用户账号
     */
    private Integer accountCode;

    /**
     * 用户姓名
     */
    private String realName;

    /**
     * 日志类型
     */
    private String type;

    /**
     * 日志标题
     */
    private String operation;

    /**
     * 操作用户的IP地址
     */
    private String ip;

    /**
     * 操作用户代理信息
     */
    private String userAgent;

    /**
     * 操作的URI
     */
    private String requestUri;

    /**
     * 操作的方式
     */
    private String method;

    /**
     * 操作提交的数据
     */
    private String params;

    /**
     * 耗时
     */
    private String time;

    /**
     * 业务模块
     */
    private String module;

    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    /**
     * 搜索条件，范围开始时间
     */
    @TableField(exist = false)
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private List<Date> beginTime;

    /**
     * 搜索条件，范围结束时间
     */
    @TableField(exist = false)
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private List<Date> endTime;

    private static final long serialVersionUID = 1L;
}