package com.lucipurr.tax.controller;

import com.lucipurr.tax.abstractions.ITaxService;
import com.lucipurr.tax.model.Employee;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/tax")
@Slf4j
@RestController
@CrossOrigin
public class TaxController {
    @Autowired
    ITaxService iTaxService;

    @PostMapping(value = "/test", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> testTax(@RequestBody Employee employee) {
        return ResponseEntity.ok(iTaxService.netTax(employee));
    }

    @GetMapping(value = "/calculate/{empId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> saveData(@PathVariable String empId) {
        return ResponseEntity.ok(iTaxService.calculateTax(empId));
    }
}