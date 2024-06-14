import React, { useState } from "react"; // Importa React e o hook useState do React
import axios from "axios"; // Importa a biblioteca axios para fazer requisições HTTP

function AddEspecialidade() { // Define o componente funcional AddEspecialidade
  const [nome, setNome] = useState(""); // Define o estado inicial para o campo nome

  const handleSubmit = async (event) => { // Define a função handleSubmit para lidar com o envio do formulário
    event.preventDefault(); // Previne o comportamento padrão do formulário de recarregar a página
    try {
      const response = await axios.post( // Faz uma requisição POST assíncrona para adicionar uma nova especialidade
        "http://localhost:8080/api/especialidade", // URL da API onde a requisição será feita
        { nome } // Corpo da requisição contendo o nome da especialidade
      );
      console.log("Resposta:", response.data); // Exibe a resposta da API no console
      alert("Especialidade adicionada com sucesso!"); // Exibe um alerta de sucesso
      setNome(""); // Reseta o campo nome
    } catch (error) {
      console.error("Houve um erro ao adicionar a especialidade!", error); // Exibe uma mensagem de erro no console
    }
  };

  return ( // Retorna o JSX para renderizar o formulário
    <form onSubmit={handleSubmit}> 
      <h2>Adicionar Especialidade</h2> 
      <div>
        <label>Nome:</label> 
        <input
          type="text" // Define o tipo do campo de entrada como texto
          value={nome} // Associa o valor do campo de entrada ao estado nome
          onChange={(e) => setNome(e.target.value)} // Atualiza o estado nome quando o valor do campo muda
          required // Define o campo como obrigatório
        />
      </div>
      <button type="submit">Adicionar</button> 
    </form>
  );
}

export default AddEspecialidade; // Exporta o componente AddEspecialidade como padrão
