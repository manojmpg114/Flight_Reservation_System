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
import com.group.FRS.model.Passenger;
import com.group.FRS.repository.FlightRepository;
import com.group.FRS.repository.PassengerRepository;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/passenger")
public class PassengerController {


	@Autowired
	PassengerRepository passengerRepository;
	
	@Autowired
	FlightRepository flightRepository;
	
	@GetMapping(path="/getAll", produces="application/json")
	public List<Passenger> getAllPassengers(){
		return passengerRepository.findAll();
	}
    

	@PostMapping(path="/addPassenger")
    public ResponseEntity<Passenger> create(@RequestBody Passenger passenger){
		return ResponseEntity.ok(passengerRepository.save(passenger));
    }
	
	@PutMapping(path="/update/{id}")
	   public ResponseEntity<Passenger> updateDoctor(@PathVariable(value = "id") Long passengerId,
	                                              @Valid @RequestBody Passenger passengerDetails) {
	       Passenger passenger = passengerRepository.findById( passengerId).orElse(null);
	       passenger.setAge(passengerDetails.getAge());
	       passenger.setName(passengerDetails.getName());
	       passenger.setGender(passengerDetails.getGender());
	       passenger.setSeatNo(passengerDetails.getSeatNo());
	       passenger.setBookingDate(passengerDetails.getBookingDate());
	       return ResponseEntity.ok(passengerRepository.save(passenger));
	   }
	
	@DeleteMapping(path="/delete/{id}")
    public void delete(@PathVariable("id") Long id) {
         passengerRepository.deleteById(id);
    }
	
}