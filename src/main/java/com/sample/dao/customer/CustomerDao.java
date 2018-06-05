package com.sample.dao.customer;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface CustomerDao extends CrudRepository<Customer, Integer> {

	public List<Customer> findByLastName(String lastName);
}
