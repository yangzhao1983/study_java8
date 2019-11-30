package zy.test.jdk8.ch5;

import java.util.ArrayList;
import java.util.List;

import zy.test.jdk8.Artist;
import static java.util.stream.Collectors.toList;

public class CompRefMeth {

	public static void main(String ...a){
		List<Artist> as = new ArrayList<Artist>();
		new CompRefMeth().comp(as);
	}
	public void comp(List<Artist> as){
		as.stream().map(a-> a.getName()).collect(toList());
		as.stream().map(Artist::getName);
	}
}
