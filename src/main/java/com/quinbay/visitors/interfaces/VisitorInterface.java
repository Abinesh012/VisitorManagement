package com.quinbay.visitors.interfaces;

import com.quinbay.visitors.models.Visitors;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface VisitorInterface {
    String createVisitor(Visitors v);
    ResponseEntity<Visitors> getVisitor(int vid);

    List<Visitors> getAllVisitors();

    ResponseEntity<HttpStatus> deleteUser(int visitorID);

}
