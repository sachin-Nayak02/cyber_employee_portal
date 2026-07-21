package com.cyber_employee_portal.repository;

import java.util.*;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cyber_employee_portal.entity.Department;
import com.cyber_employee_portal.entity.Employee;
<<<<<<< HEAD
import java.util.List;
=======


>>>>>>> 00ea78f16764d288781d597d49997294f6715b4b
@Repository 
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

	Optional<Employee> findByEmail(String email);
<<<<<<< HEAD

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
=======
    Optional<Employee> findByEmployeeId(String employeeId);
    boolean existsByEmail(String email);
    boolean existsByEmployeeId(String employeeId);
    List<Employee> findByDepartment_Id(Long departmentId);
    
    List<Employee> findByDepartment_IdAndNameContainingIgnoreCase(Long departmentId, String name);
}
>>>>>>> 00ea78f16764d288781d597d49997294f6715b4b
