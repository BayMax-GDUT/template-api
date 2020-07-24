package com.cvaiedu.template.business.system.service.impl;

import com.alibaba.fastjson.JSONObject;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;

import com.cvaiedu.template.util.PageUtils;
import com.cvaiedu.template.util.Query;
import com.cvaiedu.template.business.system.service.inter.RoleAccountService;
import com.cvaiedu.template.business.system.entity.RoleAccount;
import com.cvaiedu.template.business.system.mapper.RoleAccountMapper;

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
 * @date 2020-07-18 14:55:13
 */
@Service
public class RoleAccountServiceImpl implements RoleAccountService {

    @Resource
    private RoleAccountMapper roleAccountMapper;
	


    @Override
    public RoleAccount getByPrimaryKey(Long id) {
        RoleAccount entity = roleAccountMapper.selectById(id);
        return entity;
    }

    @Override
    public RoleAccount selectOne(RoleAccount entity) {
        return roleAccountMapper.selectOne(entity);
    }
	
	@Override
	public PageUtils findPage(Map<String, Object> queryMap) {
        Query<?> query = new Query<>(queryMap);
        Page<RoleAccount> page = new Page<>(query.getCurrPage(), query.getLimit());
        RoleAccount entity = JSONObject.parseObject(JSONObject.toJSONString(queryMap), RoleAccount.class);
        EntityWrapper<RoleAccount> eWrapper = new EntityWrapper<>(entity);
        page.setRecords(roleAccountMapper.selectPage(page, eWrapper));
        return new PageUtils(page);
	}

	@Override
	public List<RoleAccount> list(Map<String, Object> queryMap) {
        RoleAccount entity = JSONObject.parseObject(JSONObject.toJSONString(queryMap), RoleAccount.class);
        EntityWrapper<RoleAccount> eWrapper = new EntityWrapper<>(entity);
        List<RoleAccount> result = roleAccountMapper.selectList(eWrapper);
        return result;
	}
    
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void save(RoleAccount entity) {
        entity.setCreateTime(new Date());		
    	roleAccountMapper.insert(entity);
    }

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void deleteById(List<Long> ids){
		roleAccountMapper.deleteBatchIds(ids);
	}

	@Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void updateById(RoleAccount entity) {			
    	roleAccountMapper.updateById(entity);
    }


}
