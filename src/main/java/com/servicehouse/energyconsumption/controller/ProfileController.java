package com.servicehouse.energyconsumption.controller;

import com.servicehouse.energyconsumption.domainobject.ProfileDO;
import com.servicehouse.energyconsumption.exception.EnergyConsumptionException;
import com.servicehouse.energyconsumption.service.ProfileService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author johnson3yo
 */
@RestController
@RequestMapping("v1/profiles")
public class ProfileController {

    /*
    The ProfileDO Domain Object is used as the class for the request body , ideally the payload should 
    be a Data transfer object which is mapped (transformed) to a domain object to save an entity . this was done 
    essentially the save the demo time
    */
    
    /*
    The ResponseBody was also decorated with @ResponseStatus, this approach also
    doesnt give control over http header messages , An ideal approach to manage 
    http responses would be to use a ResponseEntity
    
    */
    
    @Autowired
    private ProfileService service;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ProfileDO> getProfiles() {
        return service.findAllProfiles();
    }

    @PutMapping("/{profileId}")
    @ResponseStatus(HttpStatus.OK)
    public ProfileDO updateProfile(@PathVariable long profileId, @RequestBody ProfileDO p) {
        p.setId(profileId);
        return service.updateProfile(p);
    }

    @DeleteMapping("/{profileId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteProfile(@PathVariable long profileId) {
        service.deleteProfile(profileId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createProfile(@RequestBody ProfileDO[] profiles) throws EnergyConsumptionException {
        service.createProfile(profiles);
    }
    
    
    
}
