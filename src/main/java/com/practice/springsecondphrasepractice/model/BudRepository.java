package com.practice.springsecondphrasepractice.model;

import com.practice.springsecondphrasepractice.model.entity.Bud;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface BudRepository extends JpaRepository<Bud, String> {
    Bud findByBudYmd(String budYmd);

    @Query(value = "select * from test.bud where bud_type = 'Y' and bud_ymd between ?1 and ?2", nativeQuery = true)
    List<Bud> getFilteredWorkDayBud(String startDay, String endDay);

    @Query(value = "select * from test.bud where bud_type = 'Y' order by bud_ymd", nativeQuery = true)
    List<Bud> getWorkDayBud();
}
