package com.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonParseException;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;
import com.models.UserRepositoryInfo;

@Component
public class HttpCaller {

	@Value("${access.tocken.github}")
	private String accessTockenGitHub;

	@Value("${access.tocken.gitlab}")
	private String accessTockenGitLab;

	@Value("${user.id.gitlab}")
	private String userid;

	private static final String INFO_NOT_AVAILABLE = "Information not Available !";

	private static final String GIT_HUB_API = "https://api.github.com/user/repos?access_token=";
	private static final String GIT_HUB_FILTER = "&affiliation=owner";
	private static final String GIT_LAB_API = "https://gitlab.com/api/v4/users/";
	private static final String GIT_LAB_API_END = "/projects?private_token=";
	private static final String GIT_LAB_FILTER = "&owned=true";

	public Map<String, List<UserRepositoryInfo>> callApi(boolean owenership) throws Exception {
		HashMap<String, List<UserRepositoryInfo>> result = new HashMap<>();

		result.put("GitHub", fetchForGitHub(owenership));
		result.put("GitLab", fetchForGitLab(owenership));

		return result;

	}

	private List<UserRepositoryInfo> fetchForGitHub(boolean owenership) throws Exception {
		String url = "";
		if (owenership) {
			url = new StringBuilder(GIT_HUB_API).append(accessTockenGitHub).append(GIT_HUB_FILTER).toString();
		} else {
			url = new StringBuilder(GIT_HUB_API).append(accessTockenGitHub).toString();
		}
		List<UserRepositoryInfo> obj = new ArrayList<>();
		String jsonResponse = "";
		try {
			jsonResponse = callHttpClient(url);
			if (!jsonResponse.isEmpty()) {
				JsonElement jelement = new JsonParser().parse(jsonResponse);
				JsonArray jarr = jelement.getAsJsonArray();
				for (int i = 0; i < jarr.size(); i++) {
					JsonObject jo = (JsonObject) jarr.get(i);
					JsonObject owenerInfo = (JsonObject) jo.get("owner");

					UserRepositoryInfo res = new UserRepositoryInfo(extractField("full_name", jo),
							extractField("html_url", jo), extractField("login", owenerInfo));

					obj.add(res);
				}
			}
			return obj;
		} catch (JsonParseException | JsonSyntaxException exception) {
			System.err.println(exception.getStackTrace());
			throw new Exception(jsonResponse);
		} catch (Exception exception) {
			System.err.println(exception.getStackTrace());
			throw exception;
		}
	}

	private List<UserRepositoryInfo> fetchForGitLab(boolean owenership) throws Exception {
		String url = "";
		if (owenership) {
			url = new StringBuilder(GIT_LAB_API).append(userid).append(GIT_LAB_API_END).append(accessTockenGitLab)
					.append(GIT_LAB_FILTER).toString();
		} else {
			url = new StringBuilder(GIT_LAB_API).append(userid).append(GIT_LAB_API_END).append(accessTockenGitLab)
					.toString();
		}

		List<UserRepositoryInfo> obj = new ArrayList<>();
		String jsonResponse = "";
		try {
			jsonResponse = callHttpClient(url);
			if (!jsonResponse.isEmpty()) {
				JsonElement jelement = new JsonParser().parse(jsonResponse);
				JsonArray jarr = jelement.getAsJsonArray();
				for (int i = 0; i < jarr.size(); i++) {
					JsonObject jo = (JsonObject) jarr.get(i);
					JsonObject owenerInfo = (JsonObject) jo.get("owner");

					UserRepositoryInfo res = new UserRepositoryInfo(extractField("path_with_namespace", jo),
							extractField("web_url", jo), extractField("name", owenerInfo));
					obj.add(res);
				}
			}
			return obj;
		} catch (JsonParseException | JsonSyntaxException exception) {
			System.err.println(exception.getStackTrace());
			throw new Exception(jsonResponse);
		} catch (Exception exception) {
			System.err.println(exception.getStackTrace());
			throw exception;
		}
	}

	private String callHttpClient(String url) throws Exception {
		try {
			CloseableHttpClient httpClient = HttpClientBuilder.create().build();
			HttpGet request = new HttpGet(url);
			request.addHeader("content-type", "application/json");
			HttpResponse result = httpClient.execute(request);
			return EntityUtils.toString(result.getEntity(), "UTF-8");
		} catch (Exception e) {
			System.err.println("Error while calling http client : " + e);
			throw e;
		}
	}

	private String extractField(String field, JsonObject jo) {
		return jo.get(field) != null ? jo.get(field).toString() : INFO_NOT_AVAILABLE;
	}
}
