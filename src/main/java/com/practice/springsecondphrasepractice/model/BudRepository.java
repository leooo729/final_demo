package com.practice.springsecondphrasepractice.model;

import com.practice.springsecondphrasepractice.model.entity.Bud;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BudRepository extends JpaRepository<Bud, String> {
    Bud findByBudYmd(String budYmd);

    @Query(value = "select * from test.bud where bud_type = 'Y' and bud_ymd between ?1 and ?2", nativeQuery = true)
    List<Bud> getFilteredWorkDayBud(String startDay, String endDay);

    @Query(value = "select * from test.bud where bud_type = 'Y'", nativeQuery = true)
    List<Bud> getWorkDayBud();
}
