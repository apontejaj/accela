package com.apontejaj.accela;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.apontejaj.entities.Address;
import com.apontejaj.entities.Person;
import com.apontejaj.repositories.AddressRepository;
import com.apontejaj.repositories.PersonRepository;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class AddressPersonUnitTest {
	
	@Autowired
	private PersonRepository personRepository;
	
	@Autowired
	private AddressRepository addressRepository;
	
	@Test
	public void should_add_many_addresses_to_a_person() {
		Address a1 = new Address("5th Av", "New York", "NY", "123456");
		Address a2 = new Address("Parnell Street", "Dublin", "Leinster", "987321");
		Person person = new Person("Amilcar", "Aponte");

		a1.addPerson(person);
		a2.addPerson(person);
		person.addAddress(a1);
		person.addAddress(a2);
		
		person = personRepository.save(person);
		a1 = addressRepository.save(a1);
		a2 = addressRepository.save(a2);
		
		assertThat(person.getAddresses()).hasSize(2).contains(a1, a2);
		assertThat(a1.getPersons()).hasSize(1).contains(person);
		assertThat(a2.getPersons()).hasSize(1).contains(person);

	}
	
	@Test
	public void should_add_an_address_to_an_existing_person() {

		Person person = new Person("Amilcar", "Aponte");
		person = personRepository.save(person);
		
		Address a1 = new Address("5th Av", "New York", "NY", "123456");
		
		a1.addPerson(person);
		person.addAddress(a1);
		
		a1 = addressRepository.save(a1);
		person = personRepository.save(person);
		
		
		assertThat(person.getAddresses()).hasSize(1).contains(a1);
		assertThat(a1.getPersons()).hasSize(1).contains(person);


	}
	
	@Test
	public void should_delete_an_address_from_existing_person() {

		Person person = new Person("Amilcar", "Aponte");
		person = personRepository.save(person);
		
		Address a1 = new Address("5th Av", "New York", "NY", "123456");
		
		a1.addPerson(person);
		person.addAddress(a1);
		
		a1 = addressRepository.save(a1);
		person = personRepository.save(person);
		
		person.removeAddress(a1);
		
		addressRepository.deleteById(a1.getId());
		personRepository.save(person);

		assertThat(person.getAddresses()).hasSize(0);
		assertThat(addressRepository.count()).isEqualTo(0);

	}
	

}
