/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.servicehouse.energyconsumption.service;

import com.servicehouse.energyconsumption.domainobject.FileLocation;
import com.servicehouse.energyconsumption.domainobject.MeterReadingDO;
import com.servicehouse.energyconsumption.domainobject.ProfileDO;
import com.servicehouse.energyconsumption.exception.EnergyConsumptionException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author johnson3yo
 */
@Service
public class MeterReadingProcessorService extends FileReader {

    @Autowired
    private ProfileService profileService;
    @Autowired
    private MeterService meterService;

    public void processMeterReadingFile(String file, FileLocation fileType) throws EnergyConsumptionException {

        Supplier<Stream<String>> supplier
                = readFile(file, fileType);

        if (supplier.get() == null) {
            throw new EnergyConsumptionException(" No file for processing in the stream");
        }

        /*
        Distinct profile is obtained from the supplier and fraction for profiles 
        is checked for a period of 12 months 
        
         */
        List<String> profiles = supplier.get().filter(items -> items.split(",").length > 1).filter(header -> !header.split(",")[0].
                startsWith("Profile"))
                .map(meter -> meter.split(",")[1]).distinct().collect(Collectors.toList());

        /*
           validation to ensure profile fraction occurs here
         */
        if (!hasProfileFractions(profiles)) {
            throw new EnergyConsumptionException(String.format(" Fraction for profile is necessary"));
        }

        /*
        This block <supplies> the meters from a stream and obtains the respective readings
        assuming 12 months of meter reading , if reading is not progressive
        the meter reading is ignored and the next is processed from the supplier 
        
         */
        List<String> meters = supplier.get().filter(items -> items.split(",").length > 1).filter(header -> !header.split(",")[0].
                startsWith("MeterID"))
                .map(meter -> meter.split(",")[0]).collect(Collectors.toList());

        for (String meter : meters) {
            List<Integer> readings = supplier.get().
                    filter(items -> items.split(",").length > 1).
                    filter(header -> !header.split(",")[0].
                    startsWith("MeterID")).
                    filter(a -> a.split(",")[0].equals(meter)).
                    map(reading -> Integer.parseInt(reading.split(",")[3])).
                    collect(Collectors.toList());

            /*
            validation happens here per distinct meter 
             */
            if (validateReading(readings)) {
                /*
                go ahead to process meter reading 
                saving to DB
                 */
                supplier.get().filter(items -> items.split(",").length > 1).
                    filter(header -> !header.split(",")[0].
                    startsWith("MeterID")).
                        map( fields -> { return new MeterReadingDO(fields.split(",")[0],fields.split(",")[0],
                                fields.split(",")[0],
                                Integer.parseInt(fields.split(",")[0]));})
                        .peek(reading -> this.meterService.createReading(reading));
            }
        }

    }

    private static boolean validateReading(List<Integer> readings) {
        List<Integer> tobeSorted = new ArrayList();
        tobeSorted.addAll(readings);
        Collections.sort(tobeSorted);
        return readings.equals(tobeSorted);
    }

    private boolean hasProfileFractions(List<String> profiles) {

        for (String profile : profiles) {
            List<ProfileDO> records = profileService.findProfileByName(profile);
            if (records.size() != 12) {
                return false;
            }
        }
        return true;
    }

    public void processMeterReadingHttpRequest(MeterReadingDO[] readings) throws EnergyConsumptionException {
         /*
        Distinct profile is obtained from the stream and fraction for profiles 
        is checked for a period of 12 months 
        
         */
        List<String> profiles = Stream.of(readings)
                .map(profile -> profile.getProfile()).distinct().collect(Collectors.toList());

        /*
           validation to ensure profile fraction occurs here
         */
        if (!hasProfileFractions(profiles)) {
            throw new EnergyConsumptionException(String.format(" Fraction for profile is necessary"));
        }

        /*
        This block <supplies> the meters from a stream and obtains the respective readings
        assuming 12 months of meter reading , if reading is not progressive
        the meter reading is ignored and the next is processed from the supplier 
        
         */
        List<String> meters =  Stream.of(readings)
                .map(meter ->  meter.getMeterId()).distinct().collect(Collectors.toList());

        for (String meter : meters) {
            List<Integer> read = Stream.of(readings).filter(e -> e.getMeterId().equals(meter)).map( found -> found.getMeterReading()). collect(Collectors.toList());

            /*
            validation happens here per distinct meter 
             */
            if (validateReading(read)) {
                /*
                go ahead to process meter reading 
                saving to DB
                 */
               Stream.of(readings)
                       
                        .peek(reading -> this.meterService.createReading(reading));
            }
        }

    }
}
