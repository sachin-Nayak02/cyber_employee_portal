package com.cyber_employee_portal.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cyber_employee_portal.entity.AdminUsers;

@Repository
public interface AdminUserRepository extends JpaRepository<AdminUsers,Long >{
	Optional<AdminUserRepository> findByEmail(String email);
	 boolean existsByEmail(String email);
	 Optional<AdminUsers> findByEmployeeId(String employeeId);
 
}
