/**
 * Copyright (c) 2016-2019 人人开源 All rights reserved.
 * <p>
 * https://www.renren.io
 * <p>
 * 版权所有，侵权必究！
 */

package com.cvaiedu.template.business.system.dto;

import lombok.Data;

/**
 * 登录表单
 *
 * @author Mark sunlightcs@gmail.com
 */
@Data
public class LoginDTO {
    private Integer accountCode;
    private String password;
    private String captcha;
    private String uuid;


}
