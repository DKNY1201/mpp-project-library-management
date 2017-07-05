package business;

import java.io.Serializable;

final public class Author extends Person implements Serializable {
	private String bio;
	public String getBio() {
		return bio;
	}
	

	public Author(String f, String l, String t, Address a, String bio) {
		super(f, l, t, a);
		this.bio = bio;
	}
	
	@Override
	public String toString() {
		return getFirstName() + " " + getLastName() + " : " + getBio();
	}

	private static final long serialVersionUID = 7508481940058530471L;

}
