/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.servicehouse.energyconsumption.domainobject;

import java.io.Serializable;
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
@Table(name = "meter_reading")
public class MeterReadingDO implements Serializable {

    @Id
    @GeneratedValue
    private Long id;
    @Column
    @NotNull(message = "meter ID is mandatory")
    private String meterId;
    @Column
    @NotNull(message = "profile is mandatory")
    private String profile;
    @Column
    @Size(min = 3,max=3)
    private String month;
    @Column
    @NotNull(message = "meter reading is mandatory")
    private Integer meterReading;

    public MeterReadingDO(String meterId, String profile, String month, Integer meterReading) {
        this.meterId = meterId;
        this.profile = profile;
        this.month = month;
        this.meterReading = meterReading;
    }
    
    

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMeterId() {
        return meterId;
    }

    public void setMeterId(String meterId) {
        this.meterId = meterId;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public Integer getMeterReading() {
        return meterReading;
    }

    public void setMeterReading(Integer meterReading) {
        this.meterReading = meterReading;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + Objects.hashCode(this.id);
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
        final MeterReadingDO other = (MeterReadingDO) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }
    
    

}
