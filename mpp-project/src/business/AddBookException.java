package business;

import java.io.Serializable;

public class AddBookException extends Exception implements Serializable {

	public AddBookException() {
		super();
	}
	public AddBookException(String msg) {
		super(msg);
	}
	public AddBookException(Throwable t) {
		super(t);
	}
	private static final long serialVersionUID = 8978723266036027364L;
	
}
