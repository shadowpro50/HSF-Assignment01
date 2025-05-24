package hsf302.chapter01.com.dao;

                        import hsf302.chapter01.com.pojo.Student;
                        import jakarta.persistence.EntityManager;
                        import jakarta.persistence.EntityManagerFactory;
                        import jakarta.persistence.Persistence;
                        import java.util.ArrayList;
                        import java.util.List;

                        public class StudentDAO {
                            private final EntityManagerFactory emf;

                            public StudentDAO(String persistenceName) {
                                this.emf = Persistence.createEntityManagerFactory(persistenceName);
                            }

                            public void save(Student student) {
                                EntityManager em = emf.createEntityManager();
                                try {
                                    em.getTransaction().begin();
                                    // Use persist for new entities, merge for detached
                                    if (student.getStudent_id() == null) {
                                        em.persist(student);
                                    } else {
                                        em.merge(student);
                                    }
                                    em.getTransaction().commit();
                                } catch (Exception e) {
                                    if (em.getTransaction().isActive()) {
                                        em.getTransaction().rollback();
                                    }
                                    System.out.println("Error: " + e.getMessage());
                                } finally {
                                    em.close();
                                }
                            }

                            public List<Student> findAll() {
                                List<Student> students = new ArrayList<>();
                                EntityManager em = emf.createEntityManager();
                                try {
                                    // Use join fetch to avoid N+1 problem
                                    students = em.createQuery(
                                        "SELECT DISTINCT s FROM Student s LEFT JOIN FETCH s.subjects",
                                        Student.class
                                    ).getResultList();
                                } catch (Exception e) {
                                    System.out.println("Error: " + e.getMessage());
                                } finally {
                                    em.close();
                                }
                                return students;
                            }

                            public Student findById(Long id) {
                                EntityManager em = emf.createEntityManager();
                                Student student = null;
                                try {
                                    // Use join fetch to load subjects eagerly
                                    student = em.createQuery(
                                        "SELECT s FROM Student s LEFT JOIN FETCH s.subjects WHERE s.student_id = :id",
                                        Student.class
                                    ).setParameter("id", id)
                                    .getSingleResult();
                                } catch (Exception e) {
                                    System.out.println("Error: " + e.getMessage());
                                } finally {
                                    em.close();
                                }
                                return student;
                            }

                            public void update(Long id, Student student) {
                                EntityManager em = emf.createEntityManager();
                                try {
                                    em.getTransaction().begin();
                                    Student existingStudent = em.find(Student.class, id);
                                    if (existingStudent != null) {
                                        existingStudent.setName(student.getName());
                                        existingStudent.setPassword(student.getPassword());
                                        // Update subjects
                                        existingStudent.getSubjects().clear();
                                        existingStudent.getSubjects().addAll(student.getSubjects());
                                        em.merge(existingStudent);
                                    }
                                    em.getTransaction().commit();
                                } catch (Exception e) {
                                    if (em.getTransaction().isActive()) {
                                        em.getTransaction().rollback();
                                    }
                                    System.out.println("Error: " + e.getMessage());
                                } finally {
                                    em.close();
                                }
                            }

                            public void delete(Long id) {
                                EntityManager em = emf.createEntityManager();
                                try {
                                    em.getTransaction().begin();
                                    Student student = em.find(Student.class, id);
                                    if (student != null) {
                                        // Subjects will be automatically deleted due to CascadeType.ALL
                                        em.remove(student);
                                    }
                                    em.getTransaction().commit();
                                } catch (Exception e) {
                                    if (em.getTransaction().isActive()) {
                                        em.getTransaction().rollback();
                                    }
                                    System.out.println("Error: " + e.getMessage());
                                } finally {
                                    em.close();
                                }
                            }

                            public void close() {
                                if (emf != null && emf.isOpen()) {
                                    emf.close();
                                }
                            }
                        }