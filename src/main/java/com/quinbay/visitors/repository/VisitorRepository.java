package com.quinbay.visitors.repository;

import com.quinbay.visitors.models.Record;
import com.quinbay.visitors.models.Request;
import com.quinbay.visitors.models.Visitors;
import org.apache.kafka.common.protocol.types.Schema;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface VisitorRepository extends JpaRepository<Visitors,Integer> {

//    List<Record> findByVid(Integer empid);
//    Optional<Visitors> findByVid(Integer vid);
Optional<Request> findByVisitorId(int id);

//    Optional<Visitors> findByI(int
}
