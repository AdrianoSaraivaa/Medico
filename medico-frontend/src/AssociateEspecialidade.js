import React, { useState, useEffect } from "react";
import axios from "axios";
import "./styles.css";

function AssociateEspecialidade() {
  const [medicoId, setMedicoId] = useState("");
  const [especialidadeId, setEspecialidadeId] = useState("");
  const [medicos, setMedicos] = useState([]);
  const [especialidades, setEspecialidades] = useState([]);
  const [error, setError] = useState("");

  useEffect(() => {
    axios
      .get("http://localhost:8080/api/medico")
      .then((response) => {
        setMedicos(response.data);
      })
      .catch((error) => {
        console.error("Houve um erro ao buscar os médicos!", error);
        setError(`Houve um erro ao buscar os médicos: ${error.message}`);
      });

    axios
      .get("http://localhost:8080/api/especialidade")
      .then((response) => {
        setEspecialidades(response.data);
      })
      .catch((error) => {
        console.error("Houve um erro ao buscar as especialidades!", error);
        setError(`Houve um erro ao buscar as especialidades: ${error.message}`);
      });
  }, []);

  const handleSubmit = async (event) => {
    event.preventDefault();
    try {
      const response = await axios.post(
        `http://localhost:8080/api/medico/${medicoId}/especialidade/${especialidadeId}`
      );
      console.log("Resposta:", response.data);
      alert("Especialidade associada com sucesso!");
      setMedicoId("");
      setEspecialidadeId("");
    } catch (error) {
      console.error("Houve um erro ao associar a especialidade!", error);
      setError(`Houve um erro ao associar a especialidade: ${error.message}`);
    }
  };

  return (
    <div className="container">
      <form onSubmit={handleSubmit}>
        <h2>Associar Especialidade a Médico</h2>
        {error && <p style={{ color: "red" }}>{error}</p>}
        <div>
          <label>Médico:</label>
          <select
            value={medicoId}
            onChange={(e) => setMedicoId(e.target.value)}
            required
          >
            <option value="">Selecione um médico</option>
            {medicos.map((medico) => (
              <option key={medico.id} value={medico.id}>
                {medico.nome}
              </option>
            ))}
          </select>
        </div>
        <div>
          <label>Especialidade:</label>
          <select
            value={especialidadeId}
            onChange={(e) => setEspecialidadeId(e.target.value)}
            required
          >
            <option value="">Selecione uma especialidade</option>
            {especialidades.map((especialidade) => (
              <option key={especialidade.id} value={especialidade.id}>
                {especialidade.nome}
              </option>
            ))}
          </select>
        </div>
        <button type="submit">Associar</button>
      </form>
    </div>
  );
}

export default AssociateEspecialidade;
