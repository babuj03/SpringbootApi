package com.user.info.rest;

import com.user.info.service.UserService;
import com.user.info.service.dto.UserDTO;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin(origins = "https://userapi-springboot.herokuapp.com/")
public class UserResource {
    private final Logger log = LoggerFactory.getLogger(UserResource.class);

    @Autowired
    private  UserService userService;
    
	 public UserResource(UserService userService) {
		   this.userService = userService;
	  }
    
    /**
     * {@code GET /users} : get all users.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body all users.
     */
	 @CrossOrigin(origins = "https://userapi-springboot.herokuapp.com/")
	 @GetMapping("/users")
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        final List<UserDTO> users = userService.users();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }
    
    
    /**
     * Gets users by id.
     * @param userId the user id
     * @return the users by id
     * @throws UserNotFoundException the resource not found exception
     */
    @GetMapping("/users/id/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable(name="id") Long id) {
    	UserDTO user = userService.getUserById(id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
    
    
    /**
     * Gets users image by id.
     * @param userId the user id
     * @return the users image by id
     * @throws IOException
     */
    @GetMapping(value = "/image/id/{id}", produces={ "image/jpeg" })
    public @ResponseBody byte[] getImageById(@PathVariable(name="id") Long id) throws IOException {
        InputStream in = getClass()
          .getResourceAsStream("/com/users/info/images/babu.jpg");
        return IOUtils.toByteArray(in);
    }
   
  
   
}
