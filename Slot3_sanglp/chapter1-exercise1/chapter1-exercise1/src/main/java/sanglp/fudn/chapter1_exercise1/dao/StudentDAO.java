package sanglp.fudn.chapter1_exercise1.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import sanglp.fudn.chapter1_exercise1.pojo.Student;

import java.util.List;
import java.util.Optional;

public class StudentDAO implements AutoCloseable {

	private final EntityManagerFactory entityManagerFactory;

	public StudentDAO() {
		this.entityManagerFactory = Persistence.createEntityManagerFactory("hsf302-chapter1");
	}

	public Student save(Student student) {
		return executeInTransaction(entityManager -> {
			entityManager.persist(student);
			return student;
		});
	}

	public Optional<Student> findById(Long id) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		try {
			return Optional.ofNullable(entityManager.find(Student.class, id));
		} finally {
			entityManager.close();
		}
	}

	public List<Student> findAll() {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		try {
			return entityManager.createQuery("select s from Student s order by s.id", Student.class)
					.getResultList();
		} finally {
			entityManager.close();
		}
	}

	public Student update(Student student) {
		return executeInTransaction(entityManager -> entityManager.merge(student));
	}

	public boolean delete(Long id) {
		return executeInTransaction(entityManager -> {
			Student student = entityManager.find(Student.class, id);
			if (student == null) {
				return false;
			}
			entityManager.remove(student);
			return true;
		});
	}

	@Override
	public void close() {
		if (entityManagerFactory.isOpen()) {
			entityManagerFactory.close();
		}
	}

	private <T> T executeInTransaction(EntityManagerOperation<T> operation) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		EntityTransaction transaction = entityManager.getTransaction();
		try {
			transaction.begin();
			T result = operation.execute(entityManager);
			transaction.commit();
			return result;
		} catch (RuntimeException exception) {
			if (transaction.isActive()) {
				transaction.rollback();
			}
			throw exception;
		} finally {
			entityManager.close();
		}
	}

	@FunctionalInterface
	private interface EntityManagerOperation<T> {
		T execute(EntityManager entityManager);
	}
}
