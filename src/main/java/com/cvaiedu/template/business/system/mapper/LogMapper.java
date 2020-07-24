package com.cvaiedu.template.business.system.mapper;


import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.cvaiedu.template.business.system.entity.Log;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface LogMapper extends BaseMapper<Log>  {

}
