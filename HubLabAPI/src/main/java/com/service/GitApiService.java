package com.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.models.UserRepositoryInfo;

@Service
public class GitApiService {
	
	@Autowired
	private HttpCaller httpCaller;
	

	public Map<String, List<UserRepositoryInfo>> fetchUserWithRepos(boolean owenership) throws Exception {
		return	httpCaller.callApi(owenership);
	}

}
