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

import com.group.FRS.model.Ticket;
import com.group.FRS.repository.TicketRepository;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/ticket")
public class TicketController {
	
	@Autowired
	TicketRepository ticketRepository;
	
	@GetMapping(path ="/getAll", produces="application/json")
	public List<Ticket> getAllTickets(){
		return ticketRepository.findAll();
	}
    

	@PostMapping(path ="/add")
    public ResponseEntity<Ticket> create( @RequestBody Ticket ticket){
		return ResponseEntity.ok(ticketRepository.save(ticket));
    }
	
	@PutMapping(path ="/update/{id}")
	   public ResponseEntity<Ticket> updateDoctor(@PathVariable(value = "id") Long tickedId,
	                                              @Valid @RequestBody Ticket ticketDetails) {
	       Ticket ticket = ticketRepository.findById( tickedId).orElse(null);
	       ticket.setPaymentInfo(ticketDetails.getPaymentInfo());
	       return ResponseEntity.ok(ticketRepository.save(ticket));
	   }
	
	@DeleteMapping(path ="/delete/{id}")
    public void delete(@PathVariable("id") Long id) {
         ticketRepository.deleteById(id);
    }
	
	
}

