package business;

import java.util.List;

import business.Book;
import dataaccess.DataAccess;
import dataaccess.DataAccessFacade;

public interface ControllerInterface {
	public void login(String id, String password) throws LoginException;
	public List<String> allMemberIds();
	public List<String> allBookIds();
	public void addMember(String id, String firstName, String lastName, String street, String city, String state, String zip, String phone);
	public void checkoutBook(String memberID, String isbnNumber) throws CheckoutBookException;
}
