package hsf302.chapter01.com.repo;

    import hsf302.chapter01.com.dao.StudentDAO;
    import hsf302.chapter01.com.pojo.Student;
    import java.util.List;

    public class StudentRepoImpl implements StudentRepo {
        private final StudentDAO studentDAO;

        public StudentRepoImpl() {
            this.studentDAO = new StudentDAO("studentPU");
        }

        @Override
        public void save(Student student) {
            studentDAO.save(student);
        }

        @Override
        public List<Student> findAll() {
            return studentDAO.findAll();
        }

        @Override
        public Student findById(Long id) {
            return studentDAO.findById(id);
        }

        @Override
        public void update(Long id, Student student) {
            studentDAO.update(id, student);
        }

        @Override
        public void delete(Long id) {
            studentDAO.delete(id);
        }
    }