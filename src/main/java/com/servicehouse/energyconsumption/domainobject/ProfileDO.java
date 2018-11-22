/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.servicehouse.energyconsumption.domainobject;

import java.util.Date;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author johnson3yo
 */
@Entity
@Table(name = "profile")
public class ProfileDO {

    @Id
    @GeneratedValue
    private Long id;
    /*
    for the sake of this demo , the profile name needs no length constraint
    */
    @Column
    @NotNull(message = "profile name is mandatory")
    private String name;
    /*
    Here the month field is set as a string with bean validation
    min and max size 3, Ideally this field needs a custom bean validation 
    annotation to check that the month conforms with the standard months [JAN,FEB..DEC]
    */
    @Column
    @Size(min=3,max=3)
    @NotNull(message = "profile month is mandatory")
    private String month;
    @Column
    private Date recordCreationDate;
    @Column
    @NotNull(message = "fraction is mandatory")
    private Double fraction;
    


    public ProfileDO() {
    }

    public ProfileDO(String name, String month,Double fraction) {
      this.name = name;
      this.month = month;
      this.fraction = fraction;
    }

   
    
    
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public Date getRecordCreationDate() {
        return recordCreationDate;
    }

    public void setRecordCreationDate(Date recordCreationDate) {
        this.recordCreationDate = recordCreationDate;
    }

    public Double getFraction() {
        return fraction;
    }

    public void setFraction(Double fraction) {
        this.fraction = fraction;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ProfileDO other = (ProfileDO) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.month, other.month)) {
            return false;
        }
        return true;
    }
    
    

}
