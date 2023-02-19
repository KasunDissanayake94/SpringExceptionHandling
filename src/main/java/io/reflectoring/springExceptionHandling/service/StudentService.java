package io.reflectoring.springExceptionHandling.service;

import io.reflectoring.springExceptionHandling.model.Student;
import io.reflectoring.springExceptionHandling.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    public ResponseEntity<Student> saveStudent(Student student){
        try {
            Student student1 = studentRepository.save(new Student(student.getId(), student.getName(), student.getAge(), student.getAddress(), student.getEmail()));
            return new ResponseEntity<>(student1, HttpStatus.CREATED);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    public ResponseEntity<List<Student>> getAllStudents() {
        List<Student> students = studentRepository.findAll();
        return new ResponseEntity<>(students, HttpStatus.OK);
    }

    public ResponseEntity<Student> getStudentById(int studentId) {
        Optional<Student> student = studentRepository.findById(studentId);

        return student.map(value -> new ResponseEntity<>(value, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    public ResponseEntity<List<Student>> getStudentByName(String studentName) {
        try {
            List<Student> students = new ArrayList<Student>();

            if (studentName == null)
                students.addAll(studentRepository.findAll());
            else
                students.addAll(studentRepository.findByName(studentName));
            if (students.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(students, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Student> updateStudentDetails(String id, Student student) {
        Optional<Student> inventoryData = studentRepository.findById(Integer.valueOf(id));
        if (inventoryData.isPresent()) {
            Student student1 = inventoryData.get();
            student1.setName(student.getName());
            student1.setEmail(student.getEmail());
            student1.setAge(student.getAge());
            student1.setAddress(student.getAddress());
            return new ResponseEntity<>(studentRepository.save(student1), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<HttpStatus> deleteStudent(int id) {
        try {
            studentRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<HttpStatus> deleteAllStudents() {
        try {
            studentRepository.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
