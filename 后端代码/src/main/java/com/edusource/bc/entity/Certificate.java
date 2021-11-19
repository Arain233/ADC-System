package com.edusource.bc.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "certificate")
public class Certificate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String certificate_binary;

    private String certificate_no;

    private String hash;

    public Certificate(String certificate_binary, String certificate_no) {
        this.certificate_binary = certificate_binary;
        this.certificate_no = certificate_no;
    }
}
