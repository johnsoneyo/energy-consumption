/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.servicehouse.energyconsumption.service;

import com.servicehouse.energyconsumption.domainobject.FileLocation;
import com.servicehouse.energyconsumption.domainobject.ProfileDO;
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
public class ProfileFractionProcessorServiceTest {

    @Autowired
    private ProfileFractionProcessorService service;

    @Test(expected = EnergyConsumptionException.class)
    public void testIllegalFileAccess() throws EnergyConsumptionException {
        service.processProfileFractionClassPathFile("/home/john/sample-fraction.csv", FileLocation.FILEDIR);
    }

    @Test
    public void testValidFile() throws EnergyConsumptionException {
        service.processProfileFractionClassPathFile( "valid-a-profile-fraction.csv", FileLocation.CLASSPATH);
    }

    @Test(expected = EnergyConsumptionException.class)
    public void testInValidFile() throws EnergyConsumptionException {
        service.processProfileFractionClassPathFile( "invalid-a-profile-fraction.csv", FileLocation.CLASSPATH);
    }

    /*
    here we expect the A profile fraction to throw an error
    even though the B profile is accurate
    */
    @Test(expected = EnergyConsumptionException.class)
    public void testCombinedProfileFraction() throws EnergyConsumptionException {
        service.processProfileFractionClassPathFile("valid-profile-fraction.csv", FileLocation.CLASSPATH);
    }

    @Test(expected = EnergyConsumptionException.class)
    public void testProcessProfileFractionHttpRequest() throws EnergyConsumptionException {
        ProfileDO[] collect = {new ProfileDO("A", "JAN", 0.03)};
        service.processProfileFractionHttpRequest(collect);
    }

}
