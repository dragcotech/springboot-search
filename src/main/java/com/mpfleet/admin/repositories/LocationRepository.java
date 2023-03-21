package com.mpfleet.admin.repositories;

import com.mpfleet.admin.models.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LocationRepository extends JpaRepository<Location, Long> {

    @Query(value = "SELECT l FROM Location l WHERE l.address LIKE %?1% OR l.description LIKE %?1% OR l.details LIKE %?1%")
    List<Location> findByKeyword(String keyword);
}
