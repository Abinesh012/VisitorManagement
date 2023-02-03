package com.quinbay.visitors.models;
import java.sql.Date;
import java.time.LocalDate;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import javax.persistence.*;
import javax.persistence.Table;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Entity(name="records")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Record {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int recordid;
    int vid;
    String name;
    int whomtovisit;
    String purpose;
    int approvedby;
    String comment;
    String date;
    String intime;
    String outtime;
    boolean preapproval;
}
