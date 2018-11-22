/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.servicehouse.energyconsumption.service;

import java.util.function.Predicate;

/**
 *
 * @author johnson3yo
 */
public class FractionUtility {

    public static double addData(double fr1, double fr2) {
        return fr1 + fr2;
    }

    public static Predicate<Double> isTotalEquals1() {
        return total -> total == 1.0;
    }
}
