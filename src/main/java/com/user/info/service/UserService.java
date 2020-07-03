package com.user.info.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.user.info.respository.UserRepository;
import com.user.info.service.dto.UserDTO;

@Service
public class UserService {

	private UserRepository userRepository;

	
	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	public List<UserDTO> users() {
		return userRepository.getUsers();
	}

	public UserDTO getUserById(Long id) {
		return userRepository.getUserById(id);
	}

	
}
