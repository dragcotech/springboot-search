package com.mpfleet.admin.repositories;

import com.mpfleet.admin.models.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContactRepository extends JpaRepository<Contact, Long> {

    @Query(value = "SELECT c FROM Contact c WHERE c.email LIKE %?1% OR c.firstname LIKE %?1% OR c.lastname LIKE %?1%" +
            "OR c.mobile LIKE %?1% OR c.phone LIKE %?1%")
    List<Contact> findByKeyword(String keyword);
}
