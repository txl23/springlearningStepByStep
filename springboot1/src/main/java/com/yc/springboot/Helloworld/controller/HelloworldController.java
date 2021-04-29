package com.yc.springboot.Helloworld.controller;

import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.omg.CORBA.Environment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: testspring
 * @description:
 * @author: 汤僖龙
 * @create: 2021-04-24 10:02
 */
@RestController
public class HelloworldController {
    private  static Log log= LogFactory.getLog(HelloworldController.class);


    @GetMapping("/hello")
    public String hello(@RequestParam(value = "name", defaultValue = "World") String name) {
        return String.format("Hello %s!", name);
    }
}
