package com.main.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.main.dbObjects.EmployeeInfoDBO;
import com.main.dto.EmployeeRegisterDTO;
import com.main.dto.EmployeeResponseDTO;
import com.main.services.IEmployeeInfoService;
import com.main.validator.EmployeeValidator;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/employee")
public class EmployeeOperationController 
{
	
	@Autowired
	private IEmployeeInfoService empService;
	
	@Autowired
	private EmployeeValidator validator;
	
	//two way data binding
    @GetMapping("/getForm")
	public String displayEmployeeRegisterForm(@ModelAttribute("emp1") EmployeeRegisterDTO emp)
	{ 
		return "register_form";
	}
    
    
    //response keep on redirect start to end only
	@PostMapping("/register")
	public String save(RedirectAttributes attr, @ModelAttribute("emp1") EmployeeRegisterDTO emp, BindingResult errors)
	{	
		     //use validator
		      if(validator.supports(EmployeeRegisterDTO.class))
		      {
		    	  validator.validate(emp, errors);//if form validation error found
		    	  if(errors.hasErrors())
		    		  
		    	 return "register_form";
		      }
		      //use service
			  String response = empService.registerEmployee(emp);
			  //keep the result in the redirect attribute shared memory
			  attr.addFlashAttribute("res", response);
		
		return "redirect:getAllEmployees";
	}
    
    @GetMapping("/getAllEmployees")
    public String getAllEmployees(Map<String, Object> map)
    {
    	 
    	 List<EmployeeResponseDTO> dtoList = empService.getAllEmployees();
    	 map.put("eList", dtoList);
    	 
    	return "response_page";
    }
     
    //redirect new
    @GetMapping("/delete")
    public String deleteEmployee(RedirectAttributes attr, @RequestParam Integer id)
    {
    	   String status = empService.deleteEmployeeById(id);
    	   String response = null;
    	   if(status.equals("success"))
    	   {   
    		   response = "Record Deleted Successfully With Refference Id-"+id;
    		   attr.addFlashAttribute("res", response);
    		   
    		   return "redirect:getAllEmployees";
    	   }
    	   
    		   response = "Record Deletion Failure With Refference Id-"+id;
    	   
    	return "redirect:employee/getAllEmployees";
    }
    @GetMapping("/edit")
    public String getExistData(@ModelAttribute("emp") EmployeeResponseDTO res, @RequestParam("id")Integer id)
    {
    	 EmployeeResponseDTO dto = empService.getExistData(id);
    	 BeanUtils.copyProperties(dto, res);
    	 
    	return "edit_register_form";
    }
    
    @PostMapping("/update")
    public String update(RedirectAttributes attr, @ModelAttribute("emp") EmployeeRegisterDTO empDTO, BindingResult errors)
    {
    	 //use validator
	      if(validator.supports(EmployeeRegisterDTO.class))
	      {
	    	  validator.validate(empDTO, errors);//if form validation error found
	    	  if(errors.hasErrors())
	    		  
	    	 return "edit_register_form";
	      }
    	    String response = empService.update(empDTO);
    	    //keep msg on redirect attribute
    	    attr.addFlashAttribute("res", response);
    	    
		return "redirect:getAllEmployees";
    }
   
}
