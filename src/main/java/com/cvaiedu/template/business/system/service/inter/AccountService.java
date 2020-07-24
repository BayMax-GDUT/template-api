package com.cvaiedu.template.business.system.service.inter;

import com.cvaiedu.template.business.system.entity.Account;
import com.cvaiedu.template.util.PageUtils;
import java.util.List;
import java.util.Map;




/**
 * 服务实现层接口
 *
 * @author code-generator
 * @date 2020-07-18 14:53:12
 */
public interface AccountService {
	
    Account getByPrimaryKey(Long id);
	
	Account selectOne(Account entity);
	
	PageUtils findPage(Map<String, Object> queryMap);

	List<Account> list(Map<String, Object> queryMap);

	List<Account> getByRoleId(Long roleId);
    
    void save(Account entity);

	void deleteById(List<Long> ids);

    void updateById(Account entity);
}
