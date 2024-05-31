import { BrowserRouter, Route, Routes } from "react-router-dom";
import "./App.css";
import TeamList from "./components/TeamList";
import TeamForm from "./components/TeamForm";
import TeamDetails from "./components/TeamDetails";
import React from "react";
import Navbar from "./components/Navbar";

function App() {
  return (
    <BrowserRouter>
      <div className="App">
        <Navbar />

        <div className="container mt-4">
          <h1 className="mb-4">Gestion des équipes</h1>
          <Routes>
            <Route path="/teams" element={<TeamList />} />
            <Route path="/teams/new" element={<TeamForm />} />
            <Route path="/teams/:id/edit" element={<TeamForm />} />
            <Route path="/teams/:id" element={<TeamDetails />} />
          </Routes>
        </div>
      </div>
    </BrowserRouter>
  );
}

export default App;
