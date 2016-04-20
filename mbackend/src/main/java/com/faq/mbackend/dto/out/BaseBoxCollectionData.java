package com.faq.mbackend.dto.out;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BaseBoxCollectionData {
	@JsonProperty("@rid")
	private String rid;

	@JsonProperty("@version")
	private Integer version;

	@JsonProperty("@class")
	private String clazz;

	@JsonProperty("id")
	private String id;

	@JsonProperty("_creation_date")
	private String createDate;

	@JsonProperty("_author")
	private String author;

	public String getRid() {
		return rid;
	}

	public void setRid(String rid) {
		this.rid = rid;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public String getClazz() {
		return clazz;
	}

	public void setClazz(String clazz) {
		this.clazz = clazz;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

}
