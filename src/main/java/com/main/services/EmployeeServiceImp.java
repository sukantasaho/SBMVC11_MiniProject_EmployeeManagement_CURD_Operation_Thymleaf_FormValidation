package com.main.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.main.conversion.Converter;
import com.main.dbObjects.EmployeeInfoDBO;
import com.main.dto.EmployeeRegisterDTO;
import com.main.dto.EmployeeResponseDTO;
import com.main.repository.IEmployeeInfoRepository;

@Service
public class EmployeeServiceImp implements IEmployeeInfoService 
{
    @Autowired
	private IEmployeeInfoRepository empRepository;
    
	
	@Override
	public String registerEmployee(EmployeeRegisterDTO dto) 
	{
		  EmployeeInfoDBO dbo = Converter.convertDTOToDBO(dto);
		  int id = empRepository.save(dbo).getId();
		  
		return "Record Saved Successfully With Refference Id-"+id+" (For Future Operation)";
	}

	@Override
	public List<EmployeeResponseDTO> getAllEmployees() 
	{
	        List<EmployeeInfoDBO> dboList = empRepository.getAllEmployees(); 
	        
		return Converter.convertDBOListToDTOList(dboList);
	}
//another approach for soft deletion
	/*@Override
	public String deleteEmployeeById(Integer id)
	{
		String status = null;
		Optional<EmployeeInfoDBO> opt = empRepository.findById(id);
		if(opt.isPresent())
		{
			EmployeeInfoDBO dbo = opt.get();
			dbo.setRecordStatus("Inactive");
			empRepository.save(dbo);
			status = "success";
			
		}
		else
		{
			status = "failed";
		}
		return status;
	}
	*/
//approch-2
	@Override
	public String deleteEmployeeById(Integer id)
	{
		String status = null;
		Optional<EmployeeInfoDBO> opt = empRepository.findById(id);
		if(opt.isPresent())
		{
			EmployeeInfoDBO dbo = opt.get();
			empRepository.delete(dbo);
			status = "success";
			
		}
		else
		{
			status = "failed";
		}
		return status;
	}

	@Override
	public EmployeeResponseDTO getExistData(Integer id) 
	{      
		   EmployeeResponseDTO dto = null;
		   Optional<EmployeeInfoDBO> dbo = empRepository.findById(id);
		   if(dbo.isPresent())
		   {
			   dto = Converter.convertDBOToDTO(dbo.get());
		   }
		return dto;
	}

	@Override
	public String update(EmployeeRegisterDTO dto) {
		 
		String msg = null;
		 if(dto!=null)
		 {
			Optional<EmployeeInfoDBO> dboOpt = empRepository.findById(Integer.parseInt(dto.getId()));
			if(dboOpt.isPresent())
			{
				EmployeeInfoDBO dbo = dboOpt.get();
				BeanUtils.copyProperties(dto, dbo);
				Float salary = Float.parseFloat(dto.getSalary());
				dbo.setSalary(salary);
				empRepository.save(dbo);
				msg = "Record Updated Successfully With Refference Id-"+dbo.getId();
			}
			
		 }
		return msg;
	}

	@Override
	public Page<EmployeeInfoDBO> fetchEmpByPageNo(Pageable pageable) 
	{
		Page<EmployeeInfoDBO> page = empRepository.findAll(pageable);
		 
		return   page;
	}
	

}
