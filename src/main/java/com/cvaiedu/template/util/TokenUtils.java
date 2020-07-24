
package com.cvaiedu.template.util;

import cn.hutool.core.util.IdUtil;
import com.cvaiedu.template.business.system.entity.Account;
import com.cvaiedu.template.business.system.entity.OnlineUserInfo;
import com.cvaiedu.template.result.ResultEnum;
import com.cvaiedu.template.constant.Constant;
import com.cvaiedu.template.exception.FrameworkException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;


/**
 * token 工具类，从上下文中获取token
 *
 * @author hasee
 */
@Component
public class TokenUtils {

    @Autowired
    RedisUtils redisUtils;

    private Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * 生成token并存入redis
     *
     * @return
     */
    public String createToken() {
        return IdUtil.randomUUID();
    }

    /**
     * 从request中获取token，并获取用户信息
     *
     * @return
     */
    public String getToken() {

        String strToken = null;
        try {
            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
                    .getRequest();
            strToken = request.getHeader(Constant.TOKEN);
            if (StringUtils.isEmpty(strToken)) {
                strToken = request.getParameter(Constant.TOKEN);
            }
        } catch (Exception e) {
            logger.error("获取token失败");
            e.printStackTrace();
        }
        return strToken;
    }

    /**
     * 获取在线用户信息
     *
     * @return
     */
    public OnlineUserInfo getOnlineUser() {
        String token = this.getToken();
        if (StringUtils.isEmpty(token)) {
            throw new FrameworkException(ResultEnum.ERROR_401);
        }
        String onlineUserKey = RedisKeys.getOnlineUserKey(token);
        OnlineUserInfo onlineUserEntity = redisUtils.get(onlineUserKey, OnlineUserInfo.class, RedisUtils.DEFAULT_EXPIRE);
        if (onlineUserEntity == null) {
            throw new FrameworkException(ResultEnum.ERROR_401);
        }
        return onlineUserEntity;
    }


    /**
     * 设置在线用户信息
     *
     * @return
     */
    public OnlineUserInfo setOnlineUser(String token, Account account) {
        String onlineUserKey = RedisKeys.getOnlineUserKey(token);
        OnlineUserInfo onlineUserInfo = new OnlineUserInfo();
        onlineUserInfo.setId(account.getId());
        onlineUserInfo.setOrganizationType(Constant.ADMIN_ORGANIZATION_TYPE);
        onlineUserInfo.setOrganizationName(Constant.ADMIN_ORGANIZATION_NAME);
        onlineUserInfo.setOrganizationId(Constant.ADMIN_ORGANIZATION_ID);
        onlineUserInfo.setAccountCode(account.getAccountCode());
        onlineUserInfo.setAvatar(account.getAvatar());
        onlineUserInfo.setName(account.getRealName());
        onlineUserInfo.setPhone(account.getPhone());
        redisUtils.set(onlineUserKey, onlineUserInfo, RedisUtils.DEFAULT_EXPIRE);
        return onlineUserInfo;
    }


    /**
     * 删除用户token
     *
     * @return
     */
    public void deleteUser() {
        String token = this.getToken();
        if (StringUtils.isEmpty(token)) {
            return;
        }
        String frameworkAccountKey = RedisKeys.getOnlineUserKey(token);
        redisUtils.delete(frameworkAccountKey);
    }

}
