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

// List<Map<String, ProdInfoResponse>>
//prod_id,prod_kind,prod_name,prod_ename,prod_ccy,prod_enable


//    ProdInfoResponse findByProdId(String prodId);
//    @Query(value = "select prod_id as prodId,prod_kind as prodKind,prod_name as prodName,prod_ename as prodEname,prod_ccy as prodCcy,prod_enable as prodEnable FROM test.prod ", nativeQuery = true)
//    List<ProdInfoResponse> getAllProd();
//
//    @Query(value = "select prod_id as prodId,prod_kind as prodKind,prod_name as prodName,prod_ename as prodEname,prod_ccy as prodCcy,prod_enable as prodEnable where prod_enable = 'Y' and prod_kind = ?1", nativeQuery = true)
//    List<ProdInfoResponse> getEnableProdByKind(String prodKind);
//
//    @Query(value = "select prod_id as prodId,prod_kind as prodKind,prod_name as prodName,prod_ename as prodEname,prod_ccy as prodCcy,prod_enable as prodEnable where prod_enable = 'Y' and prod_ccy = ?1", nativeQuery = true)
//    List<ProdInfoResponse> getEnableProdByCcy(String prodCcy);

//https://blog.csdn.net/Sun_lightYY/article/details/118577413?spm=1001.2101.3001.6661.1&utm_medium=distribute.pc_relevant_t0.none-task-blog-2%7Edefault%7ECTRLIST%7ERate-1-118577413-blog-87878685.pc_relevant_default&depth_1-utm_source=distribute.pc_relevant_t0.none-task-blog-2%7Edefault%7ECTRLIST%7ERate-1-118577413-blog-87878685.pc_relevant_default&utm_relevant_index=1

