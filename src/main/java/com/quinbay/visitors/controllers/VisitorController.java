package com.quinbay.visitors.controllers;

import com.quinbay.visitors.interfaces.VisitorInterface;
import com.quinbay.visitors.models.Visitors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")

public class VisitorController{
        @Autowired
        VisitorInterface visitorInterface;

        @PostMapping("/createVisitor")
        public String createVisitor(@RequestBody Visitors visitors) {
        return visitorInterface.createVisitor(visitors);
    }

        @GetMapping("/allVisitors")
        public List<Visitors> getAllVisitors(){
        return visitorInterface.getAllVisitors();
    }

        @GetMapping("/visitors/{id}")
        public ResponseEntity<Visitors> getVisitorById(@PathVariable(value = "id") Integer visitorId){
            return visitorInterface.getVisitor(visitorId);
        }
        @DeleteMapping("/deleteVisitor/{id}")
        public ResponseEntity<HttpStatus> deleteVisitor(@RequestParam int visitorID){
            return visitorInterface.deleteUser(visitorID);
        }


}
