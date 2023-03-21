package com.mpfleet.admin.repositories;

import com.mpfleet.admin.models.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {

    @Query(value = "SELECT c FROM Client c WHERE c.phone LIKE %?1% OR c.mobile LIKE %?1% OR c.email LIKE %?1%" +
            "OR c.address LIKE %?1% OR c.name LIKE %?1% OR c.website LIKE %?1%")
    List<Client> findByKeyword(String keyword);
}
