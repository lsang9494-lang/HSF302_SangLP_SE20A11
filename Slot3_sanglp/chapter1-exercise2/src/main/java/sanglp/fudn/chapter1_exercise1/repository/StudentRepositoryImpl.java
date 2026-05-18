package sanglp.fudn.chapter1_exercise1.repository;

import sanglp.fudn.chapter1_exercise1.dao.StudentDAO;
import sanglp.fudn.chapter1_exercise1.pojo.Student;

import java.util.List;
import java.util.Optional;

public class StudentRepositoryImpl implements StudentRepository, AutoCloseable {

	private final StudentDAO studentDAO;

	public StudentRepositoryImpl() {
		this(new StudentDAO());
	}

	public StudentRepositoryImpl(StudentDAO studentDAO) {
		this.studentDAO = studentDAO;
	}

	@Override
	public Student save(Student student) {
		return studentDAO.save(student);
	}

	@Override
	public Optional<Student> findById(Long id) {
		return studentDAO.findById(id);
	}

	@Override
	public List<Student> findAll() {
		return studentDAO.findAll();
	}

	@Override
	public Student update(Student student) {
		return studentDAO.update(student);
	}

	@Override
	public boolean delete(Long id) {
		return studentDAO.delete(id);
	}

	@Override
	public void close() {
		studentDAO.close();
	}
}
