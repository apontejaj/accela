package com.apontejaj.accela;


import java.util.InputMismatchException;
import java.util.Optional;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.apontejaj.entities.Address;
import com.apontejaj.entities.Person;
import com.apontejaj.repositories.AddressRepository;
import com.apontejaj.repositories.PersonRepository;


@Component
public class Runner implements CommandLineRunner {

	private static final Logger logger = LoggerFactory.getLogger(Runner.class);
	Scanner input = new Scanner(System.in);
	
	@Autowired
	private PersonRepository personRepository;
	
	@Autowired
	private AddressRepository addressRepository;

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		
		logger.info("App running");
		
		String stillRunning;
		do {
			validationLoop();
			System.out.println("Press any key to continue or X to exit");
			stillRunning = input.next();
				
		} while (!stillRunning.toLowerCase().equals("x"));
		
		logger.info("App finishing");
		
	}
	
	private void validationLoop() {
		
		String selection = "";

		while (selection.equals("")) {

			selection = menu();

			if (selection.toLowerCase().equals("a")) {
				newPerson();

			} else if (selection.toLowerCase().equals("b")) {
				editPerson(getId());

			} else if (selection.toLowerCase().equals("c")) {
				deletePerson(getId());

			} else if (selection.toLowerCase().equals("d")) {
				addAddressToPerson(getId());

			} else if (selection.toLowerCase().equals("e")) {
				editPersonAddress(getId());
				
			} else if (selection.toLowerCase().equals("f")) {
				deletePersonAddress(getId());
				
			} else if (selection.toLowerCase().equals("g")) {
				System.out.println("There are: " + personRepository.count() + " persons");
				
			} else if (selection.toLowerCase().equals("h")) {
				for(Person person : personRepository.findAll()) {
					System.out.println(person);
				}
				
			} 
			else {
				System.out.println("\n--------------------------------");
				System.out.println("| Please, try a valid selection |");
				System.out.println("--------------------------------");
				selection = "";
			}

		}
		
	}
	
	private String menu() {

		String selection;

		System.out.println("\nSelect your option");
		System.out.println("-------------------------\n");
		System.out.println("A - Add a new person");
		System.out.println("B - Edit a person -You need their ID");
		System.out.println("C - Delete a person -You need their ID");
		System.out.println("D - Add address to a person -You need their ID");
		System.out.println("E - Edit a person's address -You need their ID");
		System.out.println("F - Delete an address from a person -You need the person's ID");
		System.out.println("G - To see how many persons there are");
		System.out.println("H - To list all people");
		
		selection = input.next();
		return selection;
	}
	
	
	
	private void newPerson() {
		
		String[] names = getPersonNames();
		
		Person person = personRepository.save(new Person(names[0], names[1]));
		System.out.println(person);
		
	}
	
	private Integer getId() {
		
		int id;
		do {
		    System.out.println("\nType in the ID:");
		    while (!input.hasNextInt()) {
		        System.out.println("We need an integer number here");
		        input.next(); 
		    }
		    id = input.nextInt();
		    if(id <= 0) {
		    	System.out.println("We need an POSITIVE integer number here");
		    }
		} while (id <= 0);
		
		return id;

	}
	
	private String[] getPersonNames() {
		System.out.println("\nType in the first name:");
		String firstName = input.next();
		System.out.println("Type in the last name");
		String lastName = input.next();
		
		String[] names = {firstName, lastName};
		return names;
		
	}
	
	private String[] getAddressDetails() {
		System.out.println("\nType in the Street:");
		String street = input.next();
		System.out.println("Type in the City");
		String city = input.next();
		System.out.println("Type in the State");
		String state = input.next();
		System.out.println("Type in the Postal Code");
		String postalCode = input.next();
		
		String[] addressDetails = {street, city, state, postalCode};
		return addressDetails;
		
	}
	
	private void editPerson(Integer id) {

		Optional<Person> record = personRepository.findById(id);
		if(record.isEmpty()) {
			System.out.println("No person with this ID");
			return;
		}
		Person person = record.get();
		
		System.out.println("\nWe'll modify:");
		System.out.println(person);
		
		String[] names = getPersonNames();
		person.setFirstName(names[0]);
		person.setLastName(names[1]);
		person = personRepository.save(person);
		System.out.println(person);
		
	}
	
	private void deletePerson(Integer id) {
		
		Optional<Person> record = personRepository.findById(id);
		if(record.isEmpty()) {
			System.out.println("No person with this ID");
			return;
		}
		
		personRepository.deleteById(id);
		System.out.println("Person deleted!");
	}
	
	private void addAddressToPerson(Integer id) {
		
		Optional<Person> record = personRepository.findById(id);
		if(record.isEmpty()) {
			System.out.println("No person with this ID");
			return;
		}
		Person person = record.get();
		
		System.out.println("\nWe'll add a new address to:");
		System.out.println(person);
		
		String[] addressDetails = getAddressDetails();
		
		Address address = new Address(addressDetails[0],
									  addressDetails[1],
									  addressDetails[2],
									  addressDetails[3]);
		
		person.addAddress(address);

		addressRepository.save(address);
		personRepository.save(person);
		
	}
	
	private void editPersonAddress(Integer personId) {
		
		Optional<Person> record = personRepository.findById(personId);
		if(record.isEmpty()) {
			System.out.println("No person with this ID");
			return;
		}
		Person person = record.get();
		
		if(person.getAddresses().isEmpty()) {
			System.out.println("\nNo addresses to edit in this user");
			return;
		}
		
		System.out.println("\nWe'll edit one of these addresses:");
		for(Address address : person.getAddresses()) {
			System.out.println(address);
		}

		Integer addressId;
		Optional<Address> addressRecord;
		do  {
			System.out.println("\nWhich one you want to edit:");
			addressId = getId();
			addressRecord = addressRepository.findById(addressId);
			if(addressRecord.isEmpty()) {
				System.out.println("No address with this ID");
			}	
		} while (addressRecord.isEmpty());
		Address address = addressRecord.get();
		
		System.out.println("\nWe'll edit:");
		System.out.println(address);
		
		String[] addressDetails = getAddressDetails();
		address.setStreet(addressDetails[0]);
		address.setCity(addressDetails[1]);
		address.setState(addressDetails[2]);
		address.setPostalCode(addressDetails[3]);
		address = addressRepository.save(address);
		System.out.println(address);
		
	}
	
	private void deletePersonAddress(Integer personId) {
		Optional<Person> record = personRepository.findById(personId);
		if(record.isEmpty()) {
			System.out.println("No person with this ID");
			return;
		}
		Person person = record.get();
		
		if(person.getAddresses().isEmpty()) {
			System.out.println("\nNo addresses to delete in this user");
			return;
		}
		
		System.out.println("\nWe'll delete one of these addresses:");
		for(Address address : person.getAddresses()) {
			System.out.println(address);
		}
		
		Integer addressId;
		Optional<Address> addressRecord;
		do  {
			System.out.println("\nWhich one you want to delete:");
			addressId = getId();
			addressRecord = addressRepository.findById(addressId);
			if(addressRecord.isEmpty()) {
				System.out.println("No address with this ID");
			}	
		} while (addressRecord.isEmpty());
		Address address = addressRecord.get();
		

		person.removeAddress(address);
		address.removePerson(person);
		
		personRepository.save(person);
		addressRepository.save(address);
		
		addressRepository.delete(address);
		
		System.out.println("Address deleted");
		
	}
	
}