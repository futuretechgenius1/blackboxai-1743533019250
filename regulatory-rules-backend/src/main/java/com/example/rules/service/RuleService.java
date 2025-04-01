package com.example.rules.service;

import com.example.rules.model.Rule;
import com.example.rules.repository.RuleRepository;
import com.example.rules.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class RuleService {
    
    private static final Logger logger = LoggerFactory.getLogger(RuleService.class);
    
    @Autowired
    private RuleRepository ruleRepository;
    
    public List<Rule> getAllRules() {
        logger.info("Fetching all rules");
        return ruleRepository.findAll();
    }
    
    public Rule getRule(String id) {
        logger.info("Fetching rule with id: {}", id);
        return ruleRepository.findById(id)
            .orElseThrow(() -> {
                logger.error("Rule not found with id: {}", id);
                return new ResourceNotFoundException("Rule not found with id: " + id);
            });
    }
    
    public Rule addRule(Rule rule) {
        logger.info("Adding new rule");
        if (rule.getId() == null || rule.getId().isEmpty()) {
            rule.setId(UUID.randomUUID().toString());
        }
        Rule savedRule = ruleRepository.save(rule);
        logger.info("Added new rule with id: {}", savedRule.getId());
        return savedRule;
    }
    
    public Rule updateRule(String id, Rule ruleDetails) {
        logger.info("Updating rule with id: {}", id);
        Rule rule = getRule(id);
        
        rule.setRuleType(ruleDetails.getRuleType());
        rule.setMdState(ruleDetails.getMdState());
        rule.setShipToState(ruleDetails.getShipToState());
        rule.setZipCode(ruleDetails.getZipCode());
        rule.setChannel(ruleDetails.getChannel());
        rule.setRegCatCode(ruleDetails.getRegCatCode());
        rule.setDrugSchedule(ruleDetails.getDrugSchedule());
        rule.setRefillNumber(ruleDetails.getRefillNumber());
        rule.setQuantity(ruleDetails.getQuantity());
        rule.setDaysSupply(ruleDetails.getDaysSupply());
        rule.setUserLocation(ruleDetails.getUserLocation());
        rule.setDispensingLocation(ruleDetails.getDispensingLocation());
        rule.setProtocol(ruleDetails.getProtocol());
        rule.setDaysAgo(ruleDetails.getDaysAgo());
        rule.setMaxDaysSupply(ruleDetails.getMaxDaysSupply());
        rule.setMaxQuantity(ruleDetails.getMaxQuantity());
        rule.setMaxRefill(ruleDetails.getMaxRefill());
        rule.setMaxDaysAllowedToExpiryDate(ruleDetails.getMaxDaysAllowedToExpiryDate());
        
        Rule updatedRule = ruleRepository.save(rule);
        logger.info("Updated rule with id: {}", updatedRule.getId());
        return updatedRule;
    }
    
    public void deleteRule(String id) {
        logger.info("Deleting rule with id: {}", id);
        Rule rule = getRule(id);
        ruleRepository.delete(rule);
        logger.info("Deleted rule with id: {}", id);
    }
}