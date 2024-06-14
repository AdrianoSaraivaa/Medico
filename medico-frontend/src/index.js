import React from "react"; // Importa React
import { createRoot } from "react-dom/client"; // Importa a função createRoot do React DOM para criar a raiz de renderização
import "./index.css"; // Importa o arquivo de estilos CSS
import App from "./App"; // Importa o componente App

const container = document.getElementById("root"); // Seleciona o elemento DOM com o ID "root"
const root = createRoot(container); // Cria uma raiz de renderização React associada ao elemento selecionado

root.render(
  <React.StrictMode> 
    <App /> 
  </React.StrictMode>
);
