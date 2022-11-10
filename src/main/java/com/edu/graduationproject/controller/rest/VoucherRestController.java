package com.edu.graduationproject.controller.rest;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.edu.graduationproject.entity.Voucher;
import com.edu.graduationproject.service.VoucherService;

@CrossOrigin("*")
@RestController
public class VoucherRestController {

    private static final Logger LOGGER = LoggerFactory.getLogger(VoucherRestController.class);

    @Autowired
    VoucherService voucherService;

    @GetMapping("/rest/vouchers")
    public ResponseEntity<List<Voucher>> getAll() {
        return ResponseEntity.ok(voucherService.findAll());
    }

    @GetMapping("/rest/vouchers/id/{id}")
    public ResponseEntity<Voucher> findById(@PathVariable Integer id) {
        Optional<Voucher> findVoucher = voucherService.findById(id);
        return ResponseEntity.ok(findVoucher.orElse(null));
    }

    @GetMapping("/rest/vouchers/code/{code}")
    public ResponseEntity<Voucher> findByCode(@PathVariable String code) {
        Optional<Voucher> findVoucher = voucherService.findByCode(code);
        return ResponseEntity.ok(findVoucher.orElse(null));
    }
}
