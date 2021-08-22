package com.apontejaj.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.apontejaj.entities.Address;

/**
 * We don't need to write anything in this interface. 
 * Everything is inherited from the CrudRepository interface.
 * 
 * @author apont
 *
 */
@Repository
public interface AddressRepository extends CrudRepository<Address, Integer> {

}
