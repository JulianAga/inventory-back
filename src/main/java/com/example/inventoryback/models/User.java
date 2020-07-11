package com.example.inventoryback.models;

import java.util.Set;
import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@ToString
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String mail;

    private String password;

    @Transient
    private String passwordConfirm;

    private String name;

    private String cellphone;

    private String address;

    @ManyToOne
    @JoinColumn(name = "role")
    private Role role;

    private boolean active=true;

}
