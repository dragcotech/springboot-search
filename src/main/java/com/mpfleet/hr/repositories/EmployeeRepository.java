package com.mpfleet.hr.repositories;

import com.mpfleet.hr.models.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
	Employee findByUsername(String un);

	@Query(value = "SELECT e FROM Employee e WHERE e.email LIKE %?1% OR e.firstName LIKE %?1% OR e.lastName LIKE %?1%" +
			"OR e.fullName LIKE %?1% OR e.employeeType.description LIKE %?1% OR e.country.name LIKE %?1%" +
			"OR e.email LIKE %?1% OR e.jobTitle.description LIKE %?1% OR e.gender LIKE %?1% OR e.socialSecurityNumber LIKE %?1%" +
			"OR e.state.capital LIKE %?1% OR e.maritalStatus LIKE %?1% OR e.initials LIKE %?1%")
	List<Employee> findByKeyword(String keyword);
}
