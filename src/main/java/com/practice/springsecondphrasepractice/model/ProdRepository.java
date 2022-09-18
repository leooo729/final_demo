package com.practice.springsecondphrasepractice.model;

import com.practice.springsecondphrasepractice.controller.dto.response.ProdInfoResponse;
import com.practice.springsecondphrasepractice.model.entity.Prod;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Map;

public interface ProdRepository extends JpaRepository<Prod, String> {

    @Query(value = "select prod_id,prod_kind,prod_name,prod_ename,prod_ccy,prod_enable FROM test.prod;",nativeQuery = true)//prod_id,prod_kind,prod_name,prod_ename,prod_ccy,prod_enable
    List<Map<String,ProdInfoResponse>> getAllProd();
}
