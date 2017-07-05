package business;

import java.io.Serializable;

public class CheckoutBookException extends Exception implements Serializable {

	public CheckoutBookException() {
		super();
	}
	public CheckoutBookException(String msg) {
		super(msg);
	}
	public CheckoutBookException(Throwable t) {
		super(t);
	}
	private static final long serialVersionUID = 8978723266036027364L;
	
}
