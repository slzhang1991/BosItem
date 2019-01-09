package com.ssh.bos.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * 定区（包含若干个分区）
 * 
 * @author slzhang
 */
public class DecidedZone implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String id;
	private String name;
	private Staff staff;
	private Set<SubArea> subAreas = new HashSet<SubArea>();

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Staff getStaff() {
		return staff;
	}

	public void setStaff(Staff staff) {
		this.staff = staff;
	}

	public Set<SubArea> getSubAreas() {
		return subAreas;
	}

	public void setSubAreas(Set<SubArea> subAreas) {
		this.subAreas = subAreas;
	}

}
