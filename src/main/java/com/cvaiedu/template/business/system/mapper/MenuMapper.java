package com.cvaiedu.template.business.system.mapper;


import org.apache.ibatis.annotations.Mapper;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

import com.cvaiedu.template.business.system.entity.Menu;

import java.util.List;

@Mapper
@Repository
public interface MenuMapper extends BaseMapper<Menu>  {

    List<Menu> getByRoleId(Long roleId);

    List<Menu> getByAccountCode(Integer accountCode);
	
}
