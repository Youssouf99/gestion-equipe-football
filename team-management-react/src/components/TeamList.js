import React, { useEffect, useState } from "react";
import teamAPI from "../services/teamAPI";
import { Link } from "react-router-dom";

const TeamList = () => {
  const [teams, setTeams] = useState([]);
  const [error, setError] = useState("");
  const [loading, setLoading] = useState(false);

  useEffect(() => {
    fetchTeams();
  }, []);

  const fetchTeams = async () => {
    setLoading(true);
    setError("");
    try {
      const response = await teamAPI.getAllTeams();
      setTeams(response.data);
    } catch (error) {
      setError(
        "Erreur lors de la récupération des équipes. Veuillez réessayer plus tard."
      );
      console.error("Error fetching teams: ", error);
    } finally {
      setLoading(false);
    }
  };

  return (
    <div>
      <h2 className="mb-4">Liste des équipes</h2>
      {loading ? (
        <p>Chargement...</p>
      ) : error ? (
        <div className="alert alert-danger">{error}</div>
      ) : (
        <>
          <ul className="list-group mb-4">
            {teams.map((team) => (
              <li key={team.id} className="list-group-item">
                <Link to={`/teams/${team.id}`}>
                  <h5>{team.name}</h5>
                </Link>
                <p>{team.slogan}</p>
              </li>
            ))}
          </ul>
          <Link to="/teams/new" className="btn btn-primary">
            Ajouter une nouvelle équipe
          </Link>
        </>
      )}
    </div>
  );
};

export default TeamList;
