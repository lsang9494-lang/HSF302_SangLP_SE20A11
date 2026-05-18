package sanglp.fudn.chapter1_exercise1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import sanglp.fudn.chapter1_exercise1.pojo.Book;
import sanglp.fudn.chapter1_exercise1.pojo.Student;
import sanglp.fudn.chapter1_exercise1.repository.StudentRepositoryImpl;
import sanglp.fudn.chapter1_exercise1.service.StudentService;
import sanglp.fudn.chapter1_exercise1.service.StudentServiceImpl;

@SpringBootApplication(exclude = {
		DataSourceAutoConfiguration.class,
		HibernateJpaAutoConfiguration.class
})
public class Chapter1Exercise1Application {

	public static void main(String[] args) {
		SpringApplication.run(Chapter1Exercise1Application.class, args);

		try (StudentRepositoryImpl studentRepository = new StudentRepositoryImpl()) {
			StudentService studentService = new StudentServiceImpl(studentRepository);
			long demoId = System.currentTimeMillis();

			// 1) CREATE
			Student s = new Student();
			s.setEmail("sanglp" + demoId + "@fe.edu.vn");
			s.setPassword("123456");
			s.setFirstName("Sang");
			s.setLastName("LP");
			s.setMarks(85);
			Book javaBook = new Book();
			javaBook.setTitle("Spring Boot and JPA");
			javaBook.setAuthor("FPT Education");
			javaBook.setIsbn("HSF302-JPA-" + demoId);
			Book sqlBook = new Book();
			sqlBook.setTitle("SQL Server Fundamentals");
			sqlBook.setAuthor("FPT Education");
			sqlBook.setIsbn("HSF302-SQL-" + demoId);
			s.addBook(javaBook);
			s.addBook(sqlBook);

			Student created = studentService.createStudent(s);
			System.out.println("CREATE OK -> id: " + created.getId());

			// 2) READ
			studentService.getStudentById(created.getId())
					.ifPresent(found -> System.out.println("READ OK -> " + found));

			// 3) UPDATE
			created.setMarks(90);
			Book extraBook = new Book();
			extraBook.setTitle("Hibernate Relationships");
			extraBook.setAuthor("FPT Education");
			extraBook.setIsbn("HSF302-HIB-" + demoId);
			created.addBook(extraBook);
			Student updated = studentService.updateStudent(created);
			System.out.println("UPDATE OK -> " + updated);

			// 4) READ ALL
			System.out.println("READ ALL OK -> " + studentService.getAllStudents());

			// 5) DELETE
			boolean deleted = studentService.deleteStudent(updated.getId());
			System.out.println("DELETE OK -> " + deleted);
		}
	}

}
