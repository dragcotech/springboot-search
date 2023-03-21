package com.mpfleet.admin.repositories;

import com.mpfleet.admin.models.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CountryRepository extends JpaRepository<Country, Long> {

    @Query(value = "SELECT c FROM Country c WHERE c.name LIKE %?1% OR c.continent LIKE %?1% OR c.code LIKE %?1% OR c.nationality LIKE %?1%")
    List<Country> findByKeyword(String keyword);
}
