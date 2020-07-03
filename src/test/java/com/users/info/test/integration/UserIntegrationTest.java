package com.users.info.test.integration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.user.info.UsersApp;
import com.user.info.service.dto.UserDTO;
import com.user.info.service.errors.ExceptionResponse;

@SpringBootTest(classes = UsersApp.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserIntegrationTest {

	@LocalServerPort
	int port;
	
	@Value("${spring.security.user.name}")
	private String username;

	@Value("${spring.security.user.password}")
	private String password;
	 
	static TestRestTemplate testRestTemplate = null;

	@BeforeAll
	public static void init() {
		testRestTemplate = new TestRestTemplate();
	}

	@Test
	public void getAllUser() {
		ResponseEntity<UserDTO[]> responseEntity = testRestTemplate.getForEntity(createURLWithPort("/users/"), UserDTO[].class);
		assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);
		assertTrue(responseEntity.getBody().length > 0);
	}
	
	@Test
	public void getUserById() {
		ResponseEntity<UserDTO> user = testRestTemplate.getForEntity(createURLWithPort("/users/101/") , UserDTO.class);
		assertEquals(user.getStatusCode(), HttpStatus.OK);
	}

	
	@Test
	public void getUserByInvalidID() {
		ResponseEntity<UserDTO> user = testRestTemplate.getForEntity(createURLWithPort("/users/11111"), UserDTO.class);
		assertEquals(user.getStatusCode(), HttpStatus.NOT_FOUND);
	}

	
	private String createURLWithPort(String uri) {
	    return "http://localhost:" + port +"/api/v1"+uri;
	}
	
	@AfterAll
	public static void clean() {
		testRestTemplate = null;
	}
}