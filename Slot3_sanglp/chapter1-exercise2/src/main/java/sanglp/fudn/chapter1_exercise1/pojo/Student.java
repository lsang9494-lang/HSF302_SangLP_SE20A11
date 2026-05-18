package sanglp.fudn.chapter1_exercise1.pojo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.persistence.CascadeType;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;

import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "students_many_to_many")
public class Student {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false, unique = true, length = 100)
	private String email;

	@Column(nullable = false, length = 100)
	private String password;

	@Column(nullable = false, length = 50)
	private String firstName;

	@Column(nullable = false, length = 50)
	private String lastName;

	private int marks;

	@ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.EAGER)
	@JoinTable(
			name = "student_book",
			joinColumns = @JoinColumn(name = "student_id"),
			inverseJoinColumns = @JoinColumn(name = "book_id")
	)
	private Set<Book> books = new LinkedHashSet<>();

	public Student() {
	}

	public Student(Long id, String email, String password, String firstName, String lastName, int marks) {
		this.id = id;
		this.email = email;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.marks = marks;
	}

	public Student(Long id, String email, String password, String firstName, String lastName, int marks, Set<Book> books) {
		this(id, email, password, firstName, lastName, marks);
		if (books != null) {
			books.forEach(this::addBook);
		}
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public int getMarks() {
		return marks;
	}

	public void setMarks(int marks) {
		this.marks = marks;
	}

	public Set<Book> getBooks() {
		return books;
	}

	public void setBooks(Set<Book> books) {
		this.books.clear();
		if (books != null) {
			books.forEach(this::addBook);
		}
	}

	public void addBook(Book book) {
		if (book != null) {
			books.add(book);
			book.getStudents().add(this);
		}
	}

	public void removeBook(Book book) {
		if (book != null) {
			books.remove(book);
			book.getStudents().remove(this);
		}
	}

	@Override
	public String toString() {
		return "Student{" +
				"id=" + id +
				", email='" + email + '\'' +
				", password='" + password + '\'' +
				", firstName='" + firstName + '\'' +
				", lastName='" + lastName + '\'' +
				", marks=" + marks +
				", books=" + books.stream().map(Book::simpleInfo).toList() +
				'}';
	}
}
