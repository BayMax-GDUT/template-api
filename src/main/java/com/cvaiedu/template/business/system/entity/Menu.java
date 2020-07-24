package com.cvaiedu.template.business.system.entity;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotations.TableLogic;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * framework_menu
 * @author 
 */
@Data
@Accessors(chain = true)
@NoArgsConstructor
@TableName("framework_menu")
public class Menu extends TreeEntity<Menu> implements Serializable {

    /**
     * 菜单名称
     */
    private String menuName;

    /**
     * 菜单类型（1菜单 2按钮）
     */
    private Integer menuType;

    /**
     * 权限标识
     */
    private String perms;

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