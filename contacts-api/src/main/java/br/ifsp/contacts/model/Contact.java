package br.ifsp.contacts.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

//import org.antlr.v4.runtime.misc.NotNull;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
public class Contact {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @NotNull(message = "Name cannot be null")
  @NotBlank(message = "Name is required")
  private String name;
  @NotBlank(message = "Phone number is required")
  @Size(min = 8, max = 15, message = "Phone number must be between 8 and 15 characters")
  private String phone;
  @Email(message = "Email should be valid")
  private String email;
  @OneToMany(mappedBy = "contact", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
  @JsonManagedReference
  @NotEmpty(message = "O contato deve ter pelo menos um endereço")
  private List<Address> addresses;

  public Contact() {
  }

  public Contact(String name, String phone, String email) {
    this.name = name;
    this.phone = phone;
    this.email = email;
    this.addresses = new ArrayList<>();
  }

  // Getters e Setters
  public Long getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public void setName(String nome) {
    this.name = nome;
  }

  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public List<Address> getAddresses() {
    return addresses;
  }

  public void setAddresses(List<Address> addresses) {
    if (addresses != null) {
      addresses.forEach(address -> address.setContact(this));

      if (this.addresses == null) {
        this.addresses = new ArrayList<>();
      }

      this.addresses.clear();
      this.addresses.addAll(addresses);
    }
  }

}
