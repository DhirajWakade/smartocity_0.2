package com.allinone.mailconfiguration;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface OneTimePasswordRepository extends JpaRepository<OneTimePassword, Long> {

	public OneTimePassword findByEmail(String emailId);
}
