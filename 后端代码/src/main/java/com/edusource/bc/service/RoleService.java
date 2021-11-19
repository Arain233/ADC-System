package com.edusource.bc.service;

import com.edusource.bc.entity.Role;
import com.edusource.bc.page.PageRequest;
import com.edusource.bc.page.PageResult;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface RoleService {
    /**
     * 根据id查询
     * @param id
     * @return
     */
    Role findById(Integer id);

    /**
     * 查询所有
     * @return
     */
    List<Role> findAll();

    /**
     * 根据id删除
     * @param id
     * @return
     */
    int deleteById(Integer id);

    /**
     * 进行多字段数据修改
     * @param role
     * @return
     */
    int Update(Role role);

    /**
     * 分页查找
     * @return
     */
    PageResult findPage(PageRequest pageRequest);
}
