package com.cvaiedu.template.business.system.entity;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * framework_role_account
 * @author 
 */
@Data
@Accessors(chain = true)
@NoArgsConstructor
@TableName("framework_role_account")
public class RoleAccount implements Serializable {
    private Long id;

    /**
     * 账号
     */
    private Integer accountCode;

    /**
     * 角色ID
     */
    private Long roleId;

    /**
     * 创建时间
     */
    private Date createTime;

    private static final long serialVersionUID = 1L;
}