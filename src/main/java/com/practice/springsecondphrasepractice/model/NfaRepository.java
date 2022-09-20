package com.practice.springsecondphrasepractice.model;

import com.practice.springsecondphrasepractice.model.entity.Nfa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface NfaRepository extends JpaRepository<Nfa, String> {
    Nfa findByNfaUuid(String nfaUuid);

    @Query(value = "select * from test.nfa where nfa_subject like %?1% and nfa_enable = 'Y' and nfa_uuid between ?2 and ?3 ;",nativeQuery = true)
    List<Nfa> getFilteredNfa(String subject,String startDay, String endDay);
}
