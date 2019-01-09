package com.ssh.bos.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * 员工
 * 
 * @author slzhang
 */
public class Staff implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String id;
	private String name;
	private String telephone;
	private String hasPda = "0"; // 是否有pda 1有 0没有
	private String delTag = "0"; // 删除标识 1 删除 0 未删除
	private String station;
	private String standard;
	private Set<DecidedZone> decidedZones = new HashSet<DecidedZone>();

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

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getHasPda() {
		return hasPda;
	}

	public void setHasPda(String hasPda) {
		this.hasPda = hasPda;
	}

	public String getDelTag() {
		return delTag;
	}

	public void setDelTag(String delTag) {
		this.delTag = delTag;
	}

	public String getStation() {
		return station;
	}

	public void setStation(String station) {
		this.station = station;
	}

	public String getStandard() {
		return standard;
	}

	public void setStandard(String standard) {
		this.standard = standard;
	}

	public Set<DecidedZone> getDecidedZones() {
		return decidedZones;
	}

	public void setDecidedZones(Set<DecidedZone> decidedZones) {
		this.decidedZones = decidedZones;
	}

}
