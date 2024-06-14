package br.edu.senaisp.Medico.controller; // Declara o pacote onde esta classe está localizada

import java.util.List; // Importa a classe List para trabalhar com listas
import java.util.Optional; // Importa a classe Optional para representar valores que podem estar presentes ou não

import org.springframework.beans.factory.annotation.Autowired; // Importa a anotação @Autowired para injeção de dependências
import org.springframework.http.ResponseEntity; // Importa a classe ResponseEntity para representar toda a resposta HTTP
import org.springframework.web.bind.annotation.*; // Importa todas as anotações do Spring para mapeamento de requisições web

import br.edu.senaisp.Medico.model.Especialidade; // Importa a classe Especialidade do pacote model
import br.edu.senaisp.Medico.model.Medico; // Importa a classe Medico do pacote model
import br.edu.senaisp.Medico.repository.IEspecialidadeRepository; // Importa a interface IEspecialidadeRepository do pacote repository
import br.edu.senaisp.Medico.repository.IMedicoRepository; // Importa a interface IMedicoRepository do pacote repository

@RestController // Anotação que indica que esta classe é um controlador RESTful
@RequestMapping("/api") // Define o caminho base para todas as requisições neste controlador
public class EspecialidadeController { // Declaração da classe pública EspecialidadeController

    @Autowired // Anotação que indica que o Spring deve injetar a dependência automaticamente
    IEspecialidadeRepository especialidadeRepository; // Declaração da interface IEspecialidadeRepository

    @Autowired // Anotação que indica que o Spring deve injetar a dependência automaticamente
    IMedicoRepository medicoRepository; // Declaração da interface IMedicoRepository

    @GetMapping("/especialidade") // Mapeia requisições HTTP GET para o método listarTodas
    public ResponseEntity<List<Especialidade>> listarTodas() { // Método para listar todas as especialidades
        List<Especialidade> especialidades = especialidadeRepository.findAll(); // Busca todas as especialidades no repositório
        return ResponseEntity.ok(especialidades); // Retorna a lista de especialidades com status 200 OK
    }

    @PostMapping("/especialidade") // Mapeia requisições HTTP POST para o método criarEspecialidade
    public ResponseEntity<Especialidade> criarEspecialidade(@RequestBody Especialidade especialidade) { // Método para criar uma nova especialidade
        Especialidade salva = especialidadeRepository.save(especialidade); // Salva a nova especialidade no repositório
        return ResponseEntity.ok(salva); // Retorna a especialidade salva com status 200 OK
    }

    @GetMapping("/especialidade/{id}") // Mapeia requisições HTTP GET para o método buscaPorId com um parâmetro de caminho (id)
    public ResponseEntity<Especialidade> buscaPorId(@PathVariable int id) { // Método para buscar uma especialidade por ID
        Optional<Especialidade> especialidade = especialidadeRepository.findById(id); // Busca a especialidade pelo ID no repositório
        return especialidade.map(ResponseEntity::ok) // Se a especialidade estiver presente, retorna com status 200 OK
                            .orElseGet(() -> ResponseEntity.notFound().build()); // Se não estiver presente, retorna status 404 Not Found
    }

    @PostMapping("/medico/{medicoId}/especialidade/{especialidadeId}") // Mapeia requisições HTTP POST para o método associarEspecialidade com dois parâmetros de caminho (medicoId e especialidadeId)
    public ResponseEntity<Medico> associarEspecialidade(@PathVariable int medicoId, @PathVariable int especialidadeId) { // Método para associar uma especialidade a um médico
        Optional<Medico> medicoOpt = medicoRepository.findById(medicoId); // Busca o médico pelo ID no repositório
        Optional<Especialidade> especialidadeOpt = especialidadeRepository.findById(especialidadeId); // Busca a especialidade pelo ID no repositório

        if (medicoOpt.isPresent() && especialidadeOpt.isPresent()) { // Se ambos, médico e especialidade, estiverem presentes
            Medico medico = medicoOpt.get(); // Obtém o objeto Medico
            Especialidade especialidade = especialidadeOpt.get(); // Obtém o objeto Especialidade
            especialidade.setMedico(medico); // Define o médico na especialidade
            especialidadeRepository.save(especialidade); // Salva a especialidade atualizada no repositório
            return ResponseEntity.ok(medico); // Retorna o médico associado com status 200 OK
        } else {
            return ResponseEntity.notFound().build(); // Se qualquer um não estiver presente, retorna status 404 Not Found
        }
    }
}
