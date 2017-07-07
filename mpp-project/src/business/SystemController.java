package business;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import business.Book;
import dataaccess.Auth;
import dataaccess.DataAccess;
import dataaccess.DataAccessFacade;
import dataaccess.User;
import ui.CheckOverdueBookWindow.OverdueRecord;
import ui.CheckoutRecordWindow;
import ui.CheckoutRecordWindow.CheckoutRecordAndLibraryMember;

public class SystemController implements ControllerInterface {
	public static Auth currentAuth = Auth.UNAUTHENTICATED;

	public void login(String id, String password) throws LoginException {
		DataAccess da = new DataAccessFacade();
		HashMap<String, User> map = da.readUserMap();
		if (!map.containsKey(id)) {
			throw new LoginException("ID " + id + " not found");
		}
		String passwordFound = map.get(id).getPassword();
		if (!passwordFound.equals(password)) {
			throw new LoginException("Password incorrect");
		}
		currentAuth = map.get(id).getAuthorization();
	}

	@Override
	public List<String> allMemberIds() {
		DataAccess da = new DataAccessFacade();
		List<String> retval = new ArrayList<>();
		retval.addAll(da.readMemberMap().keySet());
		return retval;
	}

	@Override
	public List<String> allBookIds() {
		DataAccess da = new DataAccessFacade();
		List<String> retval = new ArrayList<>();
		retval.addAll(da.readBooksMap().keySet());
		return retval;
	}

	@Override
   	public List<Author> getAllAuthors() {
		DataAccess da = new DataAccessFacade();
       	HashMap<String,Book> books = da.readBooksMap();
       	HashMap<Author,Author> authors = new HashMap<Author,Author>();
       	List<Author> listAuthor = new ArrayList<Author>();
       	for (Book book : books.values()) {
   			for (Author author : book.getAuthors()) {
   				if (!authors.containsKey(author)){
   					listAuthor.add(author);
   					authors.put(author, author);
   				}
   			}
   		}
   		return listAuthor;
   	}

	@Override
	public LibraryMember searchMember(String memberID) {
		DataAccess da = new DataAccessFacade();
		return da.searchMember(memberID);
	}

	@Override
	public void addMember(String id, String firstName, String lastName, String street, String city, String state,
			String zip, String phone) throws AddMemberException {
		DataAccess da = new DataAccessFacade();
		if (da.searchMember(id) != null) {
			throw new AddMemberException("Library member with ID " + id + " is existing!");
		}
		Address address = new Address(street, city, state, zip);
		LibraryMember newLibraryMember = new LibraryMember(id, firstName, lastName, phone, address);

		da.writeLibraryMember(newLibraryMember);
	}
	
	@Override
	public void editMember(String id, String firstName, String lastName, String street, String city, String state,
			String zip, String phone) {
		Address address = new Address(street, city, state, zip);
		LibraryMember newLibraryMember = new LibraryMember(id, firstName, lastName, phone, address);
		
		DataAccess da = new DataAccessFacade();
		da.writeLibraryMember(newLibraryMember);
	}

	@Override
	public void checkoutBook(String memberID, String isbnNumber) throws CheckoutBookException {
		DataAccess da = new DataAccessFacade();

		LibraryMember member = null;
		Book book = null;

		if ((member = da.searchMember(memberID)) == null) {
			throw new CheckoutBookException("Member ID not found!");
		}

		if ((book = da.searchBook(isbnNumber)) == null) {
			throw new CheckoutBookException("The requested book is not available!");
		}

		if (!book.isAvailable()) {
			throw new CheckoutBookException("The requested book is not available!");
		}

		BookCopy bookCopy = book.getNextAvailableCopy();
		int maxCheckoutLength = book.getMaxCheckoutLength();

		member.checkout(bookCopy, LocalDate.now(), LocalDate.now().plusDays(maxCheckoutLength));

		da.saveMember(member);
		da.saveBook(book);
	}

	@Override
	public void addCopyBook(String isbn) throws AddCopyBookException {
		DataAccess da = new DataAccessFacade();
		Book book;

		if ((book = da.searchBook(isbn)) == null) {
			throw new AddCopyBookException("Book cannot found!");
		}

		book.addCopy();
		da.saveBook(book);
	}

	@Override
	public void addBook(String isbn, String title, int maxCheckoutLength, int numOfCopies, List<Author> authors)
			throws AddBookException {
		DataAccess da = new DataAccessFacade();
		if (da.searchBook(isbn) != null) {
			throw new AddBookException("Book with ISBN " + isbn + " is existing!");
		}
		Book book = new Book(isbn, title, maxCheckoutLength, authors);
		for (int i = 1; i < numOfCopies; i++) {
			book.addCopy();
		}
		da.saveBook(book);
	}

	@Override
	public List<LibraryMember> getAllMembers() {
		DataAccess da = new DataAccessFacade();
		HashMap<String, LibraryMember> memberHashMap = da.readMemberMap();
		List<LibraryMember> listMembers = new ArrayList<>();
		for (String key : memberHashMap.keySet()) {
			listMembers.add(memberHashMap.get(key));
		}
		return listMembers;
	}

	@Override
	public List<Book> getAllBooks() {
		DataAccess da = new DataAccessFacade();
		HashMap<String, Book> bookHashMap = da.readBooksMap();
		List<Book> listBooks = new ArrayList<>();
		for (String key : bookHashMap.keySet()) {
			listBooks.add(bookHashMap.get(key));
		}
		return listBooks;
	}

	@Override
	public List<CheckoutRecordAndLibraryMember> getAllCheckoutRecordEntryAndLibraryMember() {
		DataAccess da = new DataAccessFacade();
		HashMap<String, LibraryMember> memberHashMap = da.readMemberMap();
		List<CheckoutRecordAndLibraryMember> listCheckoutRecordsAndLibraryMember = new ArrayList<>();
		for (String key : memberHashMap.keySet()) {
			LibraryMember libraryMember = memberHashMap.get(key);
			List<CheckoutRecordEntry> checkoutRecordEntries = libraryMember.getCheckoutRecord()
					.getCheckoutRecordEntries();
			String firstName = libraryMember.getFirstName();
			String lastName = libraryMember.getLastName();
			for (CheckoutRecordEntry checkoutRecordEntry : checkoutRecordEntries) {
				listCheckoutRecordsAndLibraryMember.add(new CheckoutRecordWindow.CheckoutRecordAndLibraryMember(
						checkoutRecordEntry.getCheckoutDate(), checkoutRecordEntry.getDueDate(),
						checkoutRecordEntry.getBookCopy(), firstName, lastName));
			}
		}
		return listCheckoutRecordsAndLibraryMember;
	}

	@Override
	public List<OverdueRecord> getOverdueRecordByISBN(String isbn) {
		DataAccess da = new DataAccessFacade();
		HashMap<String, LibraryMember> memberHashMap = da.readMemberMap();
		List<OverdueRecord> listOverdueRecord = new ArrayList<>();
		for (String key : memberHashMap.keySet()) {
			LibraryMember libraryMember = memberHashMap.get(key);
			List<CheckoutRecordEntry> checkoutRecordEntries = libraryMember.getCheckoutRecord()
					.getCheckoutRecordEntries();

			for (CheckoutRecordEntry checkoutRecordEntry : checkoutRecordEntries) {
				LocalDate dueDate = checkoutRecordEntry.getDueDate();
				LocalDate yesterdayDate = LocalDate.now().minusDays(1);
				yesterdayDate = LocalDate.now().plusDays(21);
				
				BookCopy bookCopy = checkoutRecordEntry.getBookCopy();
				Book book = bookCopy.getBook();
				
				if (book.getIsbn().equals(isbn) && dueDate.compareTo(yesterdayDate) <= 0 && !bookCopy.isAvailable()) {
					int copyNum = bookCopy.getCopyNum();
					String title = book.getTitle();
					String memberId = libraryMember.getMemberId();
					String firstName = libraryMember.getFirstName();
					String lastName = libraryMember.getLastName();
					LocalDate checkoutDate = checkoutRecordEntry.getCheckoutDate();

					listOverdueRecord.add(new OverdueRecord(isbn, title, copyNum, memberId, firstName, lastName,
							checkoutDate, dueDate));
				}
			}
		}
		return listOverdueRecord;
	}
}
