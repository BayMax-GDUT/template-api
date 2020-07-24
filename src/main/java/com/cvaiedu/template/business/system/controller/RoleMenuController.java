package com.cvaiedu.template.business.system.controller;

import com.alibaba.fastjson.JSONObject;
import com.cvaiedu.template.annotation.SysLog;
import com.cvaiedu.template.business.system.entity.RoleMenu;
import com.cvaiedu.template.business.system.service.inter.RoleMenuService;
import com.cvaiedu.template.constant.Constant;
import com.cvaiedu.template.result.Result;
import com.cvaiedu.template.result.ResultEnum;
import com.cvaiedu.template.util.PageUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
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
 * @date 2020-07-18 14:55:20
 */
@RestController
@RequestMapping("/v1/roleMenu")
public class RoleMenuController {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private RoleMenuService service;

    /**
     * 根据Id 查询
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/getByPrimaryKey")
    public Result getByPrimaryKey(@RequestParam Long id) {
        logger.info("根据Id查询 roleMenu，参数= {} ", JSONObject.toJSONString(id));
        return Result.ok(service.getByPrimaryKey(id));
    }

    /**
     * 分页查询
     *
     * @param queryMap
     * @return
     */
    @RequestMapping(value = "/findPage")
    public Result findPage(@RequestParam Map<String, Object> queryMap) {
        logger.info("查询 roleMenu 分页列表，参数= {} ", JSONObject.toJSONString(queryMap));
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
        logger.info("查询 roleMenu 列表，参数= {} ", JSONObject.toJSONString(queryMap));
        List<RoleMenu> record = service.list(queryMap);
        return Result.ok(record);
    }

    /**
     * 新增数据
     *
     * @param entity
     * @param bindingResult
     * @return
     */
    @RequestMapping(value = "/add")
    @SysLog(module = Constant.MODULE_ROLE_MENU, value = "新增角色菜单")
    public Result save(@RequestBody @Validated RoleMenu entity, BindingResult bindingResult) {
        logger.info("新增 roleMenu，参数= {} ", JSONObject.toJSONString(entity));
        if (bindingResult.hasErrors()) {
            logger.error("新增 roleMenu 参数不正确,异常={}", bindingResult.getFieldError().getDefaultMessage());
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
    @SysLog(module = Constant.MODULE_ROLE_MENU, value = "更新角色菜单")
    public Result updateById(@RequestBody RoleMenu entity) {
        logger.info("更新 roleMenu，参数= {} ", JSONObject.toJSONString(entity));
        service.updateById(entity);
        return Result.ok();
    }


    /**
     * 删除
     *
     * @param ids
     * @return
     */
    @RequestMapping(value = "/delete")
    @SysLog(module = Constant.MODULE_ROLE_MENU, value = "删除角色菜单")
    public Result deleteById(@RequestBody List<Long> ids) {
        logger.info("删除 roleMenu，参数= {} ", JSONObject.toJSONString(ids));
        service.deleteById(ids);
        return Result.ok();
    }


}

