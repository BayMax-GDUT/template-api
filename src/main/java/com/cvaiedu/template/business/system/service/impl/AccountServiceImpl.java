package com.cvaiedu.template.business.system.service.impl;

import com.alibaba.fastjson.JSONObject;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;

import com.cvaiedu.template.business.system.service.inter.AccountService;
import com.cvaiedu.template.util.PageUtils;
import com.cvaiedu.template.util.Query;
import com.cvaiedu.template.business.system.entity.Account;
import com.cvaiedu.template.business.system.mapper.AccountMapper;

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
 * @date 2020-07-18 14:53:12
 */
@Service
public class AccountServiceImpl implements AccountService {

    @Resource
    private AccountMapper accountMapper;
	


    @Override
    public Account getByPrimaryKey(Long id) {
        Account entity = accountMapper.selectById(id);
        return entity;
    }

    @Override
    public Account selectOne(Account entity) {
        return accountMapper.selectOne(entity);
    }
	
	@Override
	public PageUtils findPage(Map<String, Object> queryMap) {
        Query<?> query = new Query<>(queryMap);
        Page<Account> page = new Page<>(query.getCurrPage(), query.getLimit());
        Account entity = JSONObject.parseObject(JSONObject.toJSONString(queryMap), Account.class);
        EntityWrapper<Account> eWrapper = new EntityWrapper<>(entity);
        page.setRecords(accountMapper.selectPage(page, eWrapper));
        return new PageUtils(page);
	}

	@Override
	public List<Account> list(Map<String, Object> queryMap) {
        Account entity = JSONObject.parseObject(JSONObject.toJSONString(queryMap), Account.class);
        EntityWrapper<Account> eWrapper = new EntityWrapper<>(entity);
        return accountMapper.selectList(eWrapper);
	}

	@Override
    public List<Account> getByRoleId(Long roleId) {
        List<Account> list = accountMapper.getByRoleId(roleId);
        return list;
    }
    
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void save(Account entity) {
        entity.setCreateTime(new Date());		
    	accountMapper.insert(entity);
    }

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void deleteById(List<Long> ids){
		accountMapper.deleteBatchIds(ids);
	}

	@Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void updateById(Account entity) {			
    	accountMapper.updateById(entity);
    }


}
