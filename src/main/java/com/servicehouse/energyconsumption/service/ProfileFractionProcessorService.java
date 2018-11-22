/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.servicehouse.energyconsumption.service;

import com.servicehouse.energyconsumption.domainobject.FileLocation;
import static com.servicehouse.energyconsumption.domainobject.FileLocation.CLASSPATH;
import static com.servicehouse.energyconsumption.domainobject.FileLocation.FILEDIR;
import com.servicehouse.energyconsumption.domainobject.ProfileDO;
import com.servicehouse.energyconsumption.exception.EnergyConsumptionException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.function.Supplier;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.springframework.stereotype.Service;

/**
 *
 * @author johnson3yo
 */
@Service
public class ProfileFractionProcessorService extends FileReader {

    /*
     * @param fileType refers to where to obtain the csv file
     * @param file specifies the file name either in a path or in a directory
     * @profile refers to profile been processed
     * Java 8 Streams is heavily used in processing the lines of the file
     *
    
     */
    public void processProfileFractionClassPathFile(String file, FileLocation fileType) throws EnergyConsumptionException {

        Supplier<Stream<String>> supplier
                = readFile(file, fileType);

        if (supplier.get() == null) {
            throw new EnergyConsumptionException(" Not file for processing in the stream");
        }

        /*
        returns a list of distinct profiles
        
         */
        List<String> distinctProfiles = supplier.get().filter(header -> !header.split(",")[0].
                startsWith("Month")).
                map(a -> a.split(",")[1]).distinct().collect(Collectors.toList());

        /*
        process distinct profiles for the fractions 
        
         */
        for (String profile : distinctProfiles) {
            List<Double> fractions = supplier.get().filter(header -> !header.split(",")[0].
                    startsWith("Month")).
                    filter(a -> a.split(",")[1].equals(profile)).map(fraction -> Double.valueOf(fraction.split(",")[2])).collect(Collectors.toList());
            if (!validateFraction(fractions)) {
                throw new EnergyConsumptionException("fraction constraint not equal to 1 ");
            }
        }
//
//        if (!isValidMonths(months)) {
//            throw new EnergyConsumptionException("months profile data incomplete ");
//        }

    }

    public void processProfileFractionHttpRequest(ProfileDO[] profiles) throws EnergyConsumptionException {
        
        List<Double> fractions = Stream.of(profiles).map(fraction -> fraction.getFraction()).collect(Collectors.toList());

        if (!validateFraction(fractions)) {
            throw new EnergyConsumptionException("fraction constraint not equal to 1 ");
        }
    }

    private static boolean validateMonths(List<String> ms) {
        Set<String> months = new HashSet(ms);
        List<Date> dateMonths = new ArrayList<Date>();

        for (String month : months) {
            Date date;
            try {
                date = new SimpleDateFormat("MMM", Locale.ENGLISH)
                        .parse(month);
                dateMonths.add(date);
            } catch (ParseException ex) {
                Logger.getLogger(ProfileFractionProcessorService.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        Collections.sort(dateMonths, new Comparator<Date>() {
            public int compare(Date arg0, Date arg1) {
                return arg0.compareTo(arg1);
            }
        });
        return dateMonths.size() == 12;
    }

    private static boolean validateFraction(List<Double> filtered) {

        return filtered.stream().
                reduce(FractionUtility::addData).filter(FractionUtility.isTotalEquals1()).isPresent();
    }

    private boolean isValidMonthprofile(List months) {
        return months.size() == 12;
    }

}
