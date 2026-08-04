// ConnectionRepository.java
package com.cyber_employee_portal.repository;

import com.cyber_employee_portal.entity.Connection;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ConnectionRepository extends JpaRepository<Connection, Long> {
    List<Connection> findByEmployeeId(Long employeeId);
}