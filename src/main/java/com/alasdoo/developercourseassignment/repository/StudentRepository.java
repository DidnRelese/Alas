package com.alasdoo.developercourseassignment.repository;

import com.alasdoo.developercourseassignment.entity.Student;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {

    Optional<Student> findByAccountName(String accountName);

    Optional<Student> findByAccountNameAndPassword(String accountName, String password);
   
    //I made a custom native query that return same value like findByAccountName but it doesent care about Upper or Lower case so it can find name even if casing is different
    @Query(value = "Select * from student where UPPER(account_name)=:accName ", nativeQuery = true)
	Optional<Student> returnByAccountName(@Param("accName") String accName);

}
