package com.cvaiedu.template.business.system.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.cvaiedu.template.annotation.AuthIgnore;
import com.cvaiedu.template.business.system.dto.LoginDTO;
import com.cvaiedu.template.business.system.entity.Account;
import com.cvaiedu.template.business.system.entity.Menu;
import com.cvaiedu.template.business.system.entity.OnlineUserInfo;
import com.cvaiedu.template.business.system.entity.Role;
import com.cvaiedu.template.business.system.service.inter.AccountService;
import com.cvaiedu.template.business.system.service.inter.FrameworkCaptchaService;
import com.cvaiedu.template.business.system.service.inter.MenuService;
import com.cvaiedu.template.business.system.service.inter.RoleService;
import com.cvaiedu.template.constant.Constant;
import com.cvaiedu.template.exception.FrameworkException;
import com.cvaiedu.template.result.Result;
import com.cvaiedu.template.result.ResultEnum;
import com.cvaiedu.template.util.RedisUtils;
import com.cvaiedu.template.util.TokenUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import sun.misc.BASE64Encoder;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.*;

@Slf4j
@RestController
@RequestMapping("/v1/auth")
public class LoginController {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private AccountService accountService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private FrameworkCaptchaService frameworkCaptchaService;

    @Autowired
    private MenuService menuService;

    @Autowired
    RedisUtils redisUtils;

    @Autowired
    TokenUtils tokenUtils;

    /**
     * 验证码
     */
    @GetMapping("/captcha")
    @AuthIgnore
    public Result captcha(@RequestParam String uuid) throws IOException {
        //获取图片验证码
        if (uuid == null || StringUtils.isEmpty(uuid)) {
            throw new FrameworkException(ResultEnum.ERROR_400002);
        }
        BufferedImage image = frameworkCaptchaService.getCaptcha(uuid);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();//io流
        ImageIO.write(image, "png", baos);//写入流中
        byte[] bytes = baos.toByteArray();//转换成字节
        BASE64Encoder encoder = new BASE64Encoder();
        String png_base64 = encoder.encodeBuffer(bytes).trim();//转换成base64串
        png_base64 = png_base64.replaceAll("\n", "").replaceAll("\r", "");//删除 \r\n
        Map<String, Object> hashMap = new HashMap<>();
        hashMap.put("image", "data:image/png;base64," + png_base64);
        return Result.ok(hashMap);
    }

    /**
     * 登录
     */
    @AuthIgnore
    @PostMapping("/login")
    public Result login(@RequestBody LoginDTO frameworkLoginForm) {
        logger.info("用户登录，参数 = {}", JSONObject.toJSONString(frameworkLoginForm));
        boolean captcha = frameworkCaptchaService.validate(frameworkLoginForm.getUuid(), frameworkLoginForm.getCaptcha());
        if (!captcha) {
            throw new FrameworkException(ResultEnum.ERROR_400002);
        }
        //根据accountCode查询
        Account user = accountService.selectOne(new Account().setAccountCode(frameworkLoginForm.getAccountCode()));
        // 账号不存在
        if (user == null) {
            throw new FrameworkException(ResultEnum.ERROR_400001);
        }
        // 密码错误
        if (!user.getPassword().equals(DigestUtils.md5Hex(frameworkLoginForm.getPassword() + Constant.SALT))) {
            throw new FrameworkException(ResultEnum.ERROR_400001);
        }
        // 没有绑定角色 不能登录
        List<Role> byAccountCode = roleService.getByAccountCode(user.getAccountCode());
        if (byAccountCode.size() < 1) {
            throw new FrameworkException(ResultEnum.ERROR_403001);
        }
        //生成token并存入redis
        String token = tokenUtils.createToken();
        tokenUtils.setOnlineUser(token, user);
        // 返回数据
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("token", token);
        return Result.ok(jsonObject);
    }

    /**
     * 获取用户信息,同时也是进入管理功能的入口
     * <p>
     *
     * @return
     */
    @GetMapping(value = "/getUserInfo")
    public Result getUserInfo() {
        logger.info("获取用户信息");
        JSONObject jsonObj;
        Integer accountCode = tokenUtils.getOnlineUser().getAccountCode();
        Account account = accountService.selectOne(new Account().setAccountCode(accountCode));
        //查询原本信息
        //更新在线用户信息
        OnlineUserInfo onlineUserInfo = tokenUtils.setOnlineUser(tokenUtils.getToken(), account);
        //返回用户信息
        jsonObj = (JSONObject) JSON.toJSON(onlineUserInfo);
        //处理perms
        List<Role> roles = roleService.getByAccountCode(account.getAccountCode());
        Set<String> perms = new HashSet<>();
        for (Role role : roles) {
            List<Menu> list = menuService.getByRoleId(role.getId());
            for (Menu menu : list) {
                perms.add(menu.getPerms());
            }
        }
        jsonObj.put("perms", perms);
        return Result.ok(jsonObj);
    }

    /**
     * 退出
     *
     * @return
     */
    @RequestMapping("/logout")
    public Result Logout() {
        tokenUtils.deleteUser();
        return Result.ok();
    }
}
