package hsf301.fe.services;

import org.springframework.stereotype.Service;
import hsf301.fe.pojos.Student;

@Service
public class StudentServiceImpl implements StudentService {

    @Override
    public void save(Student student) {
        System.out.println("Save Student...");
    }
}
