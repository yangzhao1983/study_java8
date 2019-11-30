package zy.test.jdk8;

import java.util.ArrayList;
import java.util.List;

public class Artist {
    private String name;
    private List<Artist> members = new ArrayList<Artist>();
    private String nationality;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<Artist> getMembers() {
		return members;
	}
	public void setMembers(List<Artist> members) {
		this.members = members;
	}
	public String getNationality() {
		return nationality;
	}
	public void setNationality(String nationality) {
		this.nationality = nationality;
	}
}
