package com.cvaiedu.template.business.system.mapper;


import org.apache.ibatis.annotations.Mapper;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

import com.cvaiedu.template.business.system.entity.Account;

import java.util.List;

@Mapper
@Repository
public interface AccountMapper extends BaseMapper<Account>  {

	List<Account> getByRoleId(Long roleId);

}
