package entity;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement
@XmlType(propOrder={"mssv","name","date"})
public class Student implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String mssv;
	private String name;
	private String date;
	public String getMssv() {
		return mssv;
	}
	public void setMssv(String mssv) {
		this.mssv = mssv;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDate() {
		return date;
	}
	public Student() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Student(String mssv, String name, String date) {
		super();
		this.mssv = mssv;
		this.name = name;
		this.date = date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public String toString() {
		return "Student [mssv=" + mssv + ", name=" + name + ", date=" + date + "]";
	}
	
	
}
