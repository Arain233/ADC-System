package com.edusource.bc.service.impl;
import com.edusource.bc.dao.RoleMapper;
import com.edusource.bc.entity.Role;
import com.edusource.bc.page.PageRequest;
import com.edusource.bc.page.PageResult;
import com.edusource.bc.service.RoleService;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.util.Date;
import java.util.List;


@Service
public class RoleServiceImpl implements RoleService {
    @Resource
    RoleMapper roleMapper;
    @Override
    public Role findById(Integer id) {
        return roleMapper.findById(id);
    }

    @Override
    public List<Role> findAll() {
        return roleMapper.findAll();
    }

    @Override
    public int deleteById(Integer id) {
        return roleMapper.deleteById(id);
    }

    @Override
    public int Update(Role role) {
        if(role.getName()!=null&&roleMapper.findById(role.getId())!=null)
        {
            return roleMapper.Update(role);
        }
        return 0;
    }

    @Override
    public PageResult findPage(PageRequest pageRequest) {
        return null;
    }
}
