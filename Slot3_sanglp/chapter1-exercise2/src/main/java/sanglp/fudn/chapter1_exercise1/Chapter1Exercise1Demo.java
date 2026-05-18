package sanglp.fudn.chapter1_exercise1;

import sanglp.fudn.chapter1_exercise1.pojo.Student;
import sanglp.fudn.chapter1_exercise1.pojo.Book;
import sanglp.fudn.chapter1_exercise1.repository.StudentRepositoryImpl;
import sanglp.fudn.chapter1_exercise1.service.StudentService;
import sanglp.fudn.chapter1_exercise1.service.StudentServiceImpl;

import java.util.Set;

public class Chapter1Exercise1Demo {

	public static void main(String[] args) {
		run();
	}

	public static void run() {
		try (StudentRepositoryImpl repository = new StudentRepositoryImpl()) {
			StudentService studentService = new StudentServiceImpl(repository);
			long demoId = System.currentTimeMillis();

			Book javaBook = new Book(null, "Spring Boot and JPA", "FPT Education", "HSF302-DEMO-JPA-" + demoId);
			Book sqlBook = new Book(null, "SQL Server Fundamentals", "FPT Education", "HSF302-DEMO-SQL-" + demoId);
			Student createdStudent = studentService.createStudent(
					new Student(null, "traltb.demo" + demoId + "@fe.edu.vn", "123456", "Tra", "LTB", 85,
							Set.of(javaBook, sqlBook))
			);
			System.out.println("Created: " + createdStudent);

			createdStudent.setMarks(90);
			createdStudent.addBook(new Book(null, "Hibernate Relationships", "FPT Education", "HSF302-DEMO-HIB-" + demoId));
			Student updatedStudent = studentService.updateStudent(createdStudent);
			System.out.println("Updated: " + updatedStudent);

			System.out.println("Find by id: " + studentService.getStudentById(updatedStudent.getId()));
			System.out.println("All students: " + studentService.getAllStudents());

			boolean deleted = studentService.deleteStudent(updatedStudent.getId());
			System.out.println("Deleted: " + deleted);
		}
	}
}
