package com.lucipurr.tax.controller;

import com.lucipurr.tax.abstractions.ITaxService;
import com.lucipurr.tax.kafka.KafkaPublisher;
import com.lucipurr.tax.model.ClientResponseVO;
import com.lucipurr.tax.model.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/tax")
@Slf4j
@RestController
@CrossOrigin
public class TaxController {
    @Autowired
    ITaxService iTaxService;

    @Autowired
    KafkaPublisher kafkaPublisher;

    @GetMapping(value = "/calculate/{empId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ClientResponseVO<Response> saveData(@PathVariable String empId) {
        return ClientResponseVO
                .<Response>ok()
                .desc("Tax Calculated.")
                .data(iTaxService.calculateTax(empId))
                .build();
    }

    @GetMapping(value = "/producer")
    public String producer(@RequestParam("message") String message) {
        kafkaPublisher.send(message);
        return "Message sent to the Kafka Topic java_in_use_topic Successfully";
    }
}
