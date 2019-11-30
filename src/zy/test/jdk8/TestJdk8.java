package zy.test.jdk8;

import java.net.URL;

public class TestJdk8 {
	
	interface InnerClass{
		public void doSth();
	}
	
	static public void useInnerClass(InnerClass ic){
		ic.doSth();
	}
	final static String name = "my name";
	
	public static void main(String...strings){
		useInnerClass(new InnerClass(){
			public void doSth(){
				System.out.println(name);
			}
		});
		String name2 = "my name2";
		useInnerClass(()->{System.out.println(name2.hashCode());});
	}
}
