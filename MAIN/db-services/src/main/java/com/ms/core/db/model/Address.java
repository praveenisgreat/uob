package com.ms.core.db.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;

@Entity
@Table(name = "Address")
@EqualsAndHashCode(callSuper=false)
@RequiredArgsConstructor
@Data
public class Address extends AuditModel{

	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

	@Column(name = "address_type")
    @NotBlank
    @Size(min = 4, max = 150)
    private String addressType;
	
	@Column(name = "address_name")
    @NotBlank
    @Size(min = 4, max = 150)
    private String addressName;

    @Column(name = "line_1")
    @NotBlank
    @Size(min = 4, max = 150)
    private String line1;

    @Column(name = "line_2")
    private String line2;
    
    @Column(name = "line_3")
    private String line3;

    @Column(name = "line_4")
    private String line4;
    
    @Column(name = "country")
    @NotBlank
    private String country;
    
    @Column(name = "state")
    @NotBlank
    private String state;
    
    @Column(name = "district")
    private String district;
    
    @Column(name = "province")
    private String province;
    
	@Column(name = "postal_code")
    @NotBlank
    private String postalCode;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "customer_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Customer customer;
}
