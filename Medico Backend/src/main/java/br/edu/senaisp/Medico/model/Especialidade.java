package br.edu.senaisp.Medico.model; // Declara o pacote onde esta classe está localizada

import com.fasterxml.jackson.annotation.JsonIgnore; // Importa a anotação JsonIgnore para ignorar propriedades na serialização JSON
import jakarta.persistence.*; // Importa todas as anotações e classes de persistência do Jakarta

@Entity // Anotação que indica que esta classe é uma entidade JPA
@Table(name = "especialidade") // Define a tabela correspondente no banco de dados
public class Especialidade { // Declaração da classe pública Especialidade
    
    @Id // Anotação que indica que este campo é a chave primária
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Estratégia de geração automática de valores para o campo id
    private Integer id; // Declaração do campo id como Integer
    private String nome; // Declaração do campo nome como String
    
    @ManyToOne // Anotação que indica um relacionamento muitos-para-um com a entidade Medico
    @JoinColumn(name = "medico_id") // Define a coluna de junção no banco de dados
    @JsonIgnore // Anotação que indica que esta propriedade deve ser ignorada na serialização JSON
    private Medico medico; // Declaração do campo medico como um objeto Medico
    
    // Getters e Setters

    public Integer getId() { // Getter para o campo id
        return id;
    }

    public void setId(Integer id) { // Setter para o campo id
        this.id = id;
    }

    public String getNome() { // Getter para o campo nome
        return nome;
    }

    public void setNome(String nome) { // Setter para o campo nome
        this.nome = nome;
    }

    public Medico getMedico() { // Getter para o campo medico
        return medico;
    }

    public void setMedico(Medico medico) { // Setter para o campo medico
        this.medico = medico;
    }
}
