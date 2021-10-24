package com.allinone.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.allinone.model.User;
@Repository
public interface UserRepository extends JpaRepository<User, Long> 
{
	
	public User findByUserName(String username);
}
