package com.mpfleet.admin.repositories;

import com.mpfleet.admin.models.State;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StateRepository extends JpaRepository<State, Long> {

    @Query(value = "SELECT s FROM State s WHERE s.capital LIKE %?1% OR s.code LIKE %?1%")
    List<State> findByKeyword(String keyword);
}
