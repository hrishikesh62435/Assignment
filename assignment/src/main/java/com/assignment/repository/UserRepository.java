package com.assignment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.assignment.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {
	
	@Query("select u from User u where u.mobile = :mobile")
	public User getUserByUserName(@Param("mobile") String mobile);
	



}
