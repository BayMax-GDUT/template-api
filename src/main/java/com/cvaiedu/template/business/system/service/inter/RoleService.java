package com.cvaiedu.template.business.system.service.inter;

import com.cvaiedu.template.business.system.entity.Role;
import com.cvaiedu.template.util.PageUtils;
import java.util.List;
import java.util.Map;




/**
 * 服务实现层接口
 *
 * @author code-generator
 * @date 2020-07-18 14:54:59
 */
public interface RoleService {
	
    Role getByPrimaryKey(Long id);
	
	Role selectOne(Role entity);

    List<Role> getByAccountCode(Integer accountCode);

    PageUtils findPage(Map<String, Object> queryMap);

    List<Role> list(Map<String, Object> queryMap);
    
    void save(Role entity);

	void deleteById(List<Long> ids);

    void updateById(Role entity);
}
