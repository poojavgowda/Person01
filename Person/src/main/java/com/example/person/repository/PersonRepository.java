package com.example.person.repository;

import com.example.person.model.Person;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;

import java.util.List;
import java.util.Optional;

public interface PersonRepository extends Neo4jRepository<Person, Long> {

    @Query("Create(p:Person{name:$name,age:$age,address:$address})return p")
    Person saveperson(String name,int age,String address);
    @Query("Match(p:Person) where id(p)=$id set p.name=$name return p")
    Person updatename(Long id,String name);

    @Query("MATCH(p:Person) RETURN p")
    List<Person>findAll();

    @Query("MATCH(p:Person) WHERE id(p)=$id RETURN p")
    Optional<Person> findById(Long id);




}
