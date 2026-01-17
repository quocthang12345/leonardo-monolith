package com.leonardo.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.util.List;

@Entity
@Table(name = "menu_men")
@Data
@EqualsAndHashCode(callSuper = true)
public class ListMenuOfMen extends Common {

  @ElementCollection
  @CollectionTable(name = "men_wallets", joinColumns = @JoinColumn(name = "menu_id"))
  @Column
  private List<String> wallet;

  @ElementCollection
  @CollectionTable(name = "men_shoes", joinColumns = @JoinColumn(name = "menu_id"))
  @Column
  private List<String> shoes;

  @ElementCollection
  @CollectionTable(name = "men_bags", joinColumns = @JoinColumn(name = "menu_id"))
  @Column
  private List<String> bag;

  @ElementCollection
  @CollectionTable(name = "men_accessories", joinColumns = @JoinColumn(name = "menu_id"))
  @Column
  private List<String> accessories;
}
