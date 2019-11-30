package zy.test.jdk8;

import java.util.ArrayList;
import java.util.List;

public class Album {

	private String name;
	private List<Track> tracks = new ArrayList<Track>();
	private List<Artist> musicians = new ArrayList<Artist>();
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<Track> getTracks() {
		return tracks;
	}
	public void setTracks(List<Track> tracks) {
		this.tracks = tracks;
	}
	public List<Artist> getMusicians() {
		return musicians;
	}
	public void setMusicians(List<Artist> musicians) {
		this.musicians = musicians;
	}
	
	public List<Artist> getAllMusicians(){
		return musicians.stream().reduce(
				new ArrayList<Artist>(), 
				(acc, m)->{
					ArrayList<Artist> newAcc= new ArrayList<>(acc);
					if(m.getMembers().size()>0){
						newAcc.addAll(m.getMembers());
					}else{
						newAcc.add(m);
					}
					return newAcc;
				},
				(left, right)->{
					ArrayList<Artist> newLeft = new ArrayList<Artist>(left); 
					newLeft.addAll(right);
					return newLeft;
					}
				);
		}
}
