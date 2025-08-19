package com.agri.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name = " BookEqp")

public class BookEqp {

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
	
	@Column(name = "VendorName")
	private String VendorName;
	
	@Column(name = "VendorAddress")
	private String VendorAddress;
	
	@Column(name = "VContactNumber")
	private String VContactNumber;
	
	@Column(name = "Uname")
	private String Uname;
	
	@Column(name = "UAddress")
	private String UAddress;
	
	@Column(name = "UContactNumber")
	private String UContactNumber;
	
	
	 @Column(name = "randomid")
		private String randomid;
	
	
	@Column(name = "Status")
	private String Status;
	
	
	
	
	@Column(name = "myuser")
	private String myuser;
		
	
	@Column(name = "vuser")
	private String vuser;

	
	

	

	@Column(name = "EqpName")
	private String EqpName;
	

	@Column(name = "eqpdesc")
    private String eqpdesc;

	

	



	@Column(name = "MyEqpAddress")
	private String MyEqpAddress;
	
	
	@Column(name = "latitude")
	private String latitude;
	
	
	@Column(name = "longitude")
	private String longitude;
	
	
	@Column(name = "sphoto")
	private String 	sphoto;
	
	
	@Column(name = "bookingno")
	private String 		bookingno;

	
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
	

	public String getBookingno() {
		return bookingno;
	}



	public void setBookingno(String bookingno) {
		this.bookingno = bookingno;
	}



	public String getEqpName() {
		return EqpName;
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



	public void setEqpName(String eqpName) {
		EqpName = eqpName;
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



	public long getId() {
		return id;
	}



	public String getVendorName() {
		return VendorName;
	}



	public String getVendorAddress() {
		return VendorAddress;
	}



	public String getVContactNumber() {
		return VContactNumber;
	}



	public String getUname() {
		return Uname;
	}



	public String getUAddress() {
		return UAddress;
	}



	public String getUContactNumber() {
		return UContactNumber;
	}



	public String getStatus() {
		return Status;
	}



	public String getMyuser() {
		return myuser;
	}



	public String getVuser() {
		return vuser;
	}



	public void setId(long id) {
		this.id = id;
	}



	public void setVendorName(String vendorName) {
		VendorName = vendorName;
	}



	public void setVendorAddress(String vendorAddress) {
		VendorAddress = vendorAddress;
	}



	public void setVContactNumber(String vContactNumber) {
		VContactNumber = vContactNumber;
	}



	public void setUname(String uname) {
		Uname = uname;
	}



	public void setUAddress(String uAddress) {
		UAddress = uAddress;
	}



	public void setUContactNumber(String uContactNumber) {
		UContactNumber = uContactNumber;
	}



	public void setStatus(String status) {
		Status = status;
	}



	public void setMyuser(String myuser) {
		this.myuser = myuser;
	}



	public void setVuser(String vuser) {
		this.vuser = vuser;
	}



	public String getMyEqpAddress() {
		return MyEqpAddress;
	}



	public void setMyEqpAddress(String myEqpAddress) {
		MyEqpAddress = myEqpAddress;
	}



	public String getRandomid() {
		return randomid;
	}



	public void setRandomid(String randomid) {
		this.randomid = randomid;
	}



	@Override
	public String toString() {
		return "BookEqp [id=" + id + ", VendorName=" + VendorName + ", VendorAddress=" + VendorAddress
				+ ", VContactNumber=" + VContactNumber + ", Uname=" + Uname + ", UAddress=" + UAddress
				+ ", UContactNumber=" + UContactNumber + ", Status=" + Status + ", myuser=" + myuser + ", vuser="
				+ vuser + ", EqpName=" + EqpName + ", MyEqpAddress=" + MyEqpAddress + ", latitude=" + latitude
				+ ", longitude=" + longitude + ", sphoto=" + sphoto + "]";
	}



	
	}



	
	
	
	
	
	

	
	
	
	

	

