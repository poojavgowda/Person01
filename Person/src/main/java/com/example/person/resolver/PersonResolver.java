package com.example.person.resolver;

import com.example.person.model.Person;

import com.example.person.repository.PersonRepository;
import graphql.kickstart.tools.GraphQLMutationResolver;
import graphql.kickstart.tools.GraphQLQueryResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
import java.util.Optional;


@Component
public class PersonResolver implements GraphQLQueryResolver, GraphQLMutationResolver {
        private final PersonRepository personRepository;

        @Autowired
        public PersonResolver(PersonRepository personRepository) {
            this.personRepository = personRepository;
           }

        @Transactional
        public Person createPerson(String name, int age, String address) {


            return personRepository.saveperson(name,age,address);
        }

        @Transactional(readOnly = true)
        public Iterable<Person> getAllPersons() {
            return personRepository.findAll();
        }

        @Transactional(readOnly = true)
        public Person getPerson(Long id) {
            return personRepository.findById(id).orElse(null);
        }

        @Transactional
        public Person updatePerson(Long id, String name) {
            Optional <Person> optionalPerson = personRepository.findById(id);
            if (optionalPerson.isPresent()) {
                Person person = optionalPerson.get();
                if (name != null) {
                    person.setName(name);
                }
                return personRepository.updatename(id,name);
            }
            throw new IllegalArgumentException("Person not found with ID: " + id);
        }

        @Transactional
        public Boolean deletePerson(Long id) {
            if (personRepository.existsById(id)) {
                personRepository.deleteById(id);
                return true;
            }
            return false;
        }
    }

