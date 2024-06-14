import React, { useState, useEffect } from "react";
import axios from "axios";
import "./styles.css"; // Certifique-se de que o CSS está importado

function MedicoList() {
  const [medicos, setMedicos] = useState([]);
  const [editMedico, setEditMedico] = useState(null);
  const [nome, setNome] = useState("");
  const [crm, setCrm] = useState("");

  useEffect(() => {
    axios
      .get("http://localhost:8080/api/medico")
      .then((response) => {
        setMedicos(response.data);
      })
      .catch((error) => {
        console.error("Houve um erro ao buscar os médicos!", error);
      });
  }, []);

  const handleEdit = (medico) => {
    setEditMedico(medico);
    setNome(medico.nome);
    setCrm(medico.crm);
  };

  const handleUpdate = async (event) => {
    event.preventDefault();
    try {
      const response = await axios.put(
        `http://localhost:8080/api/medico/${editMedico.id}`,
        { nome, crm }
      );
      setMedicos((prevMedicos) =>
        prevMedicos.map((med) =>
          med.id === editMedico.id ? response.data : med
        )
      );
      setEditMedico(null);
      setNome("");
      setCrm("");
      alert("Médico atualizado com sucesso!");
    } catch (error) {
      console.error("Houve um erro ao atualizar o médico!", error);
    }
  };

  const handleDelete = async (id) => {
    try {
      await axios.delete(`http://localhost:8080/api/medico/${id}`);
      setMedicos((prevMedicos) => prevMedicos.filter((med) => med.id !== id));
      alert("Médico deletado com sucesso!");
    } catch (error) {
      console.error("Houve um erro ao deletar o médico!", error);
    }
  };

  return (
    <div className="lista-medicos">
      <h2>Lista de Médicos</h2>
      {editMedico ? (
        <form onSubmit={handleUpdate} className="edit-form">
          <h3>Editar Médico</h3>
          <div>
            <label>Nome:</label>
            <input
              type="text"
              value={nome}
              onChange={(e) => setNome(e.target.value)}
              required
            />
          </div>
          <div>
            <label>CRM:</label>
            <input
              type="text"
              value={crm}
              onChange={(e) => setCrm(e.target.value)}
              required
            />
          </div>
          <button type="submit" className="update-button">Atualizar</button>
          <button type="button" className="cancel-button" onClick={() => setEditMedico(null)}>
            Cancelar
          </button>
        </form>
      ) : (
        <ul className="medico-list">
          {medicos.map((medico) => (
            <li key={medico.id} className="medico-item">
              <div className="medico-info">
                <h3>{medico.nome}</h3>
                <p>CRM: {medico.crm}</p>
                <p>
                  Especialidades:{" "}
                  {medico.especialidades.map((e) => e.nome).join(", ")}
                </p>
              </div>
              <div className="action-buttons">
                <button className="edit-button" onClick={() => handleEdit(medico)}>Editar</button>
                <button className="delete-button" onClick={() => handleDelete(medico.id)}>Deletar</button>
              </div>
            </li>
          ))}
        </ul>
      )}
    </div>
  );
}

export default MedicoList;
