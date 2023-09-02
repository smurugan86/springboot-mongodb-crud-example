package net.guides.springboot.crud.model;

import java.util.Date;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

@Document (collection = "payment")
public class PayEvent {

	@Transient
    public static final String SEQUENCE_NAME = "users_sequence";
	
	@Id
	private long id;
	
	@NotBlank
    @Size(max=100)
	private String details;
	
	private Double amount;
	
	private String payType;
	
	
	
	public String getPayType() {
		return payType;
	}

	public void setPayType(String payType) {
		this.payType = payType;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public Date getEventDate() {
		return eventDate;
	}

	public void setEventDate(Date eventDate) {
		this.eventDate = eventDate;
	}

	
	private Date eventDate;
	
	public PayEvent() {
		
	}
	
	public void payEvent(String details, Double amount, Date eventDate) {
		this.details = details;
		this.amount = amount;
		this.eventDate = eventDate;
	}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "Employee [id=" + id + ", details=" + details + ", amount=" + amount + ", eventDate=" + eventDate
				+ "]";
	}	
}
