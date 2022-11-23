package com.edu.graduationproject.service;

import com.edu.graduationproject.entity.Voucher;

import java.util.List;
import java.util.Optional;

public interface VoucherService {

    Voucher create(Voucher voucher);

    Voucher update(Integer id, Voucher voucher);

    Voucher deleteById(Integer id);

    List<Voucher> findAll();

    Optional<Voucher> findById(Integer id);

    Voucher findByCode(String code);
}
