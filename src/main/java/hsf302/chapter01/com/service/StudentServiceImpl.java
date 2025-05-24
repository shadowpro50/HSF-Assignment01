package hsf302.chapter01.com.service;

import hsf302.chapter01.com.pojo.Student;
import hsf302.chapter01.com.repo.StudentRepo;
import hsf302.chapter01.com.repo.StudentRepoImpl;
import java.util.List;

public class StudentServiceImpl implements StudentService {
    private StudentRepo studentRepo;

    public StudentServiceImpl() {
        this.studentRepo = new StudentRepoImpl();
    }

    @Override
    public void save(Student student) {
        studentRepo.save(student);
    }

    @Override
    public List<Student> findAll() {
        return studentRepo.findAll();
    }

    @Override
    public Student findById(Long id) {
        return studentRepo.findById(id);
    }

    @Override
    public void update(Long id, Student student) {
        studentRepo.update(id, student);
    }

    @Override
    public void delete(Long id) {
        studentRepo.delete(id);
    }
}