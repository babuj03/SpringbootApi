package com.users.info.test.unit;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.user.info.UsersApp;
import com.user.info.service.UserService;
import com.user.info.service.dto.UserDTO;
import com.user.info.service.errors.UserNotFoundException;

@SpringBootTest(classes = UsersApp.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@EnableConfigurationProperties
public class UserTest {

	@Autowired
	MockMvc mockmvc;

	@MockBean
	UserService userService;

	@LocalServerPort
	int port;

	@Value("${spring.security.user.name}")
	private String username;

	@Value("${spring.security.user.password}")
	private String password;

	static List<UserDTO> dtoList = new ArrayList<UserDTO>();

	String auth;

	@BeforeAll
	public static void init() {
		dtoList.add(UserDTO.builder().first_name("Babu").last_name("bab@tes.com").id(1l).build());
		dtoList.add(UserDTO.builder().first_name("Jay").last_name("jay@tes.com").id(2l).build());
	}

	@Test
	public void getAllUser() throws Exception {

		mockmvc.perform(get(createURLWithPort("/users/"))
				.accept(MediaType.APPLICATION_JSON)).andDo(print())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(content().json("[]"));
		verify(userService, times(1)).users();
	}
	

	@Test
	public void getUserById() throws Exception {

		when(userService.getUserById(1l)).thenReturn(dtoList.get(0));
		mockmvc.perform(get(createURLWithPort("/users/1"))
				.contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andExpect(jsonPath("$.id", is(1)))
				.andExpect(jsonPath("$.first_name", is("Babu")));
	}

	@Test
	public void UserNotFound() throws Exception {
		when(userService.getUserById(131l)).thenThrow(UserNotFoundException.class);
		mockmvc.perform(get(createURLWithPort("/users/131"))
				.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
				.andDo(print()).andExpect(status().isNotFound());

	}


	private String createURLWithPort(String uri) {
		return "http://localhost:" + port + "/api/v1" + uri;
	}

	
}
