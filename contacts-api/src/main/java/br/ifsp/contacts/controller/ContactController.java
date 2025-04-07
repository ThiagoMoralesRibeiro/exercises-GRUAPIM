package br.ifsp.contacts.controller;

import br.ifsp.contacts.dto.AddressDTO;
import br.ifsp.contacts.dto.ContactDTO;
import br.ifsp.contacts.model.Address;
import br.ifsp.contacts.model.Contact;
import br.ifsp.contacts.repository.ContactRepository;
import jakarta.validation.Valid;

import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.util.ReflectionUtils;
import java.lang.reflect.Field;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;

/**
 * Classe responsável por mapear as rotas/endpoints relacionados
 * aos contatos. Cada método abaixo corresponde a uma operação
 * em nosso sistema.
 * 
 * @RestController: Indica que esta classe é um controlador
 *                  responsável por responder requisições REST.
 *                  @RequestMapping("/api/contacts"): Indica o caminho base.
 */
@RestController
@RequestMapping("/api/contacts")
@Tag(name = "Contacts", description = "Endpoints para gerenciamento de contatos")
public class ContactController {

  /**
   * @Autowired permite que o Spring "injete" automaticamente
   *            uma instância de ContactRepository aqui,
   *            sem que precisemos criar manualmente.
   */
  @Autowired
  private ContactRepository contactRepository;

  @Operation(summary = "Buscar todos os contatos com paginação", description = "Retorna uma lista paginada de contatos ordenados por um campo específico")

  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Contatos retornados com sucesso"),
      @ApiResponse(responseCode = "400", description = "Parâmetros inválidos", content = @Content),
      @ApiResponse(responseCode = "500", description = "Erro interno", content = @Content)
  })

  /**
   * Método para obter todos os contatos.
   * 
   * @GetMapping indica que este método vai responder a chamadas HTTP GET.
   *             Exemplo de acesso: GET /api/contacts
   */

  @GetMapping
  public Page<ContactDTO> getAllContacts(@RequestParam(defaultValue = "0") int page,
      @RequestParam(defaultValue = "10") int size, @RequestParam(defaultValue = "name") String sortBy) {
    Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy).ascending());
    Page<Contact> contactPage = contactRepository.findAll(pageable);
    return contactPage.map(ContactDTO::new);
  }

  /**
   * Método para obter um contato específico pelo seu ID.
   * 
   * @PathVariable "amarra" a variável {id} da URL
   *               ao parâmetro do método.
   *               Exemplo de acesso: GET /api/contacts/1
   */

  @Operation(summary = "Buscar por um contato especifico", description = "Retorna o contato buscado")

  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Contato retornado com sucesso"),
      @ApiResponse(responseCode = "400", description = "Parâmetros inválidos", content = @Content),
      @ApiResponse(responseCode = "500", description = "Erro interno", content = @Content)
  })

  @GetMapping("/{id}")
  public ResponseEntity<ContactDTO> getContactById(@PathVariable Long id) {
    // findById retorna um Optional, então usamos orElseThrow
    Contact contact = contactRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Contato não encontrado: " + id));
    ContactDTO responseDto = new ContactDTO(contact);
    return ResponseEntity.status(HttpStatus.OK).body(responseDto);

  }

  @Operation(summary = "Buscar por um contato especifico atraves do nome", description = "Retorna o contato buscado")

  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Contato retornado com sucesso"),
      @ApiResponse(responseCode = "400", description = "Parâmetros inválidos", content = @Content),
      @ApiResponse(responseCode = "500", description = "Erro interno", content = @Content)
  })

  @GetMapping("/search")
  public Page<ContactDTO> getContactByName(@RequestParam(required = false) String name,
      @RequestParam(defaultValue = "0") int page,
      @RequestParam(defaultValue = "10") int size,
      @RequestParam(defaultValue = "name") String sortBy) {

    Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
    Page<Contact> contacts;

    if (name == null || name.isBlank()) {
      contacts = contactRepository.findAll(pageable);
    } else {
      contacts = contactRepository.getContactByName(name, pageable);
    }

    return contacts.map(ContactDTO::new);
  }

  @Operation(summary = "Busca contatos atraves do endereco", description = "Retorna o contato buscado")

  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Endereco retornado com sucesso"),
      @ApiResponse(responseCode = "400", description = "Parâmetros inválidos", content = @Content),
      @ApiResponse(responseCode = "500", description = "Erro interno", content = @Content)
  })

  @GetMapping("{id}/address")
  public List<AddressDTO> getContactWithAddress(@PathVariable Long id) {
    List<Address> addresses = contactRepository.findAddressesByContactId(id);

    return addresses.stream().map(AddressDTO::new).toList();
  }

  /**
   * Método para criar um novo contato.
   * 
   * @PostMapping indica que este método responde a chamadas HTTP POST.
   * @RequestBody indica que o objeto Contact será preenchido
   *              com os dados JSON enviados no corpo da requisição.
   */
  @Operation(summary = "Cria um novo contato", description = "Retorna o contato criado")

  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Contato criado com sucesso"),
      @ApiResponse(responseCode = "400", description = "Parâmetros inválidos", content = @Content),
      @ApiResponse(responseCode = "500", description = "Erro interno", content = @Content)
  })

  @PostMapping
  public ResponseEntity<ContactDTO> createContact(@Valid @RequestBody ContactDTO dto) {
    // System.out.println("DTO recebido: name=" + dto.getName() + ", phone=" +
    // dto.getPhone());
    Contact contact = dto.setToObject();
    ContactDTO responseDto = new ContactDTO(contactRepository.save(contact));
    return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
  }

  /**
   * Método para atualizar um contato existente.
   * 
   * @PutMapping indica que este método responde a chamadas HTTP PUT.
   *             Exemplo de acesso: PUT /api/contacts/1
   */

  @Operation(summary = "Altera os dados de um contato especifico", description = "Retorna o contato atualizado")

  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Contato alterado com sucesso"),
      @ApiResponse(responseCode = "400", description = "Parâmetros inválidos", content = @Content),
      @ApiResponse(responseCode = "500", description = "Erro interno", content = @Content)
  })

  @PutMapping("/{id}")
  public ResponseEntity<ContactDTO> updateContact(@PathVariable Long id, @RequestBody ContactDTO dto) {
    // Buscar o contato existente
    Contact contact = dto.setToObject();
    Contact existingContact = contactRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Contato não encontrado: " + id));

    // Atualizar os campos
    existingContact.setName(contact.getName());
    existingContact.setPhone(contact.getPhone());
    existingContact.setEmail(contact.getEmail());

    // Salvar alterações
    ContactDTO responseDto = new ContactDTO(contactRepository.save(contact));
    return ResponseEntity.status(HttpStatus.OK).body(responseDto);
  }

  @Operation(summary = "Altera alguns parametros de um contato especifico", description = "Retorna o contato atualizado")

  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Dados alterados com sucesso"),
      @ApiResponse(responseCode = "400", description = "Parâmetros inválidos", content = @Content),
      @ApiResponse(responseCode = "500", description = "Erro interno", content = @Content)
  })

  @PatchMapping("/{id}")
  public ResponseEntity<ContactDTO> partialUpdateContact(@PathVariable Long id,
      @RequestBody Map<String, Object> updates) {
    Contact existingContact = contactRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Contato não encontrado: " + id));

    updates.forEach((key, value) -> {
      Field field = ReflectionUtils.findField(Contact.class, key);
      if (field != null) {
        field.setAccessible(true);
        ReflectionUtils.setField(field, existingContact, value);
      }
    });

    Contact updated = contactRepository.save(existingContact);
    return ResponseEntity.ok(new ContactDTO(updated));
  }

  /**
   * Método para excluir um contato pelo ID.
   * 
   * @DeleteMapping indica que este método responde a chamadas HTTP DELETE.
   *                Exemplo de acesso: DELETE /api/contacts/1
   */

  @Operation(summary = "Deleta um contato especifico", description = "Retorna a info de delecao")

  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Contato deletado com sucesso"),
      @ApiResponse(responseCode = "400", description = "Parâmetros inválidos", content = @Content),
      @ApiResponse(responseCode = "500", description = "Erro interno", content = @Content)
  })


  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteContact(@PathVariable Long id) {
    if (!contactRepository.existsById(id)) {
      return ResponseEntity.notFound().build();
    }

    contactRepository.deleteById(id);
    return ResponseEntity.noContent().build();
  }

}
