package com.cvaiedu.template.business.system.service.inter;

import com.cvaiedu.template.business.system.entity.Log;
import com.cvaiedu.template.util.PageUtils;

import java.util.List;
import java.util.Map;


/**
 * 服务实现层接口
 *
 * @author code-generator
 * @date 2020-03-27 11:32:07
 */
public interface LogService {
	
    Log getByPrimaryKey(Long id);
	
	PageUtils findPage(Map<String, Object> queryMap);

	List<Log> list(Map<String, Object> queryMap);
    
    void save(Log entity);

	void deleteById(List<Long> ids);

    void updateById(Log entity);
}
