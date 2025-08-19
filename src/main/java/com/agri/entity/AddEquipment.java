package com.agri.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "AddEquipment")
public class AddEquipment {
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    
	
	
	
	 @Column(name = "randomid")
		private String randomid;
	
	
    @Column(name = "vendorId")
	private String vendorId;
    
    
  

	@Column(name = "eqName")
	private String eqName;
    
    @Column(name = "eqAddress")
	private String  eqAddress;
   
    @Column(name = "contactNumber")
	private String contactNumber;
    
    
    @Column(name = "latitude")
	private String latitude;
    
    @Column(name = "longitude")
 	private String longitude;
    
    @Column
    private String sphoto;
    
    
    

	@Column(name = "myuser")
	private String myuser;
	
	
	  @Column
	    private String eqpdesc;
	    
	  
	  @Column
	    private String price;
	      
	  
    
	  
	  
	  
    public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getEqpdesc() {
		return eqpdesc;
	}

	public void setEqpdesc(String eqpdesc) {
		this.eqpdesc = eqpdesc;
	}

	public String getMyuser() {
		return myuser;
	}

	public void setMyuser(String myuser) {
		this.myuser = myuser;
	}

	public String getVendorId() {
  		return vendorId;
  	}

  	public void setVendorId(String vendorId) {
  		this.vendorId = vendorId;
  	}

	public long getId() {
		return id;
	}

	public String getEqName() {
		return eqName;
	}

	public String getEqAddress() {
		return eqAddress;
	}

	public String getContactNumber() {
		return contactNumber;
	}

	public String getLatitude() {
		return latitude;
	}

	public String getLongitude() {
		return longitude;
	}

	public String getSphoto() {
		return sphoto;
	}

	public void setId(long id) {
		this.id = id;
	}

	public void setEqName(String eqName) {
		this.eqName = eqName;
	}

	public void setEqAddress(String eqAddress) {
		this.eqAddress = eqAddress;
	}

	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public void setSphoto(String sphoto) {
		this.sphoto = sphoto;
		
		
		
		
	}

	public String getRandomid() {
		return randomid;
	}

	public void setRandomid(String randomid) {
		this.randomid = randomid;
	}

	@Override
	public String toString() {
		return "AddEquipment [id=" + id + ", eqName=" + eqName + ", eqAddress=" + eqAddress + ", contactNumber="
				+ contactNumber + ", latitude=" + latitude + ", longitude=" + longitude + ", sphoto=" + sphoto + "]";
	}

}
