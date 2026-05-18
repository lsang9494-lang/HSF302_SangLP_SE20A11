package sanglp.fudn.chapter1_exercise1.pojo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "books_many_to_many")
public class Book {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false, length = 150)
	private String title;

	@Column(length = 100)
	private String author;

	@Column(unique = true, length = 30)
	private String isbn;

	@ManyToMany(mappedBy = "books")
	private Set<Student> students = new LinkedHashSet<>();

	public Book() {
	}

	public Book(Long id, String title, String author, String isbn) {
		this.id = id;
		this.title = title;
		this.author = author;
		this.isbn = isbn;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public Set<Student> getStudents() {
		return students;
	}

	public void setStudents(Set<Student> students) {
		this.students.clear();
		if (students != null) {
			students.forEach(this::addStudent);
		}
	}

	public void addStudent(Student student) {
		if (student != null) {
			students.add(student);
			student.getBooks().add(this);
		}
	}

	public void removeStudent(Student student) {
		if (student != null) {
			students.remove(student);
			student.getBooks().remove(this);
		}
	}

	public String simpleInfo() {
		return "Book{" +
				"id=" + id +
				", title='" + title + '\'' +
				", author='" + author + '\'' +
				", isbn='" + isbn + '\'' +
				'}';
	}

	@Override
	public String toString() {
		return "Book{" +
				"id=" + id +
				", title='" + title + '\'' +
				", author='" + author + '\'' +
				", isbn='" + isbn + '\'' +
				", studentIds=" + students.stream().map(Student::getId).toList() +
				'}';
	}
}
