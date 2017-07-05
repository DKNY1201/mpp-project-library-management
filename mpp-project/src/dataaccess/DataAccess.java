package dataaccess;

import java.util.HashMap;
import java.util.List;

import business.Author;
import business.Book;
import business.LibraryMember;

public interface DataAccess { 
	public HashMap<String,Book> readBooksMap();
	public HashMap<String,User> readUserMap();
	public HashMap<String, LibraryMember> readMemberMap();
	public void writeLibraryMember(LibraryMember member);
	public LibraryMember searchMember(String memberID);
	public Book searchBook(String isbn);
	public void saveMember(LibraryMember member);
	public void saveBook(Book book);
	public void printMembers();
	public List<Author> getAllAuthors();
}
