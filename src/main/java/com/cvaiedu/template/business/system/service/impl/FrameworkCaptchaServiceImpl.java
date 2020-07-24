/**
 * Copyright (c) 2016-2019 人人开源 All rights reserved.
 * <p>
 * https://www.renren.io
 * <p>
 * 版权所有，侵权必究！
 */

package com.cvaiedu.template.business.system.service.impl;

import com.cvaiedu.template.business.system.service.inter.FrameworkCaptchaService;
import com.cvaiedu.template.config.SystemParams;
import com.cvaiedu.template.exception.FrameworkException;
import com.cvaiedu.template.util.RedisKeys;
import com.cvaiedu.template.util.RedisUtils;
import com.google.code.kaptcha.Producer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.awt.image.BufferedImage;

/**
 * 验证码
 *
 * @author Mark sunlightcs@gmail.com
 */
@Service("frameworkCaptchaService")
public class FrameworkCaptchaServiceImpl implements FrameworkCaptchaService {
    @Resource
    private Producer producer;

    @Autowired
    private RedisUtils redisUtils;

    @Autowired
    private SystemParams systemParams;

    @Override
    public BufferedImage getCaptcha(String uuid) {
        if (StringUtils.isEmpty(uuid)) {
            throw new FrameworkException("uuid不能为空");
        }
        //生成文字验证码
        String code = producer.createText();
        String captchaKey = RedisKeys.getCaptchaKey(uuid);
        //一分钟过期
        redisUtils.set(captchaKey, code, 60);
        return producer.createImage(code);
    }

    @Override
    public boolean validate(String uuid, String code) {
        String captchaKey = RedisKeys.getCaptchaKey(uuid);
        String captchaCode = redisUtils.get(captchaKey);

        //测试环境用，设置一个超级验证码
        if (systemParams.getSuperCaptcha().equals(code)) {
            return true;
        }
        if (StringUtils.isEmpty(captchaCode)) {
            return false;
        }
        //删除验证码
        redisUtils.delete(captchaKey);
        if (captchaCode.equalsIgnoreCase(code)) {
            return true;
        }
        return false;
    }
}
