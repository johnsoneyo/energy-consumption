/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.servicehouse.energyconsumption.controller;

import com.servicehouse.energyconsumption.domainobject.MeterReadingDO;
import com.servicehouse.energyconsumption.domainobject.ProfileDO;
import com.servicehouse.energyconsumption.exception.EnergyConsumptionException;
import com.servicehouse.energyconsumption.service.MeterService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author johnson3yo
 */
@RestController
@RequestMapping("v1/meter-readings")
public class MeterReadingController {

    @Autowired
    private MeterService service;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<MeterReadingDO> getMeterReadings() {
        return service.findAllReadings();
    }

    @PutMapping("/{readingId}")
    @ResponseStatus(HttpStatus.OK)
    public MeterReadingDO updateMeterReading(@PathVariable long meterId, @RequestBody MeterReadingDO m) {
        m.setId(meterId);
        return service.updateReading(m);
    }

    @DeleteMapping("/{readingId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteMeterReading(@PathVariable long readingId) {
        service.deleteMeterReading(readingId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createMeterReading(@RequestBody MeterReadingDO[] readings) throws EnergyConsumptionException {
        service.createReading(readings);
    }

    /*
    * Consumptions API 
    */
    @GetMapping("/{meterId}/{month}/consumptions")
    @ResponseStatus(HttpStatus.OK)
    public Integer getConsumptions(@PathVariable("meterId") String meterId, @PathVariable("month") String month) {
        return service.getConsumptionByMeterIdAndMonth(meterId, month);
    }

}
