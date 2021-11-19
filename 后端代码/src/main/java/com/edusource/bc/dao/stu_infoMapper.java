package com.edusource.bc.dao;
import com.edusource.bc.entity.stu_info;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface stu_infoMapper {

    @Select("select * from stu_info")
    List<stu_info> findAll();

    @Select("select * from stu_info where id = #{id}")
    stu_info findById(Integer id);

    @Insert("insert into stu_info (name, sex, birth, start_date, end_date, university, profession, type, school_system, form," +
            "level, certificate_no, graduation_attributes, principaln_name,tra_code)"+
            "values(#{name}, #{sex}, #{birth}, #{start_date}, #{end_date}, #{university}, #{profession}, #{type}, #{school_system}, #{form},"+
            "#{level}, #{certificate_no}, #{graduation_attributes}, #{principaln_name},#{tra_code})")
    int save(stu_info stu_info);

    @Select("select * from stu_info where tra_code=#{tra_code}")
    stu_info findByTra_code(long tra_code);

    @Delete("delete from stu_info where id = #{id}")
    int deleteById(Integer id);

    @Select("select * from stu_info where certificate_no = #{no}")
    stu_info findByCert_no(String no);

    @Select("select * from stu_info where name = #{name}")
    List<stu_info> findByName(String name);

    @Update("update stu_info set"+
            "name = #{name},"+
            "sex = #{sex}, "+
            "birth = #{birth}"+
            "start_date = #{start_date}"+
            "end_date = #{end_date}"+
            "university = #{university}"+
            "profession = #{profession}"+
            "type = #{type}"+
            "school_system = #{school_system}"+
            "form = #{form}"+
            "level = #{level}"+
            "certificate_no = #{certificate_no}"+
            "graduation_attributes = #{graduation_attributes}"+
            "principaln_name = #{principaln_name}"+
            "where id = #{id}")
    int Update(stu_info stu_info);
}
