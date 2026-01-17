package com.leonardo.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "menus")
@Data
@EqualsAndHashCode(callSuper = true)
public class Menu extends Common {

  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "men_menu_id", referencedColumnName = "id")
  private ListMenuOfMen listMenuOfMen;

  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "women_menu_id", referencedColumnName = "id")
  private ListMenuOfWomen listMenuOfWoMen;
}
