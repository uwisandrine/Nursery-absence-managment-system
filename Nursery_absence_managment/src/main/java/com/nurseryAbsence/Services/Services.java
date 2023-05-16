package com.nurseryAbsence.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.nurseryAbsence.Models.Models;
import com.nurseryAbsence.Models.Signup;
import com.nurseryAbsence.Repos.Kidrepo;
import com.nurseryAbsence.Repos.SignupRepo;

@Service
public class Services implements Save{
    @Autowired
	private Kidrepo kidrepo;
    @Autowired
    private SignupRepo signupRepo;
	@Override
	public Models savekid(Models kid) {
		
		return kidrepo.save(kid);
	}
	@Override
	public List<Models> getALLkidds() {
		// TODO Auto-generated method stub
		return kidrepo.findAll();
	}
	@Override
	public Models updateUser(Models models) {
		// TODO Auto-generated method stub
		Models existingUser = kidrepo.findById(models.getId()).get();
		existingUser.setFname(models.getFname());
		existingUser.setLname(models.getLname());
		existingUser.setCname(models.getCname());
		existingUser.setLevel(models.getLevel());
		existingUser.setDate(models.getDate());
		existingUser.setAtendance(models.getAtendance());
		existingUser.setStid(models.getStid());
		
        Models updatedUser = kidrepo.save(existingUser);
        return updatedUser;
		
	}
	@Override
	public Signup createacccount(Signup account) {
		// TODO Auto-generated method stub
		return signupRepo.save(account);
	}
	@Override
	public Page<Models> getPaginated(int pageNo, int pageSize) {
		
		PageRequest pageable= PageRequest.of(pageNo-1,pageSize);
		return this.kidrepo.findAll(pageable);
	}
	
}
