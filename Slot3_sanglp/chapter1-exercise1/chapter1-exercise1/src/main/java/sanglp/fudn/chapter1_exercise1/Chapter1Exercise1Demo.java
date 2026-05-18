package sanglp.fudn.chapter1_exercise1;

import sanglp.fudn.chapter1_exercise1.pojo.Student;
import sanglp.fudn.chapter1_exercise1.pojo.Book;
import sanglp.fudn.chapter1_exercise1.repository.StudentRepositoryImpl;
import sanglp.fudn.chapter1_exercise1.service.StudentService;
import sanglp.fudn.chapter1_exercise1.service.StudentServiceImpl;

public class Chapter1Exercise1Demo {

	public static void main(String[] args) {
		run();
	}

	public static void run() {
		try (StudentRepositoryImpl repository = new StudentRepositoryImpl()) {
			StudentService studentService = new StudentServiceImpl(repository);
			long demoId = System.currentTimeMillis();

			Book book = new Book(null, "Spring Boot and JPA", "FPT Education", "HSF302-DEMO-" + demoId);
			Student createdStudent = studentService.createStudent(
					new Student(null, "traltb.demo" + demoId + "@fe.edu.vn", "123456", "Tra", "LTB", 85, book)
			);
			System.out.println("Created: " + createdStudent);

			createdStudent.setMarks(90);
			Student updatedStudent = studentService.updateStudent(createdStudent);
			System.out.println("Updated: " + updatedStudent);

			System.out.println("Find by id: " + studentService.getStudentById(updatedStudent.getId()));
			System.out.println("All students: " + studentService.getAllStudents());

			boolean deleted = studentService.deleteStudent(updatedStudent.getId());
			System.out.println("Deleted: " + deleted);
		}
	}
}
