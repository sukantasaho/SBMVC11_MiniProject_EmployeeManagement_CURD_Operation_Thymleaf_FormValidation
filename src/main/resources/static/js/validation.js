function doValidation(frm)
{
	alert("123");
	 //empty old form validation error message
	 document.getElementById("nameErr").innerHTML="";
	 document.getElementById("idTypeErr").innerHTML="";
	 document.getElementById("addressErr").innerHTML="";
	 document.getElementById("salaryErr").innerHTML="";
	 document.getElementById("emailErr").innerHTML="";
	 document.getElementById("departmentErr").innerHTML="";
	 
	 let name = frm.name.value;
	 let idType = frm.idType.value;
	 let address = frm.address.value;
	 let salary = frm.salary.value;
	 let email = frm.email.value;
	 let department = frm.department.value;
	 let vfalg = true;
	 
	 if(name=="")
	 {
	    document.getElementById("nameErr").innerHTML="*Name is required";
	    vfalg = false;
	 }
	 else if(name.length<5 || name.length>15)
	 {
		 document.getElementById("nameErr").innerHTML="Name Length should between 5 to 15";
		 vfalg = false;
	 }
	 else if(idType=="")
	 {
		 document.getElementById("idTypeErr").innerHTML="*IdType is required";
		  vfalg = false;
	 }
	 else if(address=="")
	 {
		 document.getElementById("addressErr").innerHTML="*Address is required";
		  vfalg = false;
	 }
	 else if(salary=="")
	 {
		 document.getElementById("salaryErr").innerHTML="*Salary is required";
		  vfalg = false;
	 }
	 else if(email=="")
	 {
		 document.getElementById("emailErr").innerHTML="*Email is required";
		  vfalg = false;
	 }
	 else if(department=="")
	 {
		 document.getElementById("departmentErr").innerHTML="*Department is required";
		  vfalg = false;
	 }
	 
	 return vfalg;
}
