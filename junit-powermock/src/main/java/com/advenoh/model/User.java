package kr.pe.advenoh.model;

import lombok.Data;

import java.util.List;

@Data
public class User {
	String name;
	String address;
	List<Photos> photos;

//	public List<Photos> getPhotos() {
//		return photos;
//	}
//
//	public void setPhotos(List<Photos> photos) {
//		this.photos = photos;
//	}
}
