package com.vz.ItemServiceReactive.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity

public class ItemService {
	@Id
	private Integer id;
	private String itemName;
	private String details;
	private String price;
	

}
