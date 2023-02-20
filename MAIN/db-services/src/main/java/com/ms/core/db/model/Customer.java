package com.ms.core.db.model;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "Customer")
@Getter
@Setter
public class Customer extends AuditModel{
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

	@Column(name = "first_name")
    @NotBlank
    @Size(min = 2, max = 40)
    private String firstName;

    @Column(name = "last_name")
    @NotBlank
    @Size(min = 2, max = 40)
    private String lastName;

    @Column(name = "date_of_birth")
    @Temporal(TemporalType.DATE)
    @NotNull
    private Date dateOfBirth;

	@Column(name = "race")
    @NotBlank
    @Size(min = 4, max = 10)
    private String race;
    
    @Column(name = "nationality")
    @NotBlank
    @Size(min = 2, max = 10)
    private String nationality;
    
    @Column(name = "residencial_status")
    @NotBlank
    @Size(min = 2, max = 10)
    private String residencialStatus;
    
    @Column(name = "contact_no")
    @NotBlank
    private String contactNo;
    
    @Column(name = "username")
    @Size(min = 5, max = 15)
    private String username;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    @JsonIgnore
    private String password;
    
    @OneToMany(mappedBy="customer", cascade = CascadeType.ALL)
    private List<Address> address;
}
