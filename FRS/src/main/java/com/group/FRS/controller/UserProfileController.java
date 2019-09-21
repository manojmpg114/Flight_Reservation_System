package com.group.FRS.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.group.FRS.model.UserProfile;
import com.group.FRS.repository.User_ProfileRepository;


@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/user_profile")
public class UserProfileController {
	@Autowired
	User_ProfileRepository userProfileRepository;
	
	@GetMapping(path ="/getAll",produces="application/json")
	 public List<UserProfile>findUser_Profiles() {
	 	return userProfileRepository.findAll();
	}
	 
    @PostMapping(path ="/add")
    public ResponseEntity<UserProfile> create( @RequestBody UserProfile userProfile){
    	return ResponseEntity.ok(userProfileRepository.save(userProfile));
    }
    
    @PutMapping(path ="/update/{id}")
	   public ResponseEntity<UserProfile> updateDoctor(@PathVariable(value = "id") Long id,
	                                              @Valid @RequestBody UserProfile userProfileDetails) {
    	UserProfile userProfile = userProfileRepository.findById(id).orElse(null);
    	userProfile.setName(userProfileDetails.getName());
    	userProfile.setDateOfBirth(userProfileDetails.getDateOfBirth());
    	userProfile.setGender(userProfileDetails.getGender());
    	userProfile.setAddress(userProfileDetails.getAddress());
    	userProfile.setMobileNumber(userProfileDetails.getMobileNumber());
    	userProfile.setEmailId(userProfileDetails.getEmailId());
    	userProfile.setReservations(userProfileDetails.getReservations());
    	userProfile.setUserCredential(userProfileDetails.getUserCredential());
    	return ResponseEntity.ok(userProfileRepository.save(userProfile));
	   }
    
    @DeleteMapping(path ="/delete/{id}")
    public void delete(@PathVariable("id") Long id) {
    	userProfileRepository.deleteById(id);
    }
}