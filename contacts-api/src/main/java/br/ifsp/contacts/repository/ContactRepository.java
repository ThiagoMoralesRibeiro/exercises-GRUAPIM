package br.ifsp.contacts.repository;

import java.util.List;
import java.util.ArrayList;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.ifsp.contacts.dto.ContactDTO;
import br.ifsp.contacts.model.Address;
import br.ifsp.contacts.model.Contact;

/**
 * Esta interface extende JpaRepository, que nos fornece métodos prontos
 * para acessar e manipular dados no banco de dados. Basta especificar
 * a classe de entidade (Contact) e o tipo da chave primária (Long).
 */
public interface ContactRepository extends JpaRepository<Contact, Long> {
  // Podemos adicionar métodos personalizados se necessário.
  @Query("SELECT c FROM Contact c WHERE LOWER(c.name) LIKE LOWER(CONCAT('%', :name, '%'))")
  Page<Contact> getContactByName(@Param("name") String name, Pageable pageable);

  @Query("SELECT c.addresses FROM Contact c WHERE c.id = :id")
  List<Address> findAddressesByContactId(@Param("id") Long id);

  Page<Contact> findAll(Pageable pageable);

}
