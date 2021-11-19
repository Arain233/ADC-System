package com.edusource.bc.service;

import com.edusource.bc.entity.User;
import com.edusource.bc.page.PageRequest;
import com.edusource.bc.page.PageResult;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import java.util.List;

@Component
@Service
public interface UserService {
    /**
     * 根据id查询
     * @param id
     * @return
     */
    User findById(Integer id);


    /**
     * 根据身份证号码查询
     * @param idNum
     * @return
     */
    User findByNum(String idNum);

    /**
     * 根据name查询 rolename
     * @param name
     * @return
     */

    /**
     * 根据name查询
     * @param name
     * @return
     */
    User findByName(String name);


    /**
     * 根据name查询 rolename
     * @param name
     * @return
     */
    String findRoleByName(String name);

    /**
     * 查询所有
     * @return
     */
    List<User> findAll();

    /**
     * 根据id删除
     * @param id
     * @return
     */
    int deleteById(Integer id);

    /**
     * 新增用户
     * @param user
     * @return
     */
    int save(User user);

    /**
     * 进行多字段数据修改
     * @param user
     * @return
     */
    int Update(User user);

    /**
     * 分页查找
     * @return
     */
    PageResult findPage(PageRequest pageRequest);

}
