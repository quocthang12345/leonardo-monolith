package com.leonardo.entity;

import jakarta.persistence.*;
import lombok.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "roles")
@Data
@EqualsAndHashCode(callSuper = true, exclude = "users")
@ToString(exclude = "users")
@NoArgsConstructor
@AllArgsConstructor
public class Role extends Common {

  @Column(nullable = false, unique = true, length = 50)
  private String roleName;

  @Column(name = "description")
  private String description;

  @Column(name = "isEnabled")
  private boolean enabled = true;

  @ManyToMany(mappedBy = "roles")
  private Set<User> users = new HashSet<>();
}
