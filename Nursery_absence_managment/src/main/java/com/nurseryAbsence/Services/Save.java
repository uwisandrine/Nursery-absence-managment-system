package com.nurseryAbsence.Services;

import java.util.List;

import org.springframework.data.domain.Page;

import com.nurseryAbsence.Models.Models;
import com.nurseryAbsence.Models.Signup;

public interface Save {

	public Models savekid (Models kid);
	
	public List<Models> getALLkidds();
	public Models updateUser(Models models);
	public Signup createacccount (Signup account);
	public Page<Models> getPaginated(int pageNo,int pageSize);
}
