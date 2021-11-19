package com.edusource.bc.service.impl;

import com.edusource.bc.dao.stu_infoMapper;
import com.edusource.bc.entity.stu_info;
import com.edusource.bc.fabric.Invoke;
import com.edusource.bc.fabric.Query;
import com.edusource.bc.page.MybatisHelper;
import com.edusource.bc.page.PageRequest;
import com.edusource.bc.page.PageResult;
import com.edusource.bc.service.Stu_infoService;
import com.edusource.bc.utils.Tra_codeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class Stu_infoServiceImpl implements Stu_infoService {

    @Resource
    private stu_infoMapper Mapper;
    @Autowired
    private Invoke invoke;
    @Autowired
    private Query query;

    public static long code;

    /*
     * 新学生信息入库
     *
     * */
    @Override
    public int InUpdate(stu_info stu_info) {
        code = Tra_codeUtils.code();
        stu_info.setTra_code(code);
        if (stu_info.getBirth() == null) {
            return 0;
        }
        if (stu_info.getCertificate_no() == null) {
            return 0;
        }
        if (stu_info.getType() == null) {
            return 0;
        }
        if (stu_info.getSchool_system() == null) {
            return 0;
        }
        if (stu_info.getForm() == null) {
            return 0;
        }
        if (stu_info.getName() == null) {
            return 0;
        }
        if (stu_info.getEnd_date() == null) {
            return 0;
        }
        if (stu_info.getGraduation_attributes() == null) {
            return 0;
        }
        if (stu_info.getLevel() == null) {
            return 0;
        }
        if (stu_info.getPrincipaln_name() == null) {
            return 0;
        }
        if (stu_info.getProfession() == null) {
            return 0;
        }
        if (stu_info.getSex() == null) {
            return 0;
        }
        if (stu_info.getStart_date() == null) {
            return 0;
        }
        if (stu_info.getUniversity() == null) {
            return 0;
        }
        return Mapper.save(stu_info);
    }

    /*
     * 根据ID查询
     *
     * */
    @Override
    public stu_info findById(Integer id) {
        stu_info stu_info = Mapper.findById(id);
        return stu_info;
    }

    /*
     * 根据防伪码查询
     *
     * */
    @Override
    public stu_info findByTra_code(long tra_code) {
        System.out.println("findByTraCode Method Implement:" + Mapper.findByTra_code(tra_code));
        stu_info stu_info = Mapper.findByTra_code(tra_code);
//        try {
//            query.query(stu_info);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        System.out.println(stu_info);
        return stu_info;
    }

    /*
     * 查询所有
     *
     * */
    @Override
    public List<stu_info> findAll() {
        List<stu_info> stus = Mapper.findAll();
        return stus;
    }

    /*
     * 根据ID删除
     *
     * */
    @Override
    public int deleteById(Integer id) {
        return Mapper.deleteById(id);
    }

    /*
     * 多字段数据修改
     *
     * */
    @Override
    public int Update(stu_info stu_info) {
        stu_info up = Mapper.findById(stu_info.getId());
        if (stu_info.getBirth() != null) {
            up.setBirth(stu_info.getBirth());
        }
        if (stu_info.getCertificate_no() != null) {
            up.setCertificate_no(stu_info.getCertificate_no());
        }
        if (stu_info.getType() != null) {
            up.setType(stu_info.getType());
        }
        if (stu_info.getSchool_system() != null) {
            up.setSchool_system(stu_info.getSchool_system());
        }
        if (stu_info.getForm() != null) {
            up.setForm(stu_info.getForm());
        }
        if (stu_info.getName() != null) {
            up.setName(stu_info.getName());
        }
        if (stu_info.getEnd_date() != null) {
            up.setEnd_date(stu_info.getEnd_date());
        }
        if (stu_info.getGraduation_attributes() != null) {
            up.setGraduation_attributes(stu_info.getGraduation_attributes());
        }
        if (stu_info.getLevel() != null) {
            up.setLevel(stu_info.getLevel());
        }
        if (stu_info.getPrincipaln_name() != null) {
            up.setPrincipaln_name(stu_info.getPrincipaln_name());
        }
        if (stu_info.getProfession() != null) {
            up.setProfession(stu_info.getProfession());
        }
        if (stu_info.getSex() != null) {
            up.setSex(stu_info.getSex());
        }
        if (stu_info.getStart_date() != null) {
            up.setStart_date(stu_info.getStart_date());
        }
        if (stu_info.getUniversity() != null) {
            up.setUniversity(stu_info.getUniversity());
        }
        invoke.invoke(up);
        return Mapper.Update(up);
    }

    /*
     * 分页查找
     *
     * */
    @Override
    public PageResult findPage(PageRequest pageRequest) {
        Object label = pageRequest.getParam("label");
        if (label != null) {
            return MybatisHelper.findPage(pageRequest, Mapper, "findPageByLabel", label);
        }
        return MybatisHelper.findPage(pageRequest, Mapper);
    }


    @Override
    public List<stu_info> findByName(String name) {
        return Mapper.findByName(name);
    }

    @Override
    public stu_info findByCert_no(String no) {
        return Mapper.findByCert_no(no);
    }
}
