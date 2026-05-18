package sanglp.fudn.chapter1_exercise1.repository;

import sanglp.fudn.chapter1_exercise1.pojo.Student;

import java.util.List;
import java.util.Optional;

public interface StudentRepository {

	Student save(Student student);

	Optional<Student> findById(Long id);

	List<Student> findAll();

	Student update(Student student);

	boolean delete(Long id);
}
