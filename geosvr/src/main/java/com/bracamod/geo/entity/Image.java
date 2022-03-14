package com.bracamod.geo.entity;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;

@Entity
@Table(name = "image", indexes = { @Index(name = "idx_imageId", columnList = "id") })
public class Image {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "file_name")
	private String fileName;
	
	@Column(name = "creationDate", columnDefinition = "TIMESTAMP")
	private LocalDateTime creationDate;
	
	@Column(name = "ref_id")
	private int refId;
	
	@Column(name = "ref_table")
	private String refTable;
	
	@Column(name = "type")
	private String type;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public LocalDateTime getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(LocalDateTime creationDate) {
		this.creationDate = creationDate;
	}

	public int getRefId() {
		return refId;
	}

	public void setRefId(int refId) {
		this.refId = refId;
	}

	public String getRefTable() {
		return refTable;
	}

	public void setRefTable(String refTable) {
		this.refTable = refTable;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "Image [id=" + id + ", fileName=" + fileName + ", creationDate=" + creationDate + ", refId=" + refId
				+ ", refTable=" + refTable + ", type=" + type + "]";
	}
	
}
