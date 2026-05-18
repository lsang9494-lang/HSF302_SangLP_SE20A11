package sanglp.fudn.chapter1_exercise1.service;

import sanglp.fudn.chapter1_exercise1.pojo.Student;

import java.util.List;
import java.util.Optional;

public interface StudentService {

	Student createStudent(Student student);

	Optional<Student> getStudentById(Long id);

	List<Student> getAllStudents();

	Student updateStudent(Student student);

	boolean deleteStudent(Long id);
}
