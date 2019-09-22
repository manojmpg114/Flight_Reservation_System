package com.group.FRS.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "ticket")
public class Ticket {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name="payment_info")
	private String paymentInfo;

	@JsonIgnore
	@OneToOne(mappedBy = "ticket")
	private PassengerSchedule passengerSchedule;
	
	

	public Ticket() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Ticket( String paymentInfo, PassengerSchedule passengerSchedule) {
		super();
		this.paymentInfo = paymentInfo;
		this.passengerSchedule = passengerSchedule;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPaymentInfo() {
		return paymentInfo;
	}

	public void setPaymentInfo(String paymentInfo) {
		this.paymentInfo = paymentInfo;
	}

	public PassengerSchedule getPassengerSchedule() {
		return passengerSchedule;
	}

	public void setPassengerSchedule(PassengerSchedule passengerSchedule) {
		this.passengerSchedule = passengerSchedule;
	}

	@Override
	public String toString() {
		return "Ticket [id=" + id + ", paymentInfo=" + paymentInfo + ", passengerSchedule=" + passengerSchedule + "]";
	}

	
	

	

	
}