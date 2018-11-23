/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.servicehouse.energyconsumption.service;

import com.servicehouse.energyconsumption.domainobject.FileLocation;
import com.servicehouse.energyconsumption.domainobject.MeterReadingDO;
import com.servicehouse.energyconsumption.exception.EnergyConsumptionException;
import com.servicehouse.energyconsumption.repository.MeterReadingRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author johnson3yo
 */
@Service
public class MeterService {

    @Autowired
    private MeterReadingRepository repository;
    @Autowired
    private MeterReadingProcessorService service;

    public List<MeterReadingDO> findAllReadings() {
        return repository.findAll();
    }

    public MeterReadingDO updateReading(MeterReadingDO m) {
       return repository.save(m);
    }

    public void deleteMeterReading(long readingId) {
       repository.deleteById(readingId);
    }

    public void createReading(MeterReadingDO[] readings) throws EnergyConsumptionException {
        service.processMeterReadingHttpRequest(readings);
    }
    public void createReading(MeterReadingDO reading) {
        repository.save(reading);
    }

    public Integer getConsumptionByMeterIdAndMonth(String meterId, String month) {
      return repository.getConsumptionByMeterIdAndMonth(meterId,meterId,month);
    }

}
