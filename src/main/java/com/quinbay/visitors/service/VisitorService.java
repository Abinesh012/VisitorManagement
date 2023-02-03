package com.quinbay.visitors.service;

import com.quinbay.visitors.interfaces.VisitorInterface;
import com.quinbay.visitors.models.Visitors;
import com.quinbay.visitors.repository.VisitorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service


public class VisitorService implements VisitorInterface {
    @Autowired
    VisitorRepository visitorRepository;
    @Override
    public String createVisitor(Visitors v) {
        try{
            Visitors _u = visitorRepository.save(v);
            return "Visitor created successfully!!!";
        } catch(Exception e){
            return "Visitor cannot be created!!!";
        }
    }

    @Override
    public ResponseEntity<Visitors> getVisitor(int uid){
        Optional<Visitors> visitorData = visitorRepository.findById(uid);
        if(visitorData.isPresent()){
            return new ResponseEntity<>(visitorData.get(), HttpStatus.OK);
        } else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public List<Visitors> getAllVisitors() {
        return visitorRepository.findAll();
    }

    @Override
    public ResponseEntity <HttpStatus> deleteUser(int visitorID) {
        try{
            visitorRepository.deleteById(visitorID);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
