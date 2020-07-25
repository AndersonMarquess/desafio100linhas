package com.andersonmarques.crud100lines;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import javax.persistence.*;

@SpringBootApplication
public class DemoApplication { public static void main(String[] args) { SpringApplication.run(DemoApplication.class, args); } }

interface CrudTestRepository extends org.springframework.data.repository.CrudRepository<CrudTest, Integer> {}

@RestController class CrudTestController {
    @org.springframework.beans.factory.annotation.Autowired CrudTestRepository repository;

    @GetMapping(path = "/crud/{id}")
    public ResponseEntity<java.util.Optional<CrudTest>> buscar(@PathVariable("id") Integer id) { return ResponseEntity.ok().body(repository.findById(id)); }

    @PostMapping(path = "/crud")
    public ResponseEntity<CrudTest> create(@RequestBody CrudTest obj) { return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(obj)); }

    @PutMapping("/crud")
    public ResponseEntity<CrudTest> update(@RequestBody CrudTest obj) { return ResponseEntity.status(HttpStatus.OK).body(repository.save(obj)); }

    @DeleteMapping(path = "/crud/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) { repository.deleteById(id); return ResponseEntity.status(HttpStatus.OK).build(); }
}

@Entity class CrudTest {
    @Id  @GeneratedValue 
    private int id;
    private String name;
    public int getId() { return id; }    
    public void setId(int id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
}