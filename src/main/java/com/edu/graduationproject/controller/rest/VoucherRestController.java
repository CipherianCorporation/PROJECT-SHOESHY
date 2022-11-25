package com.edu.graduationproject.controller.rest;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
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

    @PostMapping("/rest/vouchers")
    public ResponseEntity<Voucher> createVoucher(@RequestBody Voucher voucher) {
        Optional<Voucher> v = voucherService.findByCode(voucher.getCode());
        if (v.isPresent()) {
            return new ResponseEntity<Voucher>(HttpStatus.FOUND);
        } else {
            voucherService.create(voucher);
            return new ResponseEntity<Voucher>(HttpStatus.OK);
        }
    }

    @PutMapping("/rest/vouchers/id/{id}")
    public ResponseEntity<Voucher> updateVoucher(@PathVariable("id") Integer id, @RequestBody Voucher voucher) {
        Optional<Voucher> v = voucherService.findById(id);
        if (v.isPresent()) {
            voucherService.update(id, voucher);
            return new ResponseEntity<Voucher>(HttpStatus.OK);
        } else {
            return new ResponseEntity<Voucher>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/rest/vouchers/id/{id}")
    public ResponseEntity<Voucher> deleteVoucher(@PathVariable("id") Integer id) {
        if (id == null) {
            return new ResponseEntity<Voucher>(HttpStatus.NOT_FOUND);
        } else {
            voucherService.deleteById(id);
            return new ResponseEntity<Voucher>(HttpStatus.OK);
        }
    }
}
