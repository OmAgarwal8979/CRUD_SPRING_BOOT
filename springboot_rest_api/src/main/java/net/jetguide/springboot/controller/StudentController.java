package net.jetguide.springboot.controller;

import net.jetguide.springboot.bean.Student;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("students")
public class StudentController {
    // http://localhost:8080/student
    @GetMapping("student")
    public ResponseEntity<Student> getStudent(){
        Student student = new Student(
                1,
                "John",
                "Doe"
        );
//        return new ResponseEntity<>(student,HttpStatus.OK);
//        return ResponseEntity.ok(student);
        return ResponseEntity.ok().header("custom-header","john")
                .body(student);
    }
//    http://localhost:8080/students
    @GetMapping
    public List<Student> getStudents() {
        List<Student> students = new ArrayList<>();
        students.add(new Student(1,"Mary","Jane"));
        students.add(new Student(2,"John","Doe"));
        students.add(new Student(3,"Jason","Walter"));
        students.add(new Student(4,"Paul","Walker"));

        return students;
    }

    //Spring Boot with Path Variable
    //{id} - URI template variable
    //http://localhost:8080/students/1/mary/jane
    @GetMapping("{id}/{first-name}/{last-name}")
    public Student studentPathVariable(@PathVariable("id") int studentId,
                                       @PathVariable("first-name") String firstName,
                                       @PathVariable("last-name") String lastName){
        return new Student(studentId, firstName,lastName);
    }

    //Spring boot api with request Params
    //http://localhost:8080/students/query?id=1&firstName=John&lastName=Doe
    @GetMapping("query")
    public Student studentRequestVariable(@RequestParam int id,
                                          @RequestParam String firstName,
                                          @RequestParam String lastName){
        return new Student(id,firstName,lastName);
    }

    //Spring boot Rest Api to handle post request - create new resource
    //@PostMapping and @RequestBody

    @PostMapping("create")
    @ResponseStatus(HttpStatus.CREATED)
    public Student createStudent(@RequestBody Student student){
        System.out.println(student.getId());
        System.out.println(student.getFirstName());
        System.out.println(student.getLastName());
        return student;
    }
    //spring boot rest api to handle put request - update resource

    @PutMapping("{id}/update")
    public Student updatestudent(@RequestBody Student student,@PathVariable("id") int studentId){
        System.out.println(student.getFirstName());
        System.out.println(student.getLastName());
        return student;
    }

    //spring boot rest api to handle delete request
    @DeleteMapping("{id}/delete")
    public String deleteStudent(@PathVariable("id") int studentid){
        System.out.println(studentid);
        return "Student deleted Successfully";
    }
}
