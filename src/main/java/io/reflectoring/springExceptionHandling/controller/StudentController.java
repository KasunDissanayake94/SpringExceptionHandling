package io.reflectoring.springExceptionHandling.controller;

import io.reflectoring.springExceptionHandling.dto.StudentRequest;
import io.reflectoring.springExceptionHandling.exception.UserNotFoundException;
import io.reflectoring.springExceptionHandling.model.Student;
import io.reflectoring.springExceptionHandling.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @PostMapping("/create/students")
    public ResponseEntity<Student> createStudent(@RequestBody @Valid StudentRequest studentRequest){
        return studentService.saveStudent(studentRequest);
    }

    @GetMapping("/get/students/all")
    public ResponseEntity<List<Student>> getAllStudents(){
        return studentService.getAllStudents();
    }

    @GetMapping("/get/student/{studentId}")
    public ResponseEntity<Student> getStudentById(@PathVariable(value = "studentId") int studentId) throws UserNotFoundException {
        return studentService.getStudentById(studentId);
    }

    @GetMapping("/get/students/{studentName}")
    public ResponseEntity<List<Student>> getStudentByName(@RequestParam(value = "studentName") String studentName){
        return studentService.getStudentByName(studentName);
    }

    @PutMapping("/update/students/{studentId}")
    public ResponseEntity<Student> updateStudentDetails(@PathVariable("id") String studentId, @RequestBody StudentRequest studentRequest){
        return studentService.updateStudentDetails(studentId, studentRequest);

    }

    @DeleteMapping("/delete/students/{studentId}")
    public ResponseEntity<HttpStatus> deleteStudent(int studentId) {
        return studentService.deleteStudent(studentId);

    }

    @DeleteMapping("/delete/students")
    public ResponseEntity<HttpStatus> deleteAllStudents() {
        return studentService.deleteAllStudents();

    }

}
