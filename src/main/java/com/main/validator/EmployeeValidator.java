package com.main.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.main.dto.EmployeeRegisterDTO;

@Component
public class EmployeeValidator implements Validator
{

	@Override
	public boolean supports(Class<?> clazz) {
		 
		return clazz.isAssignableFrom(EmployeeRegisterDTO.class);//return true for the current dto class assignment
	}

	@Override
	public void validate(Object target, Errors errors) {
		
		   //type casting
		   EmployeeRegisterDTO dto = (EmployeeRegisterDTO) target;
		   
		   //server side form validation
		   if(dto.getName()==null || dto.getName().equals("") || dto.getName().length()==0)
		     errors.rejectValue("name", "emp.name.required");
		   else if(dto.getName().length()<5 || dto.getName().length()>15)
			   errors.rejectValue("length", "emp.name.length");
		   else if(dto.getIdType()==null || dto.getIdType().equals("") || dto.getIdType().length()==0)
			   errors.rejectValue("idType", "emp.idType.required");
		   
		   else if(dto.getAddress()==null || dto.getAddress().equals("") || dto.getAddress().length()==0)
			   errors.rejectValue("address", "emp.address.required");
		   
		   else if(dto.getEmail()== null || dto.getEmail().equals("") || dto.getEmail().length()==0)
			   errors.rejectValue("email", "emp.email.required");
		   
		   else if(dto.getSalary()==null || dto.getSalary().equals("") || dto.getSalary().length()==0)
			   errors.rejectValue("salary", "emp.salary.required");
		   
		   else if(dto.getDepartment()==null || dto.getDepartment().equals("") || dto.getDepartment().length()==0)
			   errors.rejectValue("department", "emp.department.required");
	}
       
}
