package com.apontejaj.accela;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.apontejaj.entities.Person;
import com.apontejaj.repositories.PersonRepository;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class PersonJPAUnitTest {

	@Autowired
	private PersonRepository repository;

	@Test
	public void should_add_a_person() {
		Person person = repository.save(new Person("Amilcar", "Aponte"));

		assertThat(person).hasFieldOrPropertyWithValue("firstName", "Amilcar");
		assertThat(person).hasFieldOrPropertyWithValue("lastName", "Aponte");

	}
	
	@Test
	public void should_edit_a_person() {
		Person person = repository.save(new Person("Amilcar", "Aponte"));
		
		person.setFirstName("Michael");
		person.setLastName("Jackson");
		
		person = repository.save(person);

		assertThat(person).hasFieldOrPropertyWithValue("firstName", "Michael");
		assertThat(person).hasFieldOrPropertyWithValue("lastName", "Jackson");

	}

	@Test
	public void should_find_all_persons() {
		Person p1 = new Person("Amilcar", "Aponte");
		repository.save(p1);

		Person p2 = new Person("Mickey", "Mouse");
		repository.save(p2);

		Person p3 = new Person("Donald", "Duck");
		repository.save(p3);

		Iterable<Person> persons = repository.findAll();

		assertThat(persons).hasSize(3).contains(p1, p2, p3);
	}

	@Test
	public void should_find_person_by_id() {
		Person p1 = new Person("Amilcar", "Aponte");
		repository.save(p1);

		Person p2 = new Person("Mickey", "Mouse");
		repository.save(p2);

		Person foundPerson = repository.findById(p2.getId()).get();

		assertThat(foundPerson).isEqualTo(p2);
	}
	
	@Test
	public void should_delete_person_by_id() {
		Person p1 = new Person("Amilcar", "Aponte");
		repository.save(p1);

		Person p2 = new Person("Mickey", "Mouse");
		repository.save(p2);
		
		Person p3 = new Person("Donald", "Duck");
		repository.save(p3);
		
		repository.deleteById(p2.getId());
		
		Iterable<Person> persons = repository.findAll();

		assertThat(persons).hasSize(2).contains(p1, p3);
	}
	
	@Test
	public void should_count_persons() {
		Person p1 = new Person("Amilcar", "Aponte");
		repository.save(p1);

		Person p2 = new Person("Mickey", "Mouse");
		repository.save(p2);
		
		Person p3 = new Person("Donald", "Duck");
		repository.save(p3);
		
		long count = repository.count();

		assertThat(count).isEqualTo(3);
	}

}
