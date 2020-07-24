package com.cvaiedu.template.business.system.service.inter;

import com.cvaiedu.template.business.system.entity.RoleAccount;
import com.cvaiedu.template.util.PageUtils;
import java.util.List;
import java.util.Map;




/**
 * 服务实现层接口
 *
 * @author code-generator
 * @date 2020-07-18 14:55:13
 */
public interface RoleAccountService {
	
    RoleAccount getByPrimaryKey(Long id);
	
	RoleAccount selectOne(RoleAccount entity);
	
	PageUtils findPage(Map<String, Object> queryMap);

	List<RoleAccount> list(Map<String, Object> queryMap);

    void save(RoleAccount entity);

	void deleteById(List<Long> ids);

    void updateById(RoleAccount entity);
}
