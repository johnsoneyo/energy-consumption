/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.servicehouse.energyconsumption.service;

import com.servicehouse.energyconsumption.domainobject.FileLocation;
import com.servicehouse.energyconsumption.exception.EnergyConsumptionException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 *
 * @author johnson3yo
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class MeterReadingProcessorServiceTest {

    @Autowired
    private MeterReadingProcessorService service;

    @Test(expected = EnergyConsumptionException.class)
    public void testUnknownMeterReadingFile() throws EnergyConsumptionException {
        service.processMeterReadingFile("/home/dummy.csv", FileLocation.FILEDIR);
    }

    @Test(expected = EnergyConsumptionException.class)
    public void testInvalidMeterReadingFile() throws EnergyConsumptionException {
        service.processMeterReadingFile("invalid-meter-reading.csv", FileLocation.CLASSPATH);
    }

}
