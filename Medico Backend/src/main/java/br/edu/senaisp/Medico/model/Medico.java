package br.edu.senaisp.Medico.model; // Declara o pacote onde esta classe está localizada

import jakarta.persistence.*; // Importa todas as anotações e classes de persistência do Jakarta
import jakarta.validation.constraints.NotNull; // Importa a anotação @NotNull para validação de campos
import jakarta.validation.constraints.Size; // Importa a anotação @Size para validação de tamanho de campos
import java.util.List; // Importa a classe List para trabalhar com listas

@Entity // Anotação que indica que esta classe é uma entidade JPA
@Table(name = "medico") // Define a tabela correspondente no banco de dados
public class Medico { // Declaração da classe pública Medico

    @Id // Anotação que indica que este campo é a chave primária
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Estratégia de geração automática de valores para o campo id
    private Integer id; // Declaração do campo id como Integer
    
    @NotNull // Anotação que indica que este campo não pode ser nulo
    @Size(min = 2, max = 120) // Define o tamanho mínimo e máximo para o campo nome
    private String nome; // Declaração do campo nome como String
    
    private String crm; // Declaração do campo crm como String
    
    @OneToMany(mappedBy = "medico", cascade = CascadeType.ALL) // Define um relacionamento um-para-muitos com a entidade Especialidade
    private List<Especialidade> especialidades; // Declaração do campo especialidades como uma lista de Especialidade
    
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

    public String getCrm() { // Getter para o campo crm
        return crm;
    }

    public void setCrm(String crm) { // Setter para o campo crm
        this.crm = crm;
    }

    public List<Especialidade> getEspecialidades() { // Getter para o campo especialidades
        return especialidades;
    }

    public void setEspecialidades(List<Especialidade> especialidades) { // Setter para o campo especialidades
        this.especialidades = especialidades;
    }
}
