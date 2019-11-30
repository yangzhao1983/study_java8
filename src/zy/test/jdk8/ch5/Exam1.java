package zy.test.jdk8.ch5;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;
import java.util.stream.Stream;

import zy.test.jdk8.Artist;

public class Exam1 {

	public static void main(String...strings){
		// toUpperCaseStream();
		// maxLength();
//		countWords();
		fibonacciByMap();
	}
	
	public static void countWords(){
		Stream<String> names = Stream.of("John","Paul","George","John","Paul","John");
//		Map<String, Long> result = names.collect(Collectors.groupingBy(name->name.toString(),Collectors.counting()));
//		for(String key : result.keySet()){
//			System.out.println(key + ":" + result.get(key));
//		}
		
		Map<String, List<String>> map = names.collect(
				new GroupingBy<String, String>(name->name.toString())
				);
		for(String key : map.keySet()){
			System.out.println(key);
			System.out.println("=================");
			for(String elem : map.get(key)){
				System.out.println(elem);
			}
		}
		
	}
	
	// method reference for transferring to upper case.
	public static void toUpperCaseStream(){
		Stream.of("a","b","hello").map(String::toUpperCase).forEach(System.out::println);
		
	}
	
	// reduce:max length
	public static void maxLength(){
		
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
		a4.setName("a4");

		List<Artist> artists = new ArrayList<Artist>();
		artists.add(a1);
		artists.add(a2);
		artists.add(a3);
		artists.add(a4);
		
		System.out.println(artists.stream().map(artist->artist.getName()).reduce("",(longer,name)->{return longer.length()>name.length()? longer: name;}));
	}
	static class GroupingBy<T, K> implements Collector<T, Map<K, List<T>>, Map<K, List<T>>>{

		private final static Set<Characteristics> characteristics = new HashSet<>();
		static {
			characteristics.add(Characteristics.IDENTITY_FINISH);
		}
		    
		private Function<T,K> func;
		public GroupingBy(Function<T,K> func){
			this.func = func;
		}
		
		@Override
		public Supplier<Map<K, List<T>>> supplier() {
			
			return ()->{Map<K, List<T>> ktMap = new HashMap<K, List<T>>(); return ktMap;};
		}

		@Override
		public BiConsumer<Map<K, List<T>>, T> accumulator() {
			// TODO Auto-generated method stub
			return (map, t) -> {
				K k = func.apply(t);
				if(map.containsKey(k)){
					map.get(k).add(t);
				}else{
					List<T> list = new ArrayList<T>();
					list.add(t);
					map.put(k, list);
				}
			};
		}

		@Override
		public BinaryOperator<Map<K, List<T>>> combiner() {
			// Set timeout back to 1 minute
			String string = String.format("A string %s", "");
			MessageFormat.format("A string {0}.", "");
			return (x,y) -> {x.putAll(y); return x;};
		}

		@Override
		public Function<Map<K, List<T>>, Map<K, List<T>>> finisher() {
			// TODO do nothing
			return map->map;
		}

		@Override
	    public Set<Characteristics> characteristics() {
	        return characteristics;
	    }
		
	}
	/**
	 * Calculate fibonacci using computeIfAbsent of Map
	 * 
	 */
	public static void fibonacciByMap(){
		Map<Integer, Integer> map = new HashMap<>();
		System.out.println(map.computeIfAbsent(new Integer(10), Exam1::fibonacci));
		System.out.println(map.computeIfAbsent(new Integer(8), x->fibonacci(x)));
		System.out.println(map.computeIfAbsent(new Integer(5), x->fibonacci(x)));
	}
	public static Integer fibonacci(Integer item){
		if(item<3){
			return item;
		}else{
			return fibonacci(item-2) + fibonacci(item - 1); 
		}
	}
}
