package zy.test.jdk8;

import java.util.stream.Stream;

public interface Performance {

	public String getName();
	public Stream<Artist> getMusician();
}
