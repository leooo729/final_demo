package com.practice.springsecondphrasepractice.model;

import com.practice.springsecondphrasepractice.model.entity.Nfa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NfaRepository extends JpaRepository<Nfa, String> {
    Nfa findByNfaUuid(String nfaUuid);

}
