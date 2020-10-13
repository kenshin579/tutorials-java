package com.advenoh.model.vod;

import com.advenoh.model.vod.enums.VodCollectionType;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VodCollection {
	private String title;
	private VodCollectionType vodCollectionType;
	private String content;

	@Builder
	public VodCollection(String title, VodCollectionType vodCollectionType, String content) {
		this.title = title;
		this.vodCollectionType = vodCollectionType;
		this.content = content;
	}
}
