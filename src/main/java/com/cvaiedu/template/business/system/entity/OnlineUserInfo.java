package com.cvaiedu.template.business.system.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@Accessors(chain = true)
public class OnlineUserInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private Long organizationId;

    private String organizationName;

    private Integer organizationType;

    private Integer accountCode;

    private String phone;

    /**
     * 头像
     */
    private String avatar;

    /**
     * 姓名
     */
    private String name;

}
