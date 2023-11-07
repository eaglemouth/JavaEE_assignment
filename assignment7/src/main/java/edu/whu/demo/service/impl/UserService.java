package edu.whu.demo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import edu.whu.demo.dao.UserDao;
import edu.whu.demo.domain.UserDto;
import edu.whu.demo.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService extends ServiceImpl<UserDao, User> {
    @Autowired
    UserDao userDao;

    /**
     * 将查询结果添加到缓存user中，以name作为key
     * @param name
     * @return
     */
    @Cacheable(cacheNames = "user",key = "#name",condition = "#name!=null")
    public UserDto getUser(String name){
        return userDao.getUser(name);
    }

    /**
     * 移除缓存user中key为userName的对象
     * @param userName
     */
    @Transactional
    @CacheEvict(cacheNames = "user",key = "#userName")
    public void deleteUser(String userName) {
        userDao.deleteById(userName);
    }

    /**
     * 移除缓存user中key为name的对象
     * @param user
     * @return
     */
    @Transactional
    @CacheEvict(cacheNames = "user",key = "#user.name")
    public void updateUser(User user) {
        userDao.updateById(user);
    }



    @Transactional
    public User addUser(User user){
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        userDao.insert(user);
        return user;
    }

    public List<User> findall() {
        return userDao.selectList(null);
    }
}
