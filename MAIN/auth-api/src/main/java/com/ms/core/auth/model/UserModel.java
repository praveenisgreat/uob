package com.ms.core.auth.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;


@Entity
@Table(name = "ro")
@EqualsAndHashCode(callSuper=false)
@RequiredArgsConstructor
@Data
public class UserModel extends AuditModel{

	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "username")
    private String username;
    
    @Column(name = "password")
    @JsonIgnore
    private String password;
    
    @Column(name = "avatar")
    private String avatar;
    
    @Column(name = "email")
    private String email;
    
    @Column(name = "role")
    private String role;
    
    @OneToMany(fetch = FetchType.EAGER)
    @JoinTable(
    		name = "User_ability",
    		joinColumns = { @JoinColumn(name = "ro_id") }, 
    		inverseJoinColumns = { @JoinColumn(name = "ability_id") }
            )
    @JsonIgnore
    private Set<AbilityModel> ability = new HashSet<>();
    
    /*
    @ManyToMany(fetch = FetchType.LAZY) 
    @JoinTable(name = "User_ability",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "ability_id"))
    @JsonIgnore
    private Set<AbilityModel> ability = new HashSet<>();
    */
    
}
