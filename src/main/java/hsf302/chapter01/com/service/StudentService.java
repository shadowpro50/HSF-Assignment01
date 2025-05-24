package hsf302.chapter01.com.service;

import hsf302.chapter01.com.pojo.Student;

import java.util.List;

public interface StudentService {
    public List<Student> findAll();
    public Student findById(Long id);
    public void save(Student student);
    public void delete(Long id);
    public void update(Long id, Student student);
}
