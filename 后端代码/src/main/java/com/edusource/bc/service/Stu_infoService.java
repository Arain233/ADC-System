package com.edusource.bc.service;

import com.edusource.bc.entity.stu_info;
import com.edusource.bc.page.PageResult;
import com.edusource.bc.page.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface Stu_infoService {
    /*
    * 新学生信息入库
    *
    * */
    int InUpdate(stu_info stu_info);



    /*
    * 根据ID查询
    *
    * */
    stu_info findById(Integer id);

    /*
    * 根据防伪码查询
    *
    * */
    stu_info findByTra_code(long tra_code);

    /*
    * 查询所有
    *
    * */
    List<stu_info> findAll();

    /*
    * 根据ID删除
    *
    * */
    int deleteById(Integer id);

    /*
    * 多字段数据修改
    *
    * */
    int Update(stu_info stu_info);

    /*
    * 分页查找
    *
    * */
    PageResult findPage(PageRequest pageRequest);

    /*
    * 根据姓名查找学生证书信息
    *
    * */
    List<stu_info> findByName(String name);

    /*
    * 根据证书编号查找证书信息
    *
    * */
    stu_info findByCert_no(String no);
}
