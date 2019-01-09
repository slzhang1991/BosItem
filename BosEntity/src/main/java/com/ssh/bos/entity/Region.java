package com.ssh.bos.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * 区域（行政区域）
 * 
 * @author slzhang
 */
public class Region implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String id;
	private String province;
	private String city;
	private String district;
	private String postcode;
	private String shortCode;
	private String cityCode;
	private Set<SubArea> subAreas = new HashSet<SubArea>();
	
	/**
	 * 默认构造器
	 */
	public Region() {
		
	}
	
	/**
	 * 获取省市区
	 * 
	 * @return
	 */
	public String getName() {
		return province + " " + city + " " + district;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getPostcode() {
		return postcode;
	}

	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}

	public String getShortCode() {
		return shortCode;
	}

	public void setShortCode(String shortCode) {
		this.shortCode = shortCode;
	}

	public String getCityCode() {
		return cityCode;
	}

	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}

	public Set<SubArea> getSubAreas() {
		return subAreas;
	}

	public void setSubAreas(Set<SubArea> subAreas) {
		this.subAreas = subAreas;
	}

	public Region(String id, String province, String city, String district, String postcode, String shortCode, String cityCode, Set<SubArea> subAreas) {
		this.id = id;
		this.province = province;
		this.city = city;
		this.district = district;
		this.postcode = postcode;
		this.shortCode = shortCode;
		this.cityCode = cityCode;
		this.subAreas = subAreas;
	}

}
