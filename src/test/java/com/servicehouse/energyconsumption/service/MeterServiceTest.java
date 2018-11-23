/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.servicehouse.energyconsumption.service;

import com.servicehouse.energyconsumption.domainobject.MeterReadingDO;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;
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
public class MeterServiceTest {

    @Autowired
    private MeterService instance;

    /**
     * Test of findAllReadings method, of class MeterService.
     */
    @Test
    public void testFindAllReadings() {
        System.out.println("findAllReadings");
        List<MeterReadingDO> expResult = null;
        List<MeterReadingDO> result = instance.findAllReadings();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of updateReading method, of class MeterService.
     */
    @Test
    public void testUpdateReading() {
        System.out.println("updateReading");
        MeterReadingDO m = null;
        MeterReadingDO expResult = null;
        MeterReadingDO result = instance.updateReading(m);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of deleteMeterReading method, of class MeterService.
     */
    @Test
    public void testDeleteMeterReading() {
        System.out.println("deleteMeterReading");
        long readingId = 0L;
        instance.deleteMeterReading(readingId);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of createReading method, of class MeterService.
     */
    @Test
    public void testCreateReading_MeterReadingDOArr() throws Exception {
        System.out.println("createReading");
        MeterReadingDO[] readings = null;
        instance.createReading(readings);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of createReading method, of class MeterService.
     */
    @Test
    public void testCreateReading_MeterReadingDO() {
        System.out.println("createReading");
        MeterReadingDO reading = null;
        instance.createReading(reading);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}
