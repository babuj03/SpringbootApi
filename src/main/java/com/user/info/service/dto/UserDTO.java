package com.user.info.service.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * A DTO representing a user
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDTO {
	
    private Long id;
    
    private String first_name;
    
    private String middle_name;
    
    private String last_name;
    
    private String dob;
    
    private boolean status;
    
    private String createdBy;

    private LocalDateTime createdDate;
    
    private String imageURL;

   }
