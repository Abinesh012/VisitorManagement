package com.quinbay.visitors.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.persistence.Table;


@Entity
@Table(name="visitors")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Visitors {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int visitorId;
    String name;
    long phone;

}
