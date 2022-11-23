package com.edu.graduationproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.edu.graduationproject.entity.Voucher;
import org.springframework.data.jpa.repository.Query;

public interface VoucherRepository extends JpaRepository<Voucher, Integer> {
    @Query("SELECT v FROM Voucher v WHERE v.code =?1")
    public Voucher findByCode(String code);
}
