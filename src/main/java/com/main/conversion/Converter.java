package com.main.conversion;

import java.util.List;
import java.util.stream.Collectors;
import com.main.dbObjects.EmployeeInfoDBO;
import com.main.dto.EmployeeRegisterDTO;
import com.main.dto.EmployeeResponseDTO;

public class Converter 
{
    public static EmployeeInfoDBO convertDTOToDBO(EmployeeRegisterDTO dto)
    {
    	EmployeeInfoDBO dbo = new EmployeeInfoDBO();
    	dbo.setName(dto.getName());
    	dbo.setIdType(dto.getIdType());
    	dbo.setAddress(dto.getAddress());
    	dbo.setDepartment(dto.getDepartment());
    	dbo.setEmail(dto.getEmail());
    	dbo.setRecordStatus(dbo.getRecordStatus());
    	try 
    	{
			Float salary = Float.parseFloat(dto.getSalary());
			dbo.setSalary(salary);
		} 
    	catch (Exception e) 
    	{
			 System.out.println(e.getMessage());
		}
    	
    	return dbo;
    }
    
    
    /* This method meant for converting List of DB Object to List of DTO Object to return response(DBO List To DTO List) */
    public static List<EmployeeResponseDTO> convertDBOListToDTOList(List<EmployeeInfoDBO> dboList)
    {
    	List<EmployeeResponseDTO> dtoList = null;
    	if(!dboList.isEmpty() || dboList!=null)
    	{ 
    		  dtoList = dboList.stream()
                    .map(dbo->convertDBOToDTO(dbo))
                    .collect(Collectors.toList()); 
    	}
    	
    	return dtoList;
    }
    
    public static EmployeeResponseDTO convertDBOToDTO(EmployeeInfoDBO dbo)
    {
    	EmployeeResponseDTO  dto = new EmployeeResponseDTO();
	    
    	dto.setName(dbo.getName());
    	dto.setIdType(dbo.getIdType());
    	dto.setAddress(dbo.getAddress());
    	dto.setDepartment(dbo.getDepartment());
    	dto.setEmail(dbo.getEmail());
    	dto.setId(dbo.getId());
        dto.setSalary(dbo.getSalary());
         
		return dto;
    }
}
