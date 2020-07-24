package com.cvaiedu.template.business.system.entity;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotations.TableLogic;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * framework_account
 * @author 
 */
@Data
@Accessors(chain = true)
@NoArgsConstructor
@TableName("framework_account")
public class Account implements Serializable {
    private Long id;

    /**
     * 用户账号
     */
    private Integer accountCode;

    /**
     * 用户密码
     */
    private String password;

    /**
     * 用户显示名
     */
    private String realName;

    /**
     * 用户显示头像
     */
    private String avatar;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 0 已删除 1正常
     */
    @TableLogic
    private Integer delFlag;

    private static final long serialVersionUID = 1L;

}