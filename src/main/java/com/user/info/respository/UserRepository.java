

package com.user.info.respository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

import com.user.info.service.dto.UserDTO;
import com.user.info.service.errors.UserNotFoundException;

/**
 * Spring Data JPA repository for the {@link User} entity.
 */

@Component
public class UserRepository  {
	
	
	@Autowired
	private MessageSource messageSource;
	

	
 UserDTO users []= {
			
			UserDTO.builder().id(100l).first_name("Sivan").middle_name("Om").last_name("Perumal").dob("12/08/0001").status(true).createdBy("Admin").createdDate(LocalDateTime.now())
			.imageURL("http://localhost:8080/api/v1/image/id/100").build(),
			UserDTO.builder().id(101l).first_name("Parvathy").middle_name("Ambal").last_name("Sivan").dob("12/08/0100").status(true).createdBy("Admin").createdDate(LocalDateTime.now())
			.imageURL("http://localhost:8080/api/v1/image/id/101").build(),
			UserDTO.builder().id(102l).first_name("Ganesh").middle_name("Balganesh").last_name("Siva").dob("22/09/1002").status(true).createdBy("Admin").createdDate(LocalDateTime.now())
			.imageURL("http://localhost:8080/api/v1/image/id/102").build(),
			UserDTO.builder().id(103l).first_name("Saravanan").middle_name("Mayil").last_name("Siva").dob("14/06/1200").status(true).createdBy("Admin").createdDate(LocalDateTime.now())
			.imageURL("http://localhost:8080/api/v1/image/id/103").build(),
			UserDTO.builder().id(104l).first_name("Jayee").middle_name("Pala").last_name("Vaithi").dob("03/04/1400").status(false).createdBy("Admin").createdDate(LocalDateTime.now())
			.imageURL("http://localhost:8080/api/v1/image/id/104").build(),
			UserDTO.builder().id(105l).first_name("Babu").middle_name("Kani").last_name("Jayee").dob("02/01/1500").status(true).createdBy("Admin").createdDate(LocalDateTime.now())
			.imageURL("http://localhost:8080/api/v1/image/id/105").build(),
			UserDTO.builder().id(106l).first_name("Saravanan").middle_name("Mayil").last_name("Siva").dob("14/06/1200").status(true).createdBy("Admin").createdDate(LocalDateTime.now())
			.imageURL("http://localhost:8080/api/v1/image/id/106").build(),
			UserDTO.builder().id(107l).first_name("Jayee").middle_name("Pala").last_name("Vaithi").dob("03/04/1400").status(false).createdBy("Admin").createdDate(LocalDateTime.now())
			.imageURL("http://localhost:8080/api/v1/image/id/107").build(),
			UserDTO.builder().id(108l).first_name("Babu").middle_name("Kani").last_name("Jayee").dob("02/01/1500").status(true).createdBy("Admin").createdDate(LocalDateTime.now())
			.imageURL("http://localhost:8080/api/v1/image/id/108").build()
	};
	
	public List<UserDTO> getUsers(){
		return Arrays.asList(users);
	}
	
	public UserDTO getUserById(final Long id){
		return Arrays.asList(users).stream().filter(user->user.getId() == id)
		.findFirst().orElseThrow(()->  new UserNotFoundException(
				messageSource.getMessage("error.user.not.found", new Object[] { id }, LocaleContextHolder.getLocale())));
			
		}
}
