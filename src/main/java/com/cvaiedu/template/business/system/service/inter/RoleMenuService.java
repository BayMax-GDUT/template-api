package com.cvaiedu.template.business.system.service.inter;

import com.cvaiedu.template.business.system.entity.RoleMenu;
import com.cvaiedu.template.util.PageUtils;
import java.util.List;
import java.util.Map;




/**
 * 服务实现层接口
 *
 * @author code-generator
 * @date 2020-07-18 14:55:20
 */
public interface RoleMenuService {
	
    RoleMenu getByPrimaryKey(Long id);
	
	RoleMenu selectOne(RoleMenu entity);
	
	PageUtils findPage(Map<String, Object> queryMap);

	List<RoleMenu> list(Map<String, Object> queryMap);
    
    void save(RoleMenu entity);

	void deleteById(List<Long> ids);

    void updateById(RoleMenu entity);
}
