package br.ifsp.contacts.controller;

import br.ifsp.contacts.model.Address;
import br.ifsp.contacts.repository.AddressRepository;

import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
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
public class AddressController {

  /**
   * @Autowired permite que o Spring "injete" automaticamente
   *            uma instância de AddressRepository aqui,
   *            sem que precisemos criar manualmente.
   */
  @Autowired
  private AddressRepository addressRepository;

  /**
   * Método para obter todos os contatos.
   * 
   * @GetMapping indica que este método vai responder a chamadas HTTP GET.
   *             Exemplo de acesso: GET /api/addresss
   */
  @GetMapping
  public List<Address> getAllAddresss() {
    return addressRepository.findAll();
  }

  /**
   * Método para obter um contato específico pelo seu ID.
   * 
   * @PathVariable "amarra" a variável {id} da URL
   *               ao parâmetro do método.
   *               Exemplo de acesso: GET /api/addresss/1
   */
  @GetMapping("/{id}")
  public Address getAddressById(@PathVariable Long id) {
    // findById retorna um Optional, então usamos orElseThrow
    return addressRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Endereço não encontrado: " + id));
  }

  /*@GetMapping("/search")
  public List<Address> getAddressByName(@RequestParam(required = false) String name) {
    if (name == null || name.isBlank()) {
      return addressRepository.findAll();
    }
    return addressRepository.getAddressByName(name);
  }*/

  /**
   * Método para criar um novo contato.
   * 
   * @PostMapping indica que este método responde a chamadas HTTP POST.
   * @RequestBody indica que o objeto Address será preenchido
   *              com os dados JSON enviados no corpo da requisição.
   */
  @PostMapping
  public Address createAddress(@RequestBody Address address) {
    return addressRepository.save(address);
  }

  /**
   * Método para atualizar um contato existente.
   * 
   * @PutMapping indica que este método responde a chamadas HTTP PUT.
   *             Exemplo de acesso: PUT /api/addresss/1
   */
  @PutMapping("/{id}")
  public Address updateAddress(@PathVariable Long id, @RequestBody Address updatedAddress) {
    // Buscar o contato existente
    Address existingAddress = addressRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Endereço não encontrado: " + id));

    // Atualizar os campos
    existingAddress.setStreet(updatedAddress.getStreet());
    existingAddress.setState(updatedAddress.getState());
    existingAddress.setCity(updatedAddress.getCity());
    existingAddress.setCep(updatedAddress.getCep());

    // Salvar alterações
    return addressRepository.save(existingAddress);
  }

  @PatchMapping("/{id}")
  public Address partialUpdateAddress(@PathVariable Long id, @RequestBody Map<String, Object> partialUpdates) {
    Address existingAddress = addressRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Contato não encontrado: " + id));

    partialUpdates.forEach((key, value) -> {
      Field field = ReflectionUtils.findField(Address.class, key);
      if (field != null) {
        field.setAccessible(true);
        ReflectionUtils.setField(field, existingAddress, value);
      }
    });

    return addressRepository.save(existingAddress);
  }

  /**
   * Método para excluir um contato pelo ID.
   * 
   * @DeleteMapping indica que este método responde a chamadas HTTP DELETE.
   *                Exemplo de acesso: DELETE /api/addresss/1
   */
  @DeleteMapping("/{id}")
  public void deleteAddress(@PathVariable Long id) {
    addressRepository.deleteById(id);
  }
}
