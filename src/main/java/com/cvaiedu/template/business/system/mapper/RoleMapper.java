package com.cvaiedu.template.business.system.mapper;


import org.apache.ibatis.annotations.Mapper;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

import com.cvaiedu.template.business.system.entity.Role;

import java.util.List;

@Mapper
@Repository
public interface RoleMapper extends BaseMapper<Role>  {

    List<Role> getByAccountCode(Integer accountCode);
	
}
