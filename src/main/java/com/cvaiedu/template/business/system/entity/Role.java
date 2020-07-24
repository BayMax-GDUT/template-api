package com.cvaiedu.template.business.system.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableLogic;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * framework_role
 * @author 
 */
@Data
@Accessors(chain = true)
@NoArgsConstructor
@TableName("framework_role")
public class Role implements Serializable {
    private Long id;

    /**
     * 角色名
     */
    private String roleName;

    /**
     * 角色描述
     */
    private String roleDesc;

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

    @TableField(exist = false)
    private List<Menu> menuList;
}