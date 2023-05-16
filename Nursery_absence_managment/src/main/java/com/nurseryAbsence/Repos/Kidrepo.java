package com.nurseryAbsence.Repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nurseryAbsence.Models.Models;

public interface Kidrepo extends JpaRepository<Models, Integer> {

}
