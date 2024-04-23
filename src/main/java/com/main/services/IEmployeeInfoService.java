package com.main.services;

import java.util.List;
import java.util.Optional;

import org.hibernate.query.SortDirection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;

import com.main.dbObjects.EmployeeInfoDBO;
import com.main.dto.EmployeeRegisterDTO;
import com.main.dto.EmployeeResponseDTO;

public interface IEmployeeInfoService {

	public String registerEmployee(EmployeeRegisterDTO dto);
	public List<EmployeeResponseDTO> getAllEmployees();
	public String deleteEmployeeById(Integer id);
	public EmployeeResponseDTO getExistData(Integer id); 
	public String update(EmployeeRegisterDTO dto);
	public Page<EmployeeInfoDBO> fetchEmpByPageNo(Pageable pageable);
}
