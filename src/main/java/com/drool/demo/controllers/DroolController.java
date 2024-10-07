package com.drool.demo.controllers;

import com.drool.demo.object.Order;
import com.drool.demo.services.DroolService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/drool")
public class DroolController {
    DroolService droolService;

    @PostMapping("")
    ResponseEntity<?> droolGet(@RequestBody Order order){
        System.out.println(order.getName());
        droolService.calculateDiscount(order);
        System.out.println(order.getPrice());
        if (order.isBlocked()){
            return ResponseEntity.badRequest().body("invalid name or price to high");
        }
        return ResponseEntity.ok("ordine inviato !");
    }

    public DroolController(DroolService droolService) {
        this.droolService = droolService;
    }
}
