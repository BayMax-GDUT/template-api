package com.cvaiedu.template.business.system.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

/**
 * 树形实体
 *
 * @author tangyi
 * @date 2018-09-13 20:40
 */
@Data
public abstract class TreeEntity<T> {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    protected Long id;
    /**
     * 父节点
     */
    @TableField(exist = false)
    protected T parent;

    /**
     * 父节点id
     */
    @NotNull(message = "父节点不能为空")
    protected Long parentId;

    /**
     * 子节点
     */
    @TableField(exist = false)
    protected List<TreeEntity> children = new ArrayList<>();

    public void add(TreeEntity node) {
        children.add(node);
    }
}

