package ch6;

import java.util.Arrays;

public class Exam {
	public static void main(String...strings){
//		squareSum();
		parallel();
	}
	
	public static void squareSum(){
		Integer[] values = new Integer[10];
		for(int i = 1; i<11; i++){
			values[i-1] = i;
		}
		
		System.out.println(Arrays.asList(values).parallelStream().mapToInt(x->x*x).sum());
	}
	
	public static void parallel(){
		System.out.println(Arrays.asList(1,2,3,4,5).parallelStream().reduce(2, (acc,x)-> x*acc, (l,r)->l*r/2));
	}
	
	public static void sum(){
		
	}
}