package zy.test.jdk8.ch3;

import static java.util.stream.Collectors.toList;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Stream;

import zy.test.jdk8.Album;
import zy.test.jdk8.Artist;

public class Funcs {

	public static void main(String... strings) {
		// System.out.println(addUp(Stream.of(1,2,3,4)));
		Artist a1 = new Artist();
		a1.setName("xu wei");
		a1.setNationality("CN");

		Artist a2 = new Artist();
		a2.setName("Huang jia ju");
		a2.setNationality("CN");

		Artist a3 = new Artist();
		a3.setName("Huang guan zhong");
		a3.setNationality("CN");

		Artist a4 = new Artist();
		a3.setName("Beyond");
		a3.setNationality("CN");
		a4.getMembers().add(a2);
		a4.getMembers().add(a3);

		List<Artist> artists = new ArrayList<Artist>();
		artists.add(a1);
		artists.add(a2);
		artists.add(a3);
		artists.add(a4);

		// String[] as = getNameAndNation(artists);
		// for(String a : as){
		// System.out.println(a);
		// }
		for (String s : getNamesAndOrigins(artists)) {
			System.out.println(s);
		}
	}

	public static Integer addUp(Stream<Integer> numbers) {

		return numbers.reduce(0, (acc, elem) -> acc + elem);
	}
	
	public static int numOfLowerCase(String str){
		return (int) str.chars().filter(c -> Character.isLowerCase(c)).count();
	}
	
	/**
	 * Calculate the sum member of the artist
	 * 
	 * @param Artists
	 * @return
	 */
	public static int sumArtist(List<Artist> artists){
		return artists.stream().mapToInt(a -> a.getMembers().size()).sum();
	}
	
	public static List<Album> getAlbum(List<Album> albums){
		return albums.stream().filter(album-> album.getTracks().size()<=3).collect(toList());
	}

	/**
	 * Return a string array containing the names and nation of the artist
	 * 
	 * @param artists
	 * @return
	 */
	public static String[] getNameAndNation(List<Artist> artists) {

		return artists.stream().map(artist -> {
			if (artist.getMembers().size() == 0) {
				return artist.getName() + ":" + artist.getNationality();
			} else {
				StringBuilder sb = new StringBuilder();
				artist.getMembers().stream().map(a -> a.getName() + ":" + a.getNationality()).forEach(str -> {
					sb.append(str);
					sb.append(",");
				});
				return sb.toString();
			}
		}).toArray(String[]::new);
	}

	public static List<String> getNamesAndOrigins(List<Artist> artists) {
		return artists.stream().flatMap(artist -> Stream.of(artist.getName(), artist.getNationality()))
				.collect(toList());
	}

	public static void verifyTypeOfReduce(List<String> ss){
		ss.stream().reduce(new ArrayList<String>(), (acc,s)->{ArrayList<String> l = new ArrayList<>(acc);l.add(s);return l;},(left, right)->{ArrayList<String> newL = new ArrayList<>(left);
		newL.addAll(right);return newL;});
	}
	
	public static <I, O> List<O> map(Stream<I> stream, Function<I, O> mapper) {
        return stream.reduce(new ArrayList<O>(), (acc, x) -> {
        	// We are copying data from acc to new list instance. It is very inefficient,
        	// but contract of Stream.reduce method requires that accumulator function does
        	// not mutate its arguments.
        	// Stream.collect method could be used to implement more efficient mutable reduction,
        	// but this exercise asks to use reduce method.
        	List<O> newAcc = new ArrayList<>(acc);
        	newAcc.add(mapper.apply(x));
            return newAcc;
        }
        , 
        		
        		(List<O> left, List<O> right) -> {
        	// We are copying left to new list to avoid mutating it. 
        	List<O> newLeft = new ArrayList<>(left);
        	newLeft.addAll(right);
            return newLeft;
        }
        		);
    }
}
