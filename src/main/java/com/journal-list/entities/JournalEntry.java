package com.test.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class JournalEntry {
	
	@Id
	@GeneratedValue
	private int id;
	private String title;
	private String category;

    public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
    public String getCategory() {
		return category;
	}
	
	public void setCategory(String category) {
		this.category = category;
	}	
	@Override
	public String toString() {
		return "JournalEntry [id=" + id + ", title=" + title + ", category=" + category + "]";
	}
}

