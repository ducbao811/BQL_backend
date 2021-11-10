package com.bql_backend.bql_backend.controller;

import com.bql_backend.bql_backend.service.PaymentService;
import com.stripe.exception.StripeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("payment")
@CrossOrigin("http://localhost:3000")
public class PaymentController {

    @Autowired
    PaymentService paymentService;

    @PostMapping("/create")
    public ResponseEntity<String> getClientSecret(@RequestParam Double total){
        try {
            String client_secret = paymentService.createIntent(total);
            return new ResponseEntity<>(client_secret, HttpStatus.OK);
        } catch (StripeException e) {
            return new ResponseEntity<>("There is no way for getting the secret key", HttpStatus.BAD_REQUEST);
        }
    }
}
