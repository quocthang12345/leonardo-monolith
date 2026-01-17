package com.leonardo.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.util.List;

@Entity
@Table(name = "menu_women")
@Data
@EqualsAndHashCode(callSuper = true)
public class ListMenuOfWomen extends Common {

  @ElementCollection
  @CollectionTable(name = "women_shirts", joinColumns = @JoinColumn(name = "menu_id"))
  @Column
  private List<String> shirts;

  @ElementCollection
  @CollectionTable(name = "women_trousers", joinColumns = @JoinColumn(name = "menu_id"))
  @Column
  private List<String> trousers;

  @ElementCollection
  @CollectionTable(name = "women_bags", joinColumns = @JoinColumn(name = "menu_id"))
  @Column
  private List<String> bag;

  @ElementCollection
  @CollectionTable(name = "women_accessories", joinColumns = @JoinColumn(name = "menu_id"))
  @Column
  private List<String> accessories;
}
