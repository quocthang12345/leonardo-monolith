package com.leonardo.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "fashion")
@Data
@EqualsAndHashCode(callSuper = true)
public class CommonFashion extends Common {

	@Column
	private String imgDisplay;

	@Column
	private String imgContinue;

	@Column
	private String name;

	@Column
	private String anotherName;

	@Column
	private String type;

	@Column
	private String model;

	@Column
	private String price;

	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "fashion_id")
	private List<TypeColor> typeColor = new ArrayList<>();

	@ElementCollection
	@CollectionTable(name = "fashion_images", joinColumns = @JoinColumn(name = "fashion_id"))
	@Column(name = "image_url")
	private List<String> listImg = new ArrayList<>();
}