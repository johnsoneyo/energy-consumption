/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.servicehouse.energyconsumption.service;

import com.servicehouse.energyconsumption.domainobject.ProfileDO;
import com.servicehouse.energyconsumption.exception.EnergyConsumptionException;
import com.servicehouse.energyconsumption.repository.ProfileRepository;
import java.util.List;
import java.util.stream.Stream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author johnson3yo
 */
@Service
public class ProfileService {

    @Autowired
    private ProfileFractionProcessorService processorService;

    @Autowired
    private ProfileRepository profileRepository;

    public List<ProfileDO> findProfileByName(String name) {
        return profileRepository.findByName(name);
    }

    public List<ProfileDO> findAllProfiles() {
        return profileRepository.findAll();
    }

    public ProfileDO updateProfile(ProfileDO p) {
        return profileRepository.save(p);
    }

    public void removeProfile(Long profileId) {
        ProfileDO found = profileRepository.findById(profileId).get();
        profileRepository.delete(found);
    }

    public void deleteProfile(long profileId) {
        profileRepository.deleteById(profileId);
    }

    /*
    profile fraction method maintains legacy implementation to validate a stream of 
    records
     */
    public void createProfile(ProfileDO []profiles) throws EnergyConsumptionException {
        Stream.of(profiles);
        processorService.processProfileFractionHttpRequest(profiles);
        Stream.of(profiles).forEach(p -> profileRepository.save(p));
    }
    
    
}
