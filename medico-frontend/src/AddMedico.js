import React, { useState } from "react"; // Importa React e o hook useState do React
import axios from "axios"; // Importa a biblioteca axios para fazer requisições HTTP

function AddMedico() { // Define o componente funcional AddMedico
  const [nome, setNome] = useState(""); // Define o estado inicial para o campo nome
  const [crm, setCrm] = useState(""); // Define o estado inicial para o campo CRM

  const handleSubmit = async (event) => { // Define a função handleSubmit para lidar com o envio do formulário
    event.preventDefault(); // Previne o comportamento padrão do formulário de recarregar a página
    try {
      const response = await axios.post("http://localhost:8080/api/medico", { // Faz uma requisição POST assíncrona para adicionar um novo médico
        nome, // Nome do médico a ser enviado no corpo da requisição
        crm, // CRM do médico a ser enviado no corpo da requisição
      });
      console.log("Resposta:", response.data); // Exibe a resposta da API no console
      alert("Médico adicionado com sucesso!"); // Exibe um alerta de sucesso
      setNome(""); // Reseta o campo nome
      setCrm(""); // Reseta o campo CRM
    } catch (error) {
      console.error("Houve um erro ao adicionar o médico!", error); // Exibe uma mensagem de erro no console
    }
  };

  return ( // Retorna o JSX para renderizar o formulário
    <form onSubmit={handleSubmit}> 
      <h2>Adicionar Médico</h2> 
      <div>
        <label>Nome:</label> 
        <input
          type="text" // Define o tipo do campo de entrada como texto
          value={nome} // Associa o valor do campo de entrada ao estado nome
          onChange={(e) => setNome(e.target.value)} // Atualiza o estado nome quando o valor do campo muda
          required // Define o campo como obrigatório
        />
      </div>
      <div>
        <label>CRM:</label> 
        <input
          type="text" // Define o tipo do campo de entrada como texto
          value={crm} // Associa o valor do campo de entrada ao estado CRM
          onChange={(e) => setCrm(e.target.value)} // Atualiza o estado CRM quando o valor do campo muda
          required // Define o campo como obrigatório
        />
      </div>
      <button type="submit">Adicionar</button> 
    </form>
  );
}

export default AddMedico; // Exporta o componente AddMedico como padrão
