package com.apontejaj.accela;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.apontejaj.entities.Address;
import com.apontejaj.repositories.AddressRepository;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class AddressJPAUnitTest {

	@Autowired
	private AddressRepository repository;
	
	@Test
	public void should_add_an_address() {
		Address address = repository.save(new Address("5th Av", "New York", "NY", "123456"));

		assertThat(address).hasFieldOrPropertyWithValue("street", "5th Av");
		assertThat(address).hasFieldOrPropertyWithValue("city", "New York");
		assertThat(address).hasFieldOrPropertyWithValue("state", "NY");
		assertThat(address).hasFieldOrPropertyWithValue("postalCode", "123456");

	}
	
	@Test
	public void should_edit_an_address() {
		Address address = repository.save(new Address("5th Av", "New York", "NY", "123456"));

		address.setStreet("Parnell Street");
		address.setCity("Dublin");
		address.setState("Leinster");
		address.setPostalCode("654321");
		
		address = repository.save(address);

		assertThat(address).hasFieldOrPropertyWithValue("street", "Parnell Street");
		assertThat(address).hasFieldOrPropertyWithValue("city", "Dublin");
		assertThat(address).hasFieldOrPropertyWithValue("state", "Leinster");
		assertThat(address).hasFieldOrPropertyWithValue("postalCode", "654321");
	}
	
	@Test
	public void should_delete_address_by_id() {
		Address a1 = new Address("5th Av", "New York", "NY", "123456");
		repository.save(a1);

		Address a2 = new Address("Parnell Street", "Dublin", "Leinster", "987321");
		repository.save(a2);
		
		Address a3 = new Address("College Green", "Dublin", "Leinster", "789456");
		repository.save(a3);
		
		repository.deleteById(a2.getId());
		
		Iterable<Address> addresses = repository.findAll();

		assertThat(addresses).hasSize(2).contains(a1, a3);
	}

}
