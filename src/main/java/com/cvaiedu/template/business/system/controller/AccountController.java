package com.cvaiedu.template.business.system.controller;

import com.alibaba.fastjson.JSONObject;
import com.cvaiedu.template.business.system.service.inter.AccountService;
import com.cvaiedu.template.annotation.SysLog;
import com.cvaiedu.template.constant.Constant;
import com.cvaiedu.template.exception.FrameworkException;
import com.cvaiedu.template.result.Result;
import com.cvaiedu.template.result.ResultEnum;
import com.cvaiedu.template.business.system.entity.Account;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import com.cvaiedu.template.util.PageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.Map;


/**
 * 控制层
 * 通过拦截器，或者AOP的方式，处理异常信息
 *
 * @author code-generator
 * @date 2020-07-18 14:53:12
 */
@RestController
@RequestMapping("/v1/account")
public class AccountController {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private AccountService service;

    /**
     * 根据Id 查询
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/getByPrimaryKey")
    public Result getByPrimaryKey(@RequestParam Long id) {
		logger.info("根据Id查询 account，参数= {} ", JSONObject.toJSONString(id));
		return Result.ok(service.getByPrimaryKey(id));
    }

    /**
     * 根据accountCode查询账号
     * @param accountCode
     * @return
     */
    @RequestMapping(value = "/getByAccountCode")
    public Result getByAccountCode(@RequestParam Integer accountCode) {
        logger.info("根据accountCode 查询 account， 参数 = {}", JSONObject.toJSONString(accountCode));
        if (accountCode == null || accountCode < 1) {
            throw new FrameworkException(ResultEnum.ERROR_400);
        }
        return Result.ok(service.selectOne(new Account().setAccountCode(accountCode)));
    }

    /**
     * 分页查询
     *
     * @param queryMap
     * @return
     */
    @RequestMapping(value = "/findPage")
    public Result findPage(@RequestParam Map<String, Object> queryMap) {
        logger.info("查询 account 分页列表，参数= {} ", JSONObject.toJSONString(queryMap));
        PageUtils record = service.findPage(queryMap);
        return Result.ok(record);
    }
        
	/**
	 * 列表查询
     *
	 * @param queryMap
	 * @return
	 */
	@RequestMapping(value = "/list")
	public Result list(@RequestParam Map<String, Object> queryMap) {
		logger.info("查询 account 列表，参数= {} ", JSONObject.toJSONString(queryMap));
        List<Account> record = service.list(queryMap);
        return Result.ok(record);
	}

    /**
     * 根据角色id查询账号列表
     * @param roleId
     * @return
     */
	public Result getByRoleId(@RequestParam Long roleId) {
        logger.info("根据角色id查询账号列表， 参数 = {}", JSONObject.toJSONString(roleId));
        if (roleId == null || roleId < 1) {
            throw new FrameworkException(ResultEnum.ERROR_400);
        }
        return Result.ok(service.getByRoleId(roleId));
    }
    
    /**
     * 新增数据
     *
     * @param entity
     * @param bindingResult
     * @return
     */
    @RequestMapping(value = "/add")
	@SysLog(module = Constant.MODULE_ACCOUNT, value = "新增账号")
    public Result save(@RequestBody @Validated Account entity, BindingResult bindingResult) {
		logger.info("新增 account，参数= {} ", JSONObject.toJSONString(entity));
		if (bindingResult.hasErrors()) {
 	       logger.error("新增 account 参数不正确,异常={}", bindingResult.getFieldError().getDefaultMessage());
 	       return Result.error(ResultEnum.ERROR_400.getCode(), bindingResult.getFieldError().getDefaultMessage());
 	    }
        service.save(entity);
        return Result.ok();
    }
	
	/**
     * 更新
     *
     * @param entity
     * @return
     */
    @RequestMapping(value = "/update")
	@SysLog(module = Constant.MODULE_ACCOUNT, value = "更新账号")
    public Result updateById(@RequestBody Account entity){
		logger.info("更新 account，参数= {} ", JSONObject.toJSONString(entity));
    	service.updateById(entity);
        return Result.ok();
    }
    

    /**
     * 删除
     *
     * @param ids
     * @return
     */
    @RequestMapping(value="/delete")
	@SysLog(module = Constant.MODULE_ACCOUNT, value = "删除用户")
    public Result deleteById(@RequestBody List<Long> ids) {
		logger.info("删除 account，参数= {} ", JSONObject.toJSONString(ids));
        service.deleteById(ids);
        return Result.ok();
    }
     
    
}

