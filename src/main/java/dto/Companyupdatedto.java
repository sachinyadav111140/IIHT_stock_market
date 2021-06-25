package dto;

import java.util.List;

public class Companyupdatedto {
	private String name;
	private float	turnover;
	private String directors;
	private String ceo;
	private String sector;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public float getTurnover() {
		return turnover;
	}
	public void setTurnover(float turnover) {
		this.turnover = turnover;
	}
	public String getDirectors() {
		return directors;
	}
	public void setDirectors(String directors) {
		this.directors = directors;
	}
	public String getSector() {
		return sector;
	}
	public void setSector(String sector) {
		this.sector = sector;
	}
	public String getCeo() {
		return ceo;
	}
	public void setCeo(String ceo) {
		this.ceo = ceo;
	}
	
}
