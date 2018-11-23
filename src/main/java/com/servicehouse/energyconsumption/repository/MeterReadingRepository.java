/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.servicehouse.energyconsumption.repository;

import com.servicehouse.energyconsumption.domainobject.MeterReadingDO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 *
 * @author johnson3yo
 */
public interface MeterReadingRepository extends JpaRepository<MeterReadingDO, Long> {

    MeterReadingDO findByMeterIdAndMonth(String meterId, String month);

    @Query(nativeQuery = true, value = "select m.meter_reading - ( select mt.meter_reading from meter_reading mt where meter_id = ?1 and mt.id < m.id order by mt.id desc limit 1 ) from meter_reading m where m.meter_id = ?2 and m.month = ?3")
    Integer getConsumptionByMeterIdAndMonth(String meterId1, String meterId2, String month);

}
