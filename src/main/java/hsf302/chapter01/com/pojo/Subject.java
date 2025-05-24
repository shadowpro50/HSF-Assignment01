package hsf302.chapter01.com.pojo;

import jakarta.persistence.*;

@Entity
public class Subject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long subject_id;

    @Column(name = "subject_name", length = 100, nullable = false)
    private String subject_name;

    @Column(length = 200)
    private String location;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;

    // Default constructor
    public Subject() {
    }

    // Constructor with fields
    public Subject(String subject_name, String location) {
        this.subject_name = subject_name;
        this.location = location;
    }

    // Getters and Setters
    public Long getSubject_id() {
        return subject_id;
    }

    public void setSubject_id(Long subject_id) {
        this.subject_id = subject_id;
    }

    public String getSubject_name() {
        return subject_name;
    }

    public void setSubject_name(String subject_name) {
        this.subject_name = subject_name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }
}