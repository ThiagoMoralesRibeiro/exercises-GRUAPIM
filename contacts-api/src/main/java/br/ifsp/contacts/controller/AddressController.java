package br.ifsp.contacts.controller;

import br.ifsp.contacts.dto.AddressDTO;
import br.ifsp.contacts.model.Address;
import br.ifsp.contacts.model.Contact;
import br.ifsp.contacts.repository.AddressRepository;
import br.ifsp.contacts.repository.ContactRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.media.Content;

import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.PageRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.util.ReflectionUtils;
import java.lang.reflect.Field;

import java.util.List;

/**
 * Classe responsável por mapear as rotas/endpoints relacionados
 * aos contatos. Cada método abaixo corresponde a uma operação
 * em nosso sistema.
 * 
 * @RestController: Indica que esta classe é um controlador
 *                  responsável por responder requisições REST.
 *                  @RequestMapping("/api/addresss"): Indica o caminho base.
 */
@RestController
@RequestMapping("/api/address")
@Tag(name = "Address", description = "Endpoints para gerenciamento dos endereços")
public class AddressController {

  /**
   * @Autowired permite que o Spring "injete" automaticamente
   *            uma instância de AddressRepository aqui,
   *            sem que precisemos criar manualmente.
   */
  @Autowired
  private AddressRepository addressRepository;

  @Autowired
  private ContactRepository contactRepository;

  @Operation(summary = "Buscar todos os endereços com paginação", description = "Retorna uma lista paginada de endereços ordenados por um campo específico")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Endereços retornados com sucesso"),
      @ApiResponse(responseCode = "400", description = "Parâmetros inválidos", content = @Content),
      @ApiResponse(responseCode = "500", description = "Erro interno", content = @Content)
  })

  /**
   * Método para obter todos os contatos.
   * 
   * @GetMapping indica que este método vai responder a chamadas HTTP GET.
   *             Exemplo de acesso: GET /api/addresss
   */
  @GetMapping
  public Page<AddressDTO> getAllAddresss(@RequestParam(defaultValue = "0") int page,
      @RequestParam(defaultValue = "10") int size, @RequestParam(defaultValue = "state") String sortBy) {
    Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy).ascending());
    Page<Address> addressPage = addressRepository.findAll(pageable);
    return addressPage.map(AddressDTO::new);
  }

  /**
   * Método para obter um contato específico pelo seu ID.
   * 
   * @PathVariable "amarra" a variável {id} da URL
   *               ao parâmetro do método.
   *               Exemplo de acesso: GET /api/addresss/1
   */
  @Operation(summary = "Buscar por um endereco especifico", description = "Retorna o endereco buscado")

  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Endereco retornado com sucesso"),
      @ApiResponse(responseCode = "400", description = "Parâmetros inválidos", content = @Content),
      @ApiResponse(responseCode = "500", description = "Erro interno", content = @Content)
  })

  @GetMapping("/{id}")
  public ResponseEntity<AddressDTO> getAddressById(@PathVariable Long id) {
    // findById retorna um Optional, então usamos orElseThrow
    Address address = addressRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Endereço não encontrado: " + id));
    AddressDTO responseDto = new AddressDTO(address);
    return ResponseEntity.status(HttpStatus.FOUND).body(responseDto);
  }

  /*
   * @GetMapping("/search")
   * public List<Address> getAddressByName(@RequestParam(required = false) String
   * name) {
   * if (name == null || name.isBlank()) {
   * return addressRepository.findAll();
   * }
   * return addressRepository.getAddressByName(name);
   * }
   */

  /**
   * Método para criar um novo contato.
   * 
   * @PostMapping indica que este método responde a chamadas HTTP POST.
   * @RequestBody indica que o objeto Address será preenchido
   *              com os dados JSON enviados no corpo da requisição.
   */

  @Operation(summary = "Cria um novo endereco", description = "Retorna o endereco criado")

  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Endereco criado com sucesso"),
      @ApiResponse(responseCode = "400", description = "Parâmetros inválidos", content = @Content),
      @ApiResponse(responseCode = "500", description = "Erro interno", content = @Content)
  })

  @PostMapping
  public ResponseEntity<?> createAddress(@RequestBody AddressDTO dto) {
    if (dto.getContactId() == null) {
      return ResponseEntity.badRequest().body("Contact ID is required");
    }

    Contact contact = contactRepository.findById(dto.getContactId())
        .orElseThrow(() -> new RuntimeException("Contact not found"));

    Address address = dto.toEntity(contact);
    AddressDTO responseDto = new AddressDTO(addressRepository.save(address));

    return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
  }

  /**
   * Método para atualizar um contato existente.
   * 
   * @PutMapping indica que este método responde a chamadas HTTP PUT.
   *             Exemplo de acesso: PUT /api/addresss/1
   */

  @Operation(summary = "Altera os dados de um endereco especifico", description = "Retorna o endereco atualizado")

  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Endereco alterado com sucesso"),
      @ApiResponse(responseCode = "400", description = "Parâmetros inválidos", content = @Content),
      @ApiResponse(responseCode = "500", description = "Erro interno", content = @Content)
  })

  @PutMapping("/{id}")
  public ResponseEntity<AddressDTO> updateAddress(@PathVariable Long id, @RequestBody AddressDTO dto) {
    // Buscar o endereço existente
    Address existingAddress = addressRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Endereço não encontrado: " + id));

    // Consequentemente, aqui eu atualizo o contato
    if (dto.getContactId() != null) {
      Contact contact = contactRepository.findById(dto.getContactId())
          .orElseThrow(() -> new RuntimeException("Contato não encontrado: " + dto.getContactId()));
      existingAddress.setContact(contact);
    }

    // Atualizar os campos do endereço
    existingAddress.setStreet(dto.getStreet());
    existingAddress.setState(dto.getState());
    existingAddress.setCity(dto.getCity());
    existingAddress.setCep(dto.getCep());

    // Salvar alterações
    Address savedAddress = addressRepository.save(existingAddress);
    AddressDTO responseDto = new AddressDTO(savedAddress);

    return ResponseEntity.ok(responseDto);
  }

  @Operation(summary = "Altera alguns parametros de um endereco especifico", description = "Retorna o endereco atualizado")

  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Dados alterados com sucesso"),
      @ApiResponse(responseCode = "400", description = "Parâmetros inválidos", content = @Content),
      @ApiResponse(responseCode = "500", description = "Erro interno", content = @Content)
  })

  @PatchMapping("/{id}")
  public ResponseEntity<AddressDTO> partialUpdateContact(@PathVariable Long id,
      @RequestBody Map<String, Object> updates) {
    Address existingAddress = addressRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Endereco não encontrado: " + id));

    updates.forEach((key, value) -> {
      Field field = ReflectionUtils.findField(Address.class, key);
      if (field != null) {
        field.setAccessible(true);
        ReflectionUtils.setField(field, existingAddress, value);
      }
    });

    Address updated = addressRepository.save(existingAddress);
    return ResponseEntity.ok(new AddressDTO(updated));
  }

  /**
   * Método para excluir um contato pelo ID.
   * 
   * @DeleteMapping indica que este método responde a chamadas HTTP DELETE.
   *                Exemplo de acesso: DELETE /api/addresss/1
   */
  @Operation(summary = "Deleta um contato especifico", description = "Retorna a info de delecao")

  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Contato deletado com sucesso"),
      @ApiResponse(responseCode = "400", description = "Parâmetros inválidos", content = @Content),
      @ApiResponse(responseCode = "500", description = "Erro interno", content = @Content)
  })

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteContact(@PathVariable Long id) {
    if (!addressRepository.existsById(id)) {
      return ResponseEntity.notFound().build();
    }

    addressRepository.deleteById(id);
    return ResponseEntity.noContent().build();
  }

}
