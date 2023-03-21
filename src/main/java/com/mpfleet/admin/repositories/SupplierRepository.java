package com.mpfleet.admin.repositories;

import com.mpfleet.admin.models.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SupplierRepository extends JpaRepository<Supplier, Long> {

    @Query(value = "SELECT s FROM Supplier s WHERE s.website LIKE %?1% OR s.name LIKE %?1%" +
            "OR s.email LIKE %?1%")
    List<Supplier> findByKeyword(String keyword);
}
