package com.allinone.admin.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.allinone.admin.model.AdminUser;

public interface AdminUserRepository extends JpaRepository<AdminUser, Long>
{
	public AdminUser findByAdUserMail(String mail);
}
