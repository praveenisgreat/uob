package com.ms.core.auth.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.NaturalId;

import com.fasterxml.jackson.annotation.JsonTypeName;

import lombok.Data;

@Entity
@Table(name = "Ability")
@Data
public class AbilityModel {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @NaturalId
    @Column(length = 60)
    private AbilityAction action;	
    
    @Enumerated(EnumType.STRING)
    @NaturalId
    @Column(length = 60)
    private AbilitySubject subject;	
    
    public AbilityModel() {

    }
    
    public AbilityModel(AbilityAction action, AbilitySubject subject) {
        this.action = action;
        this.subject = subject;
    }
}
