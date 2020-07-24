package com.cvaiedu.template.business.system.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.cvaiedu.template.business.system.entity.Menu;
import com.cvaiedu.template.business.system.entity.Role;
import com.cvaiedu.template.business.system.mapper.RoleMapper;
import com.cvaiedu.template.business.system.service.inter.MenuService;
import com.cvaiedu.template.business.system.service.inter.RoleService;
import com.cvaiedu.template.util.PageUtils;
import com.cvaiedu.template.util.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;


/**
 * 服务实现层
 *
 * @author code-generator
 * @date 2020-07-18 14:54:59
 */
@Service
public class RoleServiceImpl implements RoleService {

    @Resource
    private RoleMapper roleMapper;

    @Resource
    private MenuService menuService;


    @Override
    public Role getByPrimaryKey(Long id) {
        Role entity = roleMapper.selectById(id);
        return entity;
    }

    @Override
    public Role selectOne(Role entity) {
        return roleMapper.selectOne(entity);
    }

    @Override
    public List<Role> getByAccountCode(Integer accountCode) {
        List<Role> list = roleMapper.getByAccountCode(accountCode);
        for (Role role : list) {
            List<Menu> menuList = menuService.getByRoleId(role.getId());
            role.setMenuList(menuList);
        }
        return list;
    }


    @Override
    public PageUtils findPage(Map<String, Object> queryMap) {
        Query<?> query = new Query<>(queryMap);
        Page<Role> page = new Page<>(query.getCurrPage(), query.getLimit());
        Role entity = JSONObject.parseObject(JSONObject.toJSONString(queryMap), Role.class);
        EntityWrapper<Role> eWrapper = new EntityWrapper<>(entity);
        page.setRecords(roleMapper.selectPage(page, eWrapper));
        return new PageUtils(page);
    }


    @Override
    public List<Role> list(Map<String, Object> queryMap) {
        Role entity = JSONObject.parseObject(JSONObject.toJSONString(queryMap), Role.class);
        EntityWrapper<Role> eWrapper = new EntityWrapper<>(entity);
        List<Role> result = roleMapper.selectList(eWrapper);
        return result;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void save(Role entity) {
        entity.setCreateTime(new Date());
        roleMapper.insert(entity);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void deleteById(List<Long> ids) {
        roleMapper.deleteBatchIds(ids);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void updateById(Role entity) {
        roleMapper.updateById(entity);
    }


}
