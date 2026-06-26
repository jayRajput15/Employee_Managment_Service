package com.example.EmployeeOrg.repositories;

import com.example.EmployeeOrg.entities.DepartmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<DepartmentEntity, Long> {
}
