package business;

import java.io.Serializable;
import java.util.Objects;

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
	
	@Override
	public boolean equals(Object ob) {
		if(ob == null) return false;
		if(ob.getClass() != getClass()) return false;
		Author b = (Author)ob;
		return b.getFirstName().equals(getFirstName()) && b.getLastName().equals(getLastName()) && b.getBio().equals(getBio());
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(getFirstName(),getLastName(),getBio());
	}

	private static final long serialVersionUID = 7508481940058530471L;

}
