package com.simple.patientapp.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;

@Entity
public class Department {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long Id ;
	
	private String deptId;
	
	private String deptName;
	
	@Lob
	@Column(name= "dept_desc", columnDefinition = "TEXT")
	private String deptDesc;
	
	@Lob
	@Column(name = "dept_image", columnDefinition = "LONGBLOB")
	private byte[] deptImage;

	public long getId() {
		return Id;
	}

	public void setId(long id) {
		Id = id;
	}

	public String getDeptId() {
		return deptId;
	}

	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public String getDeptDesc() {
		return deptDesc;
	}

	public void setDeptDesc(String deptDesc) {
		this.deptDesc = deptDesc;
	}

	public byte[] getDeptImage() {
		return deptImage;
	}

	public void setDeptImage(byte[] deptImage) {
		this.deptImage = deptImage;
	}
	
	
}
