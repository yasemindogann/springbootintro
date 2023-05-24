package com.tpe.controller;

import com.tpe.domain.Student;
import com.tpe.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController //response + status code
@RequestMapping("/students")    // http://localhost:8080/students
public class StudentController {

    @Autowired  //Field injection tavsiye edilmez   //Constructor injection tavsiye edilir.
    private StudentService studentService;


    //Bütün öğrencileri getirelim
    @GetMapping()   // http://localhost:8080/students + GET
    public ResponseEntity<List<Student>> getAll(){
        List<Student> students = studentService.getAll();
        return ResponseEntity.ok(students); //List<Student> + HTTP Status Code = 200
        //ok: 200 Status code
        //Student yoksa içi boş JSON [] gelir.
    }


    //Student create
    @PostMapping()//http://localhost:8080/students + POST + JSON
    public ResponseEntity<Map<String,String>> createStudent(@Valid @RequestBody Student student){
        studentService.createStudent(student);

        Map<String,String> map = new HashMap<>();   //Neden new'ledim? --> Çünkü 1 defa kullandım bitti.
        map.put("message", "Student is created successfuly");
        map.put("status", "true"); //STATUSCODE = 201

        //return ResponseEntity.ok(map); //ok() newlwmw işlemi yapıyor. //OR
        return new ResponseEntity<>(map, HttpStatus.CREATED);
    }

    //Belli bir id'li öğrenciyi getirme
    //@PathVariable ==> http://localhost:8080/students/1
    //@RequestParam ==> http://localhost:8080/students/query?id=1
    //İkisini de kullanabilirim.
    //Genelde birden fazla parametre için @RequestParam kullanılır.
    //@RequestParam daha açıklayıcı oluyor  ==> "query?id=1" bunun gibi
    @GetMapping("/query") //http://localhost:8080/students/query?id=1
    public ResponseEntity<Student> getStudent(@RequestParam("id") Long id){
        Student student = studentService.findStudent(id);
        return ResponseEntity.ok(student);
    }


    //@PathVariable ile yapalım
    @GetMapping("/{id}") //http://localhost:8080/students/1
    public ResponseEntity<Student> getStudent2(@PathVariable("id") Long id){
        Student student = studentService.findStudent(id);
        return ResponseEntity.ok(student);
    }


    //Delete student
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String,String>> deleteStudent(@PathVariable("id") Long id){
        studentService.deleteStudent(id);
        Map<String,String> map = new HashMap<>();
        map.put("message", "Student is deleted successfuly");
        map.put("status", "true");

        return ResponseEntity.ok(map); //OR return new ResponseEntity<>(map, HttpStatus.OK);
    }


}
