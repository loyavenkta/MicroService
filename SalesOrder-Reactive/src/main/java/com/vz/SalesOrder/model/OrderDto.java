package com.vz.SalesOrder.model;

import java.time.LocalDate;
import java.util.List;

import lombok.Data;

@Data
public class OrderDto {
	private String description;
	private LocalDate orderDate;
	private String emailId;
	private List<String> itemNames;

}
