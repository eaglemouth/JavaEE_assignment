package edu.whu.demo.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import edu.whu.demo.domain.User;
import edu.whu.demo.domain.UserDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserDao extends BaseMapper<User> {
    @Select("SELECT id, password FROM user WHERE name = #{name}")
    public UserDto getUser(String name);
}
