package com.example.fileprocessing.entity;

import java.util.Arrays;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "feeds")
public class Feeds {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false, unique = true)
	private Long id;
	
	@Column(name = "feed_title", nullable = false)
	private String feedTitle;
	
	@Column(name = "description", nullable = false)
	private String description;	
	
	@Lob
    @Column(name = "Image", length = Integer.MAX_VALUE, nullable = true)
    private byte[] image;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_date", nullable = false)
    private Date createDate;

	public Feeds() {}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}



	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	

	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getFeedTitle() {
		return feedTitle;
	}

	public void setFeedTitle(String feedTitle) {
		this.feedTitle = feedTitle;
	}

	@Override
	public String toString() {
		return "Feeds [id=" + id + ", feedTitle=" + feedTitle + ", description=" + description + ", image="
				+ Arrays.toString(image) + ", createDate=" + createDate + "]";
	}

}