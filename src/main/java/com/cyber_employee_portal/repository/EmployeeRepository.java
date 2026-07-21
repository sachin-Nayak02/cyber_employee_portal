package com.cyber_employee_portal.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cyber_employee_portal.entity.Employee;
import java.util.List;
@Repository 
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

	Optional<Employee> findByEmail(String email);

    @Query("SELECT e.id FROM Employee e WHERE e.email = :email")
    boolean existsByEmail(String email);
    @Query("SELECT e FROM Employee e WHERE MONTH(e.dateOfBirth) = MONTH(CURRENT_DATE) AND DAY(e.dateOfBirth) = DAY(CURRENT_DATE)")
    List<Employee> findTodayBirthdays();
    @Query("SELECT e FROM Employee e WHERE MONTH(e.joiningDate) = MONTH(CURRENT_DATE) AND DAY(e.joiningDate) = DAY(CURRENT_DATE) AND YEAR(e.joiningDate) < YEAR(CURRENT_DATE)")
    List<Employee> findTodayAnniversaries();
    @Query("""
    		SELECT e FROM Employee e
    		WHERE
    		(MONTH(e.dateOfBirth) > MONTH(CURRENT_DATE))
    		OR
    		(MONTH(e.dateOfBirth) = MONTH(CURRENT_DATE)
    		AND DAY(e.dateOfBirth) > DAY(CURRENT_DATE))
    		ORDER BY MONTH(e.dateOfBirth), DAY(e.dateOfBirth)
    		""")
    		List<Employee> findUpcomingBirthdays();
    List<Employee> findByGender(String gender);
	
}
