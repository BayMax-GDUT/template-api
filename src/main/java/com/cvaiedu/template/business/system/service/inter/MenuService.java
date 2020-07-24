package com.cvaiedu.template.business.system.service.inter;

import com.cvaiedu.template.business.system.entity.Menu;
import com.cvaiedu.template.util.PageUtils;
import java.util.List;
import java.util.Map;




/**
 * 服务实现层接口
 *
 * @author code-generator
 * @date 2020-07-18 14:54:30
 */
public interface MenuService {
	
    Menu getByPrimaryKey(Long id);
	
	Menu selectOne(Menu entity);

    List<Menu> getByRoleId(Long roleId);

    List<Menu> getByAccountCode(Integer accountCode);
	
	PageUtils findPage(Map<String, Object> queryMap);

	List<Menu> list(Map<String, Object> queryMap);

    void save(Menu entity);

	void deleteById(List<Long> ids);

    void updateById(Menu entity);
}
