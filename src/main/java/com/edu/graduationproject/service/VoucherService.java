package com.edu.graduationproject.service;

import java.util.List;
import java.util.Optional;

import com.edu.graduationproject.entity.Voucher;

public interface VoucherService {

    Optional<Voucher> findById(Integer id);

    Optional<Voucher> findByCode(String code);

    List<Voucher> findAll();
}
