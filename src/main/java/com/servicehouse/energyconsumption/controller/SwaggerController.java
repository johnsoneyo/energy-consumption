/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.servicehouse.energyconsumption.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import springfox.documentation.annotations.ApiIgnore;

/**
 *
 * @author johnson3yo
 */
@Controller
@ApiIgnore
public class SwaggerController {
      @RequestMapping("/swagger")
    public String home()
    {
        return "redirect:swagger-ui.html";
    }
}
