package com;


import org.apache.camel.dataformat.bindy.annotation.CsvRecord;
import org.apache.camel.dataformat.bindy.annotation.DataField;

@CsvRecord(separator = ",", crlf="MAC")
public class Artist {

	@DataField(pos = 1)
	String genre;
	
	@DataField(pos = 2)
	String name;
	
	@DataField(pos = 3)
	String album1;
	
	@DataField(pos = 4)
	String album2;
	
	@DataField(pos = 5)
	String album3;
	
	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAlbum1() {
		return album1;
	}

	public void setAlbum1(String album1) {
		this.album1 = album1;
	}

	public String getAlbum2() {
		return album2;
	}

	public void setAlbum2(String album2) {
		this.album2 = album2;
	}

	public String getAlbum3() {
		return album3;
	}

	public void setAlbum3(String album3) {
		this.album3 = album3;
	}

	
	
}
