package br.edu.senaisp.Medico.controller; // Declara o pacote onde esta classe está localizada

import java.util.List; // Importa a classe List para trabalhar com listas
import java.util.Optional; // Importa a classe Optional para representar valores que podem estar presentes ou não

import org.springframework.beans.factory.annotation.Autowired; // Importa a anotação @Autowired para injeção de dependências
import org.springframework.http.ResponseEntity; // Importa a classe ResponseEntity para representar toda a resposta HTTP
import org.springframework.web.bind.annotation.*; // Importa todas as anotações do Spring para mapeamento de requisições web

import br.edu.senaisp.Medico.model.Medico; // Importa a classe Medico do pacote model
import br.edu.senaisp.Medico.repository.IMedicoRepository; // Importa a interface IMedicoRepository do pacote repository
import jakarta.validation.Valid; // Importa a anotação @Valid para validação de beans

@RestController // Anotação que indica que esta classe é um controlador RESTful
@RequestMapping("/api") // Define o caminho base para todas as requisições neste controlador
public class MedicoController { // Declaração da classe pública MedicoController

    @Autowired // Anotação que indica que o Spring deve injetar a dependência automaticamente
    IMedicoRepository repositorio; // Declaração da interface IMedicoRepository

    @GetMapping("/medico") // Mapeia requisições HTTP GET para o método listartudo
    public ResponseEntity<List<Medico>> listartudo() { // Método para listar todos os médicos
        List<Medico> medicos = repositorio.findAll(); // Busca todos os médicos no repositório
        return ResponseEntity.ok(medicos); // Retorna a lista de médicos com status 200 OK
    }

    @PostMapping("/medico") // Mapeia requisições HTTP POST para o método criarMedicoAPI
    public ResponseEntity<Medico> criarMedicoAPI(@RequestBody @Valid Medico medico) { // Método para criar um novo médico
        Medico salvo = repositorio.save(medico); // Salva o novo médico no repositório
        return ResponseEntity.ok(salvo); // Retorna o médico salvo com status 200 OK
    }

    @GetMapping("/medico/{id}") // Mapeia requisições HTTP GET para o método buscaPorId com um parâmetro de caminho (id)
    public ResponseEntity<Medico> buscaPorId(@PathVariable int id) { // Método para buscar um médico por ID
        Optional<Medico> medico = repositorio.findById(id); // Busca o médico pelo ID no repositório
        return medico.map(ResponseEntity::ok) // Se o médico estiver presente, retorna com status 200 OK
                     .orElseGet(() -> ResponseEntity.notFound().build()); // Se não estiver presente, retorna status 404 Not Found
    }

    @PutMapping("/medico/{id}") // Mapeia requisições HTTP PUT para o método altera com um parâmetro de caminho (id)
    public ResponseEntity<Medico> altera(@RequestBody Medico medico, @PathVariable int id) { // Método para alterar um médico existente
        Optional<Medico> medicoExistente = repositorio.findById(id); // Busca o médico existente pelo ID no repositório
        if (medicoExistente.isPresent()) { // Se o médico estiver presente
            Medico updatedMedico = medicoExistente.get(); // Obtém o objeto Medico existente
            updatedMedico.setNome(medico.getNome()); // Atualiza o nome do médico
            updatedMedico.setCrm(medico.getCrm()); // Atualiza o CRM do médico
            repositorio.save(updatedMedico); // Salva o médico atualizado no repositório
            return ResponseEntity.ok(updatedMedico); // Retorna o médico atualizado com status 200 OK
        } else {
            return ResponseEntity.notFound().build(); // Se o médico não estiver presente, retorna status 404 Not Found
        }
    }

    @DeleteMapping("/medico/{id}") // Mapeia requisições HTTP DELETE para o método deletar com um parâmetro de caminho (id)
    public ResponseEntity<Medico> deletar(@PathVariable int id) { // Método para deletar um médico por ID
        if (repositorio.existsById(id)) { // Verifica se o médico existe pelo ID no repositório
            repositorio.deleteById(id); // Deleta o médico pelo ID
            return ResponseEntity.noContent().build(); // Retorna status 204 No Content
        } else {
            return ResponseEntity.notFound().build(); // Se o médico não estiver presente, retorna status 404 Not Found
        }
    }
}
