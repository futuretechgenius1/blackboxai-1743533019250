package com.example.rules.controller;

import com.example.rules.model.Rule;
import com.example.rules.service.RuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class RuleController {
    
    private static final Logger logger = LoggerFactory.getLogger(RuleController.class);
    
    @Autowired
    private RuleService ruleService;
    
    @GetMapping("/rules")
    public ResponseEntity<List<Rule>> getAllRules() {
        logger.info("GET request to fetch all rules");
        List<Rule> rules = ruleService.getAllRules();
        return ResponseEntity.ok(rules);
    }
    
    @GetMapping("/rules/{id}")
    public ResponseEntity<Rule> getRuleById(@PathVariable String id) {
        logger.info("GET request to fetch rule with id: {}", id);
        Rule rule = ruleService.getRule(id);
        return ResponseEntity.ok(rule);
    }
    
    @PostMapping("/rules")
    public ResponseEntity<Rule> createRule(@RequestBody Rule rule) {
        logger.info("POST request to create new rule");
        Rule createdRule = ruleService.addRule(rule);
        return ResponseEntity.ok(createdRule);
    }
    
    @PutMapping("/rules/{id}")
    public ResponseEntity<Rule> updateRule(@PathVariable String id, @RequestBody Rule ruleDetails) {
        logger.info("PUT request to update rule with id: {}", id);
        Rule updatedRule = ruleService.updateRule(id, ruleDetails);
        return ResponseEntity.ok(updatedRule);
    }
    
    @DeleteMapping("/rules/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteRule(@PathVariable String id) {
        logger.info("DELETE request to remove rule with id: {}", id);
        ruleService.deleteRule(id);
        
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, String>> handleException(Exception e) {
        logger.error("Error occurred: ", e);
        Map<String, String> response = new HashMap<>();
        response.put("error", e.getMessage());
        return ResponseEntity.badRequest().body(response);
    }
}