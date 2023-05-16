package com.nurseryAbsence.Repos;

import org.springframework.data.jpa.repository.JpaRepository;


import com.nurseryAbsence.Models.Signup;

public interface SignupRepo extends JpaRepository<Signup, Integer> {

	Signup findByEmail(String email);	
}
