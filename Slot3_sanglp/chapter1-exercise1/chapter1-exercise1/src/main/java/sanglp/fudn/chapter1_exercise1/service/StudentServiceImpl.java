package sanglp.fudn.chapter1_exercise1.service;

import sanglp.fudn.chapter1_exercise1.pojo.Student;
import sanglp.fudn.chapter1_exercise1.repository.StudentRepository;

import java.util.List;
import java.util.Optional;

public class StudentServiceImpl implements StudentService {

	private final StudentRepository studentRepository;

	public StudentServiceImpl(StudentRepository studentRepository) {
		this.studentRepository = studentRepository;
	}

	@Override
	public Student createStudent(Student student) {
		validateStudent(student);
		return studentRepository.save(student);
	}

	@Override
	public Optional<Student> getStudentById(Long id) {
		validateId(id);
		return studentRepository.findById(id);
	}

	@Override
	public List<Student> getAllStudents() {
		return studentRepository.findAll();
	}

	@Override
	public Student updateStudent(Student student) {
		validateId(student.getId());
		validateStudent(student);
		return studentRepository.update(student);
	}

	@Override
	public boolean deleteStudent(Long id) {
		validateId(id);
		return studentRepository.delete(id);
	}

	private void validateStudent(Student student) {
		if (student == null) {
			throw new IllegalArgumentException("Student must not be null");
		}
		if (isBlank(student.getEmail())) {
			throw new IllegalArgumentException("Student email must not be blank");
		}
		if (isBlank(student.getPassword())) {
			throw new IllegalArgumentException("Student password must not be blank");
		}
		if (isBlank(student.getFirstName())) {
			throw new IllegalArgumentException("Student first name must not be blank");
		}
		if (isBlank(student.getLastName())) {
			throw new IllegalArgumentException("Student last name must not be blank");
		}
		if (student.getMarks() < 0 || student.getMarks() > 100) {
			throw new IllegalArgumentException("Student marks must be between 0 and 100");
		}
		if (student.getBook() == null) {
			throw new IllegalArgumentException("Student must have one book");
		}
	}

	private void validateId(Long id) {
		if (id == null || id <= 0) {
			throw new IllegalArgumentException("Student id must be a positive number");
		}
	}

	private boolean isBlank(String value) {
		return value == null || value.trim().isEmpty();
	}
}
