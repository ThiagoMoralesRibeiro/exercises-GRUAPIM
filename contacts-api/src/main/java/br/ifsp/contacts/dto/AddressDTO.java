package br.ifsp.contacts.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import br.ifsp.contacts.model.Address;
import br.ifsp.contacts.model.Contact;

public class AddressDTO {
  private String street;
  private String city;
  private String cep;
  private String state;
  @JsonProperty(access = Access.WRITE_ONLY)
  private Long contactId;

  public AddressDTO() {
  }

  public AddressDTO(Address address) {
    this.street = address.getStreet();
    this.city = address.getCity();
    this.cep = address.getCep();
    this.state = address.getState();
    this.contactId = address.getContact() != null ? address.getContact().getId() : null;
  }

  public Address toEntity(Contact contact) {
    return new Address(street, city, cep, state, contact);
  }

  // Getters e Setters
  public String getStreet() {
    return street;
  }

  public void setStreet(String street) {
    this.street = street;
  }

  public String getCity() {
    return city;
  }

  public void setCity(String city) {
    this.city = city;
  }

  public String getState() {
    return state;
  }

  public void setState(String state) {
    this.state = state;
  }

  public String getCep() {
    return cep;
  }

  public void setCep(String cep) {
    this.cep = cep;
  }

  public Long getContactId() {
    return contactId;
  }

  public void setContactId(Long contactId) {
    this.contactId = contactId;
  }
}
