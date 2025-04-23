package com.simple.patientapp.model;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;

@Entity
public class Package {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long Id ;
	
	private String packageId;
	
	private String packageName;
	
	private long packagePrice;
	
	@Lob
	@Column(name= "package_desc", columnDefinition = "TEXT")
	private String packageDesc;
	
	@Lob
	@Column(name = "package_image", columnDefinition = "LONGBLOB")
	private byte[] packageImage;

	public long getId() {
		return Id;
	}

	public void setId(long id) {
		Id = id;
	}

	public String getPackageId() {
		return packageId;
	}

	public void setPackageId(String packageId) {
		this.packageId = packageId;
	}

	public String getPackageName() {
		return packageName;
	}

	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}

	public long getPackagePrice() {
		return packagePrice;
	}

	public void setPackagePrice(long packagePrice) {
		this.packagePrice = packagePrice;
	}

	public String getPackageDesc() {
		return packageDesc;
	}

	public void setPackageDesc(String packageDesc) {
		this.packageDesc = packageDesc;
	}

	public byte[] getPackageImage() {
		return packageImage;
	}

	public void setPackageImage(byte[] packageImage) {
		this.packageImage = packageImage;
	}
	
	
	

}
