package com.quinbay.visitors.controllers;

import com.quinbay.visitors.helpers.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.quinbay.visitors.interfaces.RequestInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.*;
@RestController
@RequestMapping("/api")
public class StatisticsController {

    @Autowired
    RequestInterface requestInterface;


    @GetMapping("/getStatistics")
    public List <GetStatistics> getStatistics(@RequestParam String date){
        return requestInterface.getStatistics(date);
    }
}
