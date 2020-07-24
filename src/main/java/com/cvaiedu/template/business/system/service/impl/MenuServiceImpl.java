package com.cvaiedu.template.business.system.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.cvaiedu.template.business.system.service.inter.MenuService;
import com.cvaiedu.template.business.system.service.inter.RoleService;
import com.cvaiedu.template.business.system.entity.Menu;
import com.cvaiedu.template.business.system.entity.Role;
import com.cvaiedu.template.business.system.mapper.MenuMapper;
import com.cvaiedu.template.constant.Constant;
import com.cvaiedu.template.exception.FrameworkException;
import com.cvaiedu.template.util.PageUtils;
import com.cvaiedu.template.util.Query;
import com.cvaiedu.template.util.TokenUtils;
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
 * @date 2020-07-18 14:54:30
 */
@Service
public class MenuServiceImpl implements MenuService {

    @Resource
    private MenuMapper menuMapper;

    @Resource
    private TokenUtils tokenUtils;

    @Resource
    private RoleService roleService;

    @Override
    public Menu getByPrimaryKey(Long id) {
        Menu entity = menuMapper.selectById(id);
        return entity;
    }

    @Override
    public Menu selectOne(Menu entity) {
        return menuMapper.selectOne(entity);
    }

    @Override
    public List<Menu> getByRoleId(Long roleId) {
        Role role = roleService.getByPrimaryKey(roleId);
        if (role == null || role.getDelFlag().equals(Constant.DEL_TAG_DEL)) {
            throw new FrameworkException("角色不存在");
        }
        List<Menu> menuList;
        menuList = menuMapper.getByRoleId(role.getId());
        return menuList;
    }

    @Override
    public List<Menu> getByAccountCode(Integer accountCode) {
        // 平台管理员直接返回所有菜单
        if (accountCode.equals(Constant.ADMIN_ID)) {
            return menuMapper.selectList(new EntityWrapper<>(new Menu()));
        } else {
            // 如果不是平台管理员（accountCode = 1） 根据角色查询菜单
            return menuMapper.getByAccountCode(accountCode);
        }
    }

    @Override
	public PageUtils findPage(Map<String, Object> queryMap) {
        Query<?> query = new Query<>(queryMap);
        Page<Menu> page = new Page<>(query.getCurrPage(), query.getLimit());
        Menu entity = JSONObject.parseObject(JSONObject.toJSONString(queryMap), Menu.class);
        EntityWrapper<Menu> eWrapper = new EntityWrapper<>(entity);
        page.setRecords(menuMapper.selectPage(page, eWrapper));
        return new PageUtils(page);
	}

	@Override
	public List<Menu> list(Map<String, Object> queryMap) {
        Menu entity = JSONObject.parseObject(JSONObject.toJSONString(queryMap), Menu.class);
        EntityWrapper<Menu> eWrapper = new EntityWrapper<>(entity);
        List<Menu> result = menuMapper.selectList(eWrapper);
        return result;
	}

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void save(Menu entity) {
        entity.setCreateTime(new Date());
    	menuMapper.insert(entity);
    }

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void deleteById(List<Long> ids){
		menuMapper.deleteBatchIds(ids);
	}

	@Override
    @Transactional(propagation = Propagation.REQUIRED)
    public
    void updateById(Menu entity) {
    	menuMapper.updateById(entity);
    }


}
