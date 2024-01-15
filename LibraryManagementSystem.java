import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Books
{
	private String bookName;
	private String authorName;
	private int bookId;
	private boolean bookAvailable;
public Books( String bookName,String authorName,int bookId)
{
	this.bookName=bookName;
	this.authorName=authorName;
	this.bookId=bookId;
	this.bookAvailable=true;
}
public String getBookName()
{
	return bookName;

}
public String getAuthorName()
{
	return authorName;
}
public int getBookId()
{
	return bookId;
}
public boolean isAvailable()
{
	return 	bookAvailable;
}
public void setAvailable(boolean bookAvailable)
{
	this.bookAvailable=bookAvailable;
}
}
class Users
{
private String name;
private int rollNo;
public Users(String name, int rollNo)
{
    this.name = name;
    this.rollNo= rollNo;
}
public String getName()
{
    return name;
}
 public int getRollNo() 
{
    return rollNo;
}
}
 class Library {
    private List<Books> books;
    private Map<Integer, Users> users;
    private Map<Integer, Integer> borrowedBooks;  // Map<userId, bookId>

    public Library() {
        this.books = new ArrayList<>();
        this.users = new HashMap<>();
        this.borrowedBooks = new HashMap<>();
    }

    public void addBook(Books book) {
        books.add(book);
    }

    public void addUser(Users user) {
        users.put(user.getRollNo(), user);
    }

    public void borrowBook(int userId, int bookId) {
        if (users.containsKey(userId) && books.stream().anyMatch(book -> book.getBookId() == bookId && book.isAvailable())) {
            borrowedBooks.put(userId, bookId);
            books.stream().filter(book -> book.getBookId() == bookId).findFirst().ifPresent(book -> book.setAvailable(false));
            System.out.println("Book successfully borrowed by user " + userId);
        } else {
            System.out.println("Book not available or user not registered");
        }
    }

    public void returnBook(int userId) {
        if (borrowedBooks.containsKey(userId)) {
            int bookId = borrowedBooks.get(userId);
            books.stream().filter(book -> book.getBookId() == bookId).findFirst().ifPresent(book -> book.setAvailable(true));
            borrowedBooks.remove(userId);
            System.out.println("Book successfully returned by user " + userId);
        } else {
            System.out.println("No book borrowed by user " + userId);
        }
    }

    public void displayAvailableBooks() {
        System.out.println("Available Books:");
        books.stream().filter(Books::isAvailable).forEach(book -> System.out.println(book.getBookName() + " by " + book.getAuthorName()));
    }
}

public class LibraryManagementSystem {
    public static void main(String[] args) {
        Library library = new Library();

        // Adding books to the library
        Books book1 = new Books("The Catcher in the Rye", "J.D. Salinger", 1);
        Books book2 = new Books("To Kill a Mockingbird", "Harper Lee", 2);
        library.addBook(book1);
        library.addBook(book2);

        // Adding users to the library
        Users user1 = new Users("Alice", 101);
        Users user2 = new Users("Bob", 102);
        library.addUser(user1);
        library.addUser(user2);

        // Displaying available books
        library.displayAvailableBooks();

        // Borrowing and returning books
        library.borrowBook(101, 1);
        library.returnBook(101);

        // Displaying available books after borrowing and returning
        library.displayAvailableBooks();
    }
}





