package business;

import java.util.List;

public interface ControllerInterface {
	public void login(String id, String password) throws LoginException;
	public List<String> allMemberIds();
	public List<String> allBookIds();
	public List<Author> getAllAuthors();
	public LibraryMember searchMember(String memberID);
	public void addMember(String id, String firstName, String lastName, String street, String city, String state, String zip, String phone);
	public void checkoutBook(String memberID, String isbnNumber) throws CheckoutBookException;
	public void addCopyBook(String isbn) throws AddCopyBookException;
	public void addBook(String isbn, String title, int maxCheckoutLength, int numOfCopies, List<Author> authors) throws AddBookException;
}
