package com.apontejaj.entities;
 
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
 
/**
 * This class is the MODEL for the persons.
 * 
 * @author apont
 *
 */
@Entity
public class Person {
 
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String firstName;
    private String lastName;
    
    @ManyToMany
    @JoinTable(name = "person_address",
    		   joinColumns = @JoinColumn(name = "person_id"), 
    		   inverseJoinColumns = @JoinColumn(name = "address_id"))
    Set<Address> addresses;
 
    /**
     * A default constructor is required by the JPA
     */
    public Person() {
    	this.addresses = new HashSet<>();
    	
    }
    
	public Person(String firstName, String lastName) {
		this.firstName = firstName;
		this.lastName = lastName;
    	this.addresses = new HashSet<>();
	}
	
	public Person(Integer id, String firstName, String lastName) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
    	this.addresses = new HashSet<>();
	}

	/**
     * @return the id
     */
    public Integer getId() {
        return id;
    }
 
    /**
     * @param id the id to set
     */
    public void setId(Integer id) {
        this.id = id;
    }
 
    /**
     * @return the firstName
     */
    public String getFistName() {
        return firstName;
    }
 
    /**
     * @param firstName the first name to set
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
 
    /**
     * @return the lastName
     */
    public String getLastName() {
        return lastName;
    }
 
    /**
     * @param lastName the last name to set
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

	public Set<Address> getAddresses() {
		return addresses;
	}

	public void setAddresses(Set<Address> addresses) {
		this.addresses = addresses;
	}
	
	public void addAddress(Address address) {
		this.addresses.add(address);
	}

	@Override
	public String toString() {
		return "Person [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + "]";
	}

}