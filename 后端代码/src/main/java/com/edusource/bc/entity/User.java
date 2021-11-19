package com.edusource.bc.entity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "user")
public class User implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @JsonProperty("username")
    private String name;

    private String password;

    private String idNum;

    private String salt;

    private String email;

    private String mobile;

    @Column(name = "role_id")
    private Integer role_id;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "role_id",referencedColumnName = "id",insertable = false,updatable = false)
    @JsonIgnore
    private Role role;

    public User(String name, String password, String salt, String email, String mobile, Integer role_id) {
        this.name = name;
        this.password = password;
        this.salt = salt;
        this.email = email;
        this.mobile = mobile;
        this.role_id = role_id;
    }

    public User(String name, String password, String idNum, String salt, String email, String mobile, Integer role_id) {
        this.name = name;
        this.password = password;
        this.idNum = idNum;
        this.salt = salt;
        this.email = email;
        this.mobile = mobile;
        this.role_id = role_id;
    }
}
