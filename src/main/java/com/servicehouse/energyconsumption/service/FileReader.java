/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.servicehouse.energyconsumption.service;

import com.servicehouse.energyconsumption.domainobject.FileLocation;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.function.Supplier;
import java.util.stream.Stream;

/**
 *
 * @author johnson3yo
 */
public class FileReader {
    
    public Supplier<Stream<String>> readFile( String file,FileLocation fileType){
        
                return () -> {
                    try {
                        switch (fileType) {
                            case CLASSPATH:
                                return Files.lines(Paths.get(ClassLoader.getSystemResource(file).toURI()));
                            case FILEDIR:
                                return Files.lines(Paths.get(file));
                        }
                    } catch (IOException | URISyntaxException ex) {
                        // ex.printStackTrace();
                    }
                    return null;
                };
    }
}
