package com.edu.graduationproject.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.edu.graduationproject.entity.Voucher;
import org.springframework.data.jpa.repository.Query;

public interface VoucherRepository extends JpaRepository<Voucher, Integer> {

    @Query("SELECT v FROM Voucher v WHERE v.code = :code")
    Optional<Voucher> findByCode(String code);

}
