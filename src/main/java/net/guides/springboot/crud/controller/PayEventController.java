package net.guides.springboot.crud.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

import net.guides.springboot.crud.exception.ResourceNotFoundException;
import net.guides.springboot.crud.model.Employee;
import net.guides.springboot.crud.model.PayEvent;
import net.guides.springboot.crud.repository.PayEventRepository;
import net.guides.springboot.crud.service.SequenceGeneratorService;


@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v1")
public class PayEventController {
	@Autowired
	private PayEventRepository payEventRepository;
	
	@Autowired
	private SequenceGeneratorService sequenceGeneratorService;

	@GetMapping("/payevents")
	public List<PayEvent> getAllEmployees() {
		return payEventRepository.findAll();
	}
	
	@GetMapping("/payevents/pays")
	public Map<String,Object> getAllpayments() {
		Map<String,Object> payMap = new HashMap<String, Object>();
		
		List<PayEvent> paylist = payEventRepository.findAll();
		
		Double credit = paylist.stream().filter(o -> o.getPayType().equalsIgnoreCase("credit")).mapToDouble(o -> o.getAmount()).sum();
		Double debit = paylist.stream().filter(o -> o.getPayType().equalsIgnoreCase("debit")).mapToDouble(o -> o.getAmount()).sum();
		
		payMap.put("payments", paylist);
		payMap.put("credit", credit);
		payMap.put("debit", debit);
		payMap.put("balance", credit-debit);
		
		return payMap;
	}
	

	@GetMapping("/payevents/{id}")
	public ResponseEntity<PayEvent> getEmployeeById(@PathVariable(value = "id") Long employeeId)
			throws ResourceNotFoundException {
		PayEvent employee = payEventRepository.findById(employeeId)
				.orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + employeeId));
		return ResponseEntity.ok().body(employee);
	}

	@PostMapping("/payevents")
	public PayEvent createEmployee(@Valid @RequestBody PayEvent employee) {
		employee.setId(sequenceGeneratorService.generateSequence(Employee.SEQUENCE_NAME));
		return payEventRepository.save(employee);
	}

	@PutMapping("/payevents/{id}")
	public ResponseEntity<PayEvent> updateEmployee(@PathVariable(value = "id") Long employeeId,
			@Valid @RequestBody PayEvent employeeDetails) throws ResourceNotFoundException {
		PayEvent employee = payEventRepository.findById(employeeId)
				.orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + employeeId));

		employee.setDetails(employeeDetails.getDetails());
		employee.setAmount(employeeDetails.getAmount());
		employee.setEventDate(employeeDetails.getEventDate());
		final PayEvent updatedEmployee = payEventRepository.save(employee);
		return ResponseEntity.ok(updatedEmployee);
	}

	@DeleteMapping("/payevents/{id}")
	public Map<String, Boolean> deleteEmployee(@PathVariable(value = "id") Long employeeId)
			throws ResourceNotFoundException {
		PayEvent employee = payEventRepository.findById(employeeId)
				.orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + employeeId));

		payEventRepository.delete(employee);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}
}
