package com.main.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.main.dbObjects.EmployeeInfoDBO;

 

public interface IEmployeeInfoRepository extends JpaRepository<EmployeeInfoDBO, Integer> {
    
	@Query(value = "SELECT * FROM MVC_EMP_INFO WHERE RECORD_STATUS='Active' ORDER BY ID", nativeQuery = true)
	public List<EmployeeInfoDBO> getAllEmployees();
}
