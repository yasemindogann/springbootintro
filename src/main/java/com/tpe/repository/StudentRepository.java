package com.tpe.repository;

import com.tpe.domain.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long>{
//JpaRepository'den hazır kodlar gelecek
//Student, Long: Entity class + unique field(id) data type

    boolean existsByEmail(String email);

}
