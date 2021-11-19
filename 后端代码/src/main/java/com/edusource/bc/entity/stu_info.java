package com.edusource.bc.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@NoArgsConstructor
@Table(name = "stu_info")
public class stu_info implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private String sex;

    private String birth;

    private String start_date;

    private String end_date;

    private String university;

    private String profession;

    private String type;

    private String school_system;

    private String form;

    private String level;

    private String certificate_no;

    private String graduation_attributes;

    private String principaln_name;

    private long tra_code;


}
