package com.cvaiedu.template.business.system.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.cvaiedu.template.business.system.entity.Log;
import com.cvaiedu.template.business.system.mapper.LogMapper;
import com.cvaiedu.template.business.system.service.inter.LogService;
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
 * @date 2020-03-27 11:32:07
 */
@Service
public class LogServiceImpl implements LogService {

    @Resource
    private LogMapper logMapper;


    @Override
    public Log getByPrimaryKey(Long id) {
        Log entity = logMapper.selectById(id);
        return entity;
    }

    @Override
    public PageUtils findPage(Map<String, Object> queryMap) {
        Query<?> query = new Query<>(queryMap);
        Page<Log> page = new Page<>(query.getCurrPage(), query.getLimit());
        Log entity = JSONObject.parseObject(JSONObject.toJSONString(queryMap), Log.class);
        List<Log> records = logMapper.selectPage(page, new EntityWrapper<>(entity));
        page.setRecords(records);
        return new PageUtils(page);
    }

    @Override
    public List<Log> list(Map<String, Object> queryMap) {
        Log entity = JSONObject.parseObject(JSONObject.toJSONString(queryMap), Log.class);
        return logMapper.selectList(new EntityWrapper<>(entity));
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void save(Log entity) {
        entity.setCreateTime(new Date());
        logMapper.insert(entity);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void deleteById(List<Long> ids) {
        logMapper.deleteBatchIds(ids);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void updateById(Log entity) {
        logMapper.updateById(entity);
    }


}
