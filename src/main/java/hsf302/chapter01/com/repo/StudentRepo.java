package hsf302.chapter01.com.repo;

import hsf302.chapter01.com.pojo.Student;
import java.util.List;

public interface StudentRepo {
    void save(Student student);
    List<Student> findAll();
    Student findById(Long id);
    void update(Long id, Student student);
    void delete(Long id);
}