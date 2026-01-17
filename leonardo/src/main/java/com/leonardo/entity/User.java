package com.leonardo.entity;

import com.leonardo.entity.resource.AuthProvider;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class User extends Common {

  @NotNull
  @Column(name = "username", nullable = false, unique = true)
  private String username;

  @Column(name = "fullname")
  private String fullname;

  @NotNull
  @Column(name = "email", nullable = false, unique = true)
  private String email;

  @NotNull
  @Column(name = "password", nullable = false)
  private String password;

  @Column(name = "phone_number")
  private String phoneNumber;

  @Column(name = "image_url")
  private String imageUrl;

  @Column(name = "address")
  private String address;

  @Column(name = "city")
  private String city;

  @Column(name = "status")
  private int status;

  @ManyToMany(fetch = FetchType.LAZY)
  @JoinTable(
      name = "user_roles",
      joinColumns = @JoinColumn(name = "user_id"),
      inverseJoinColumns = @JoinColumn(name = "role_id"))
  private Set<Role> roles = new HashSet<>();

  @Column(name = "email_verified")
  private Boolean emailVerified = false;

  @Column(name = "verify_code")
  private String verifyCode;

  @NotNull
  @Enumerated(EnumType.STRING)
  @Column(name = "provider", nullable = false)
  private AuthProvider provider;

  @Column(name = "provider_id")
  private String providerId;
}
