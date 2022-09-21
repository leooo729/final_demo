package com.practice.springsecondphrasepractice.model;

import com.practice.springsecondphrasepractice.controller.dto.response.ProdInfoResponse;
import com.practice.springsecondphrasepractice.model.entity.Prod;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
@Repository
public interface ProdRepository extends JpaRepository<Prod, String> {

    Prod findByProdId(String prodId);
    List<Prod> findByProdKind(String prodKind);
    List<Prod> findByProdCcy(String prodCcy);

    @Query(value = "select * from test.prod order where prod_enable = 'Y'", nativeQuery = true)
    List<Prod> getEnableProd();

    @Query(value = "select * from test.prod where prod_enable = 'Y' and prod_kind = ?1", nativeQuery = true)
    List<Prod> getEnableProdByKind(String prodKind);

    @Query(value = "select * from test.prod where prod_enable = 'Y' and prod_ccy = ?1", nativeQuery = true)
    List<Prod> getEnableProdByCcy(String prodCcy);

}

