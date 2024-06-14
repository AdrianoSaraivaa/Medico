package br.edu.senaisp.Medico.repository; // Declara o pacote onde esta interface está localizada

import org.springframework.data.jpa.repository.JpaRepository; // Importa a interface JpaRepository do Spring Data JPA
import org.springframework.stereotype.Repository; // Importa a anotação Repository do Spring
import br.edu.senaisp.Medico.model.Medico; // Importa a classe Medico do pacote model

@Repository // Anotação que indica que esta interface é um repositório Spring
public interface IMedicoRepository extends JpaRepository<Medico, Integer> { // Declaração da interface IMedicoRepository que estende JpaRepository
    // JpaRepository fornece métodos CRUD e outras operações de banco de dados para a entidade Medico com chave primária do tipo Integer
}
