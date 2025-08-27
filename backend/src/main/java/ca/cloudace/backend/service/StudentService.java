package ca.cloudace.backend.service;

import java.util.List;

import org.springframework.stereotype.Service;

import ca.cloudace.backend.model.Student;
import ca.cloudace.backend.repository.StudentRepository;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    // Business logic related to students can be added here


    public List<Student> findAllStudents() {
        return studentRepository.findAll();
    }

    public Student findStudentById(int id) {
        return studentRepository.findById(id).orElse(null);
    }

   

    
    public void saveStudent(Student student) {
        studentRepository.save(student);
    }

    public void deleteStudent(int id) {
        studentRepository.deleteById(id);
    }


    /**
     * Updates an existing student.
     * @param id
     * @param student
     * @return
     */
    public Student updateStudent(int id, Student student) {
        if (studentRepository.existsById(id)) {
            student.setStudentId(id);
            studentRepository.save(student);
            return student;
        }
        return null;
    }

}
