package com.cvaiedu.template.business.system.service.impl;

import com.alibaba.fastjson.JSONObject;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;

import com.cvaiedu.template.util.PageUtils;
import com.cvaiedu.template.util.Query;
import com.cvaiedu.template.business.system.service.inter.RoleMenuService;
import com.cvaiedu.template.business.system.entity.RoleMenu;
import com.cvaiedu.template.business.system.mapper.RoleMenuMapper;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.annotation.Propagation;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;
import java.util.Date;


/**
 * 服务实现层
 *
 * @author code-generator
 * @date 2020-07-18 14:55:20
 */
@Service
public class RoleMenuServiceImpl implements RoleMenuService {

    @Resource
    private RoleMenuMapper roleMenuMapper;
	


    @Override
    public RoleMenu getByPrimaryKey(Long id) {
        RoleMenu entity = roleMenuMapper.selectById(id);
        return entity;
    }

    @Override
    public RoleMenu selectOne(RoleMenu entity) {
        return roleMenuMapper.selectOne(entity);
    }
	
	@Override
	public PageUtils findPage(Map<String, Object> queryMap) {
        Query<?> query = new Query<>(queryMap);
        Page<RoleMenu> page = new Page<>(query.getCurrPage(), query.getLimit());
        RoleMenu entity = JSONObject.parseObject(JSONObject.toJSONString(queryMap), RoleMenu.class);
        EntityWrapper<RoleMenu> eWrapper = new EntityWrapper<>(entity);
        page.setRecords(roleMenuMapper.selectPage(page, eWrapper));
        return new PageUtils(page);
	}

	@Override
	public List<RoleMenu> list(Map<String, Object> queryMap) {
        RoleMenu entity = JSONObject.parseObject(JSONObject.toJSONString(queryMap), RoleMenu.class);
        EntityWrapper<RoleMenu> eWrapper = new EntityWrapper<>(entity);
        List<RoleMenu> result = roleMenuMapper.selectList(eWrapper);
        return result;
	}
    
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void save(RoleMenu entity) {
        entity.setCreateTime(new Date());		
    	roleMenuMapper.insert(entity);
    }

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void deleteById(List<Long> ids){
		roleMenuMapper.deleteBatchIds(ids);
	}

	@Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void updateById(RoleMenu entity) {			
    	roleMenuMapper.updateById(entity);
    }


}
