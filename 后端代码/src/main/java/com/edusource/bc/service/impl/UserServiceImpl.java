package com.edusource.bc.service.impl;

import com.edusource.bc.dao.UserMapper;
import com.edusource.bc.entity.User;
import com.edusource.bc.page.MybatisHelper;
import com.edusource.bc.page.PageRequest;
import com.edusource.bc.page.PageResult;
import com.edusource.bc.service.RoleService;
import com.edusource.bc.service.UserService;
import com.edusource.bc.utils.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.List;

@Component
public class UserServiceImpl implements UserService {
    @Resource
    UserMapper userMapper;

    @Resource
    RoleService roleService;

    @Override
    public User findById(Integer id) {
        return userMapper.findById(id);
    }

    @Override
    public User findByName(String name) {
        return userMapper.findByName(name);
    }

    @Override
    public List<User> findAll() {
        return userMapper.findAll();
    }

    @Override
    public int deleteById(Integer id) {
        return userMapper.deleteById(id);
    }

    @Override
    public String findRoleByName(String name) {
        return roleService.findById(userMapper.findByName(name).getRole_id()).getName();
    }

    @Override
    public int save(User user) {

        if(userMapper.findByName(user.getName())!=null)
        {
            return 0;
        }
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[48];
        random.nextBytes(salt);
        user.setSalt(Base64.getEncoder().encodeToString(salt));
        user.setPassword(PasswordEncoder.Encrypt(user.getPassword(),user.getSalt()));
        return userMapper.save(user);
    }

    @Override
    public int Update(User user) {
        User update = userMapper.findById(user.getId());
        if(user.getName()!=null)
        {
            update.setName(user.getName());
        }
        if(user.getRole_id()!=null)
        {
            update.setRole_id(user.getRole_id());
        }
        if(user.getPassword()!=null)
        {
            update.setPassword(user.getPassword());
        }
        if(user.getSalt()!=null)
        {
            update.setSalt(user.getSalt());
        }
        if(user.getMobile()!=null)
        {
            update.setMobile(user.getMobile());
        }
        if(user.getEmail()!=null)
        {
            update.setEmail(user.getEmail());
        }
        if(user.getIdNum()!=null)
        {
            update.setIdNum(user.getIdNum());
        }
        return userMapper.Update(update);
    }

    @Override
    public PageResult findPage(PageRequest pageRequest) {
        Object label = pageRequest.getParam("label");
        if(label != null) {
            return MybatisHelper.findPage(pageRequest,userMapper,"findPageByLabel", label);
        }
        return MybatisHelper.findPage(pageRequest,userMapper);
    }

    @Override
    public User findByNum(String idNum) {
        return userMapper.findByNum(idNum);
    }
}
