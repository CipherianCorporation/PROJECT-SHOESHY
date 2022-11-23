package com.edu.graduationproject.controller.rest;

import com.edu.graduationproject.entity.Product;
import com.edu.graduationproject.entity.Voucher;
import com.edu.graduationproject.service.VoucherService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

@CrossOrigin("*")
@RestController
public class VoucherRestController {
    private static final Logger LOGGER = LoggerFactory.getLogger(OrderRestController.class);

    @Autowired
    VoucherService voucherService;

    @GetMapping("/rest/voucher")
    public List<Voucher> getAll(){
        return voucherService.findAll();
    }

    @PostMapping("/rest/voucher")
    public ResponseEntity<Voucher> createVoucher(@RequestBody Voucher voucher){
        Optional<Voucher> v = Optional.ofNullable(voucherService.findByCode(voucher.getCode()));
        if(v.isPresent()){
            return new ResponseEntity<Voucher>(HttpStatus.FOUND);
        }else {
            voucherService.create(voucher);
            return new ResponseEntity<Voucher>(HttpStatus.OK);
        }
    }

    @PutMapping("/rest/voucher/{id}")
    public ResponseEntity<Voucher> updateVoucher(@PathVariable("id")Integer id ,@RequestBody Voucher voucher ){
        Optional<Voucher> v = voucherService.findById(id);
        if(v.isPresent()){
            voucherService.update(id,voucher);
            return new ResponseEntity<Voucher>(HttpStatus.OK);
        }else {
            return new ResponseEntity<Voucher>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/rest/voucher/{id}")
    public Optional<Voucher> findById(@PathVariable("id")Integer id){
        return voucherService.findById(id);
    }

    @DeleteMapping("/rest/voucher/{id}")
    public ResponseEntity<Voucher> deleteVoucher(@PathVariable("id")Integer id){
        Optional<Voucher> v = Optional.ofNullable(voucherService.deleteById(id));
        if(!v.isPresent()){
            return new ResponseEntity<Voucher>(HttpStatus.NOT_FOUND);
        }else {
            voucherService.deleteById(id);
            return new ResponseEntity<Voucher>(HttpStatus.OK);
        }
    }
}
