package com.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.models.UserRepositoryInfo;
import com.service.GitApiService;

@RestController
public class GitApiController {
	
	@Autowired
	private GitApiService gitApiService;
	
	@GetMapping("/userrepos")
    public Map<String, List<UserRepositoryInfo>> getUserRepo(@RequestParam(value="owenership", defaultValue = "false", required = false) boolean owenership) throws Exception {
		return gitApiService.fetchUserWithRepos(owenership);
    }

}
