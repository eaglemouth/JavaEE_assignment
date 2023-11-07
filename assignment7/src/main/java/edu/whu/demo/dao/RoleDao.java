package edu.whu.demo.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import edu.whu.demo.domain.Role;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface RoleDao extends BaseMapper<Role> {
}
