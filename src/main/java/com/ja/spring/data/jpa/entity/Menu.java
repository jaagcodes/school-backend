package com.ja.spring.data.jpa.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Menu {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long menuId;

    private String icon;

    private String nome;

    private String url;

    @ManyToMany
    @JoinTable(
            name = "rol_menu",
            joinColumns = @JoinColumn(name = "id_menu", referencedColumnName = "menuId"),
            inverseJoinColumns = @JoinColumn(name = "id_role", referencedColumnName = "roleId")
    )
    private List<Role> roles;
}
