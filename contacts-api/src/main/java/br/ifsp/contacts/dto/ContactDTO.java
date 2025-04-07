package br.ifsp.contacts.dto;

import java.util.List;
import java.util.stream.Collectors;

import br.ifsp.contacts.model.Address;
import br.ifsp.contacts.model.Contact;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class ContactDTO {
  private Long id;
  @NotBlank(message = "Name is required")
  @NotNull(message = "Name cannot be null")
  private String name;
  @NotBlank(message = "Phone is required")
  @Size(min = 8, max = 15, message = "Phone number must be between 8 and 15 characters")
  private String phone;
  @Email(message = "Email should be valid")
  private String email;
  @NotEmpty(message = "At least one address is required")
  private List<AddressDTO> addresses;

  public ContactDTO() {

  }

  public ContactDTO(Contact contact) {
    this.id = contact.getId();
    this.name = contact.getName();
    this.phone = contact.getPhone();
    this.email = contact.getEmail();
    this.addresses = contact.getAddresses().stream().map(AddressDTO::new).collect(Collectors.toList());
  }

  public Contact setToObject() {
    Contact contact = new Contact(name, phone, email);

    if (addresses != null) {
      List<Address> addressEntities = addresses.stream()
          .map(dto -> dto.toEntity(contact))
          .collect(Collectors.toList());
      contact.setAddresses(addressEntities);
    }

    return contact;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
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

  public List<AddressDTO> getAddresses() {
    return addresses;
  }

  public void setAddresses(List<AddressDTO> addresses) {
    this.addresses = addresses;
  }
}
