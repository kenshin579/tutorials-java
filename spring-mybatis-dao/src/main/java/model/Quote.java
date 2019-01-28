package model;

import lombok.Data;

import java.io.Serializable;

@Data
public class Quote implements Serializable {
	int quoteNo;
	String author;
	String quote;
}
