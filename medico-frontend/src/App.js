import React from "react";
import MedicoList from "./MedicoList";
import AddMedico from "./AddMedico";
import AddEspecialidade from "./AddEspecialidade";
import AssociateEspecialidade from "./AssociateEspecialidade";
import logo from "./Images/logotipo.png";

function App() {
  return (
    <div className="App">
      <header className="header">
        <img src={logo} alt="Logo Senai" />
        <h1>Adriano Saraiva 2DN                  ...</h1>
      </header>
      <div className="container">
        <AddMedico />
        <AddEspecialidade />
        <AssociateEspecialidade />
        <MedicoList />
      </div>
    </div>
  );
}

export default App;
