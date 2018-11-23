/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.servicehouse.energyconsumption.service;

import com.servicehouse.energyconsumption.domainobject.ProfileDO;
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
public class ProfileServiceTest {

    @Autowired
    private ProfileService instance;

    /**
     * Test of findProfileByName method, of class ProfileService.
     */
    @Test
    public void testFindProfileByName() {
        System.out.println("findProfileByName");
        String name = "";
        List<ProfileDO> expResult = null;
        List<ProfileDO> result = instance.findProfileByName(name);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of findAllProfiles method, of class ProfileService.
     */
    @Test
    public void testFindAllProfiles() {
        System.out.println("findAllProfiles");
         List<ProfileDO> expResult = null;
        List<ProfileDO> result = instance.findAllProfiles();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of updateProfile method, of class ProfileService.
     */
    @Test
    public void testUpdateProfile() {
        System.out.println("updateProfile");
        ProfileDO p = null;
        ProfileDO expResult = null;
        ProfileDO result = instance.updateProfile(p);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of removeProfile method, of class ProfileService.
     */
    @Test
    public void testRemoveProfile() {
        System.out.println("removeProfile");
        Long profileId = null;
        instance.removeProfile(profileId);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of deleteProfile method, of class ProfileService.
     */
    @Test
    public void testDeleteProfile() {
        System.out.println("deleteProfile");
        long profileId = 0L;
        instance.deleteProfile(profileId);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of createProfile method, of class ProfileService.
     */
    @Test
    public void testCreateProfile() throws Exception {
        System.out.println("createProfile");
        ProfileDO[] profiles = null;
        instance.createProfile(profiles);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}
