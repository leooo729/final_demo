package com.practice.springsecondphrasepractice.model;

import com.practice.springsecondphrasepractice.model.entity.Nfa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface NfaRepository extends JpaRepository<Nfa, String> {
    Nfa findByNfaUuid(String nfaUuid);

    @Query(value = "select * from test.nfa where nfa_enable = 'Y' ;",nativeQuery = true)
    List<Nfa> getEnabledNfa();

}
