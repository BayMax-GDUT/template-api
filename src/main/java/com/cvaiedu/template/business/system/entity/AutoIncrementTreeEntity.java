package com.cvaiedu.template.business.system.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

/**
 * 因为部门的id为自增，所以需要单独处理
 *
 * @author tangyi
 * @date 2018-09-13 20:40
 */
@Data
@Accessors(chain = true)
@NoArgsConstructor
public abstract class AutoIncrementTreeEntity<T> {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId(value = "id", type = IdType.AUTO)
    protected Integer id;
    /**
     * 父节点
     */
    @TableField(exist = false)
    protected T parent;

    /**
     * 父节点id
     */
    @NotNull(message = "上级不能为空")
    protected Integer parentId;

    /**
     * 子节点
     */
    @TableField(exist = false)
    protected List<AutoIncrementTreeEntity> children = new ArrayList<>();

    public void add(AutoIncrementTreeEntity node) {
        children.add(node);
    }
}

