package com.example.rules.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "rules")
public class Rule {
    @Id
    private String id;
    private String ruleType;
    private String mdState;
    private String shipToState;
    private String zipCode;
    private String channel;
    private String regCatCode;
    private String drugSchedule;
    private Integer refillNumber;
    private Integer quantity;
    private Integer daysSupply;
    private String userLocation;
    private String dispensingLocation;
    private String protocol;
    private Integer daysAgo;
    private Integer maxDaysSupply;
    private Integer maxQuantity;
    private Integer maxRefill;
    private Integer maxDaysAllowedToExpiryDate;
}