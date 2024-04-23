package com.main.dbObjects;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;
import org.hibernate.annotations.Where;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Entity
@Table(name="MVC_EMP_INFO")
@SQLDelete(sql = "UPDATE MVC_EMP_INFO SET RECORD_STATUS='Inactive' WHERE id=?")
//@Where(clause = "RECORD_STATUS <> 'Inactive'")
@SQLRestriction("RECORD_STATUS <> 'Inactive'")
public class EmployeeInfoDBO {
        
	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    @Column(name="ID")
	    private Integer id;
	    
	    @Column(name="NAME", length = 20)
	    private String name;
	    
	    @Column(name="ID_TYPE", length = 20)
	    private String idType;
	    
	    @Column(name="ADDRESS", length = 20)
	    private String address;
	    
	    @Column(name="SALARY")
	    private Float salary;
	    
	    @Column(name="EMAIL", length = 30)
	    private String email;
	    
	    @Column(name="DEPARTMENT", length = 20)
	    private String department;
	    
	    @Column(name="RECORD_STATUS", length = 20)
	    private String recordStatus = "Active";

		public Integer getId() {
			return id;
		}

		public void setId(Integer id) {
			this.id = id;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getIdType() {
			return idType;
		}

		public void setIdType(String idType) {
			this.idType = idType;
		}

		public String getAddress() {
			return address;
		}

		public void setAddress(String address) {
			this.address = address;
		}

		public Float getSalary() {
			return salary;
		}

		public void setSalary(Float salary) {
			this.salary = salary;
		}

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}

		public String getDepartment() {
			return department;
		}

		public void setDepartment(String department) {
			this.department = department;
		}

		public String getRecordStatus() {
			return recordStatus;
		}

		public void setRecordStatus(String recordStatus) {
			this.recordStatus = recordStatus;
		}
	    
}
