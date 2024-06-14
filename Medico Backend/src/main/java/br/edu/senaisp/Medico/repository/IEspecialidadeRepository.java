package br.edu.senaisp.Medico.repository; // Declara o pacote onde esta interface está localizada

import org.springframework.data.jpa.repository.JpaRepository; // Importa a interface JpaRepository do Spring Data JPA
import br.edu.senaisp.Medico.model.Especialidade; // Importa a classe Especialidade do pacote model

public interface IEspecialidadeRepository extends JpaRepository<Especialidade, Integer> { // Declaração da interface IEspecialidadeRepository que estende JpaRepository
    // JpaRepository fornece métodos CRUD e outras operações de banco de dados para a entidade Especialidade com chave primária do tipo Integer
}
