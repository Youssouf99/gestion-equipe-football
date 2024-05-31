import React, { useState, useEffect } from "react";
import { useParams, useNavigate } from "react-router-dom";
import teamAPI from "../services/teamAPI";

const TeamDetails = () => {
  const { id } = useParams();
  const [team, setTeam] = useState({});
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState("");
  const navigate = useNavigate();

  useEffect(() => {
    fetchTeam(id);
  }, [id]);

  const fetchTeam = async (id) => {
    setLoading(true);
    setError("");
    try {
      const response = await teamAPI.getTeamById(id);
      setTeam(response.data);
    } catch (error) {
      setError(
        "Erreur lors de la récupération de l'équipe. Veuillez réessayer plus tard."
      );
      console.error("Error fetching team: ", error);
    } finally {
      setLoading(false);
    }
  };

  const handleDeleteTeam = async () => {
    setLoading(true);
    setError("");
    try {
      await teamAPI.deleteTeam(id);
      navigate("/teams");
    } catch (error) {
      setError(
        "Erreur lors de la suppression de l'équipe. Veuillez réessayer plus tard."
      );
      console.error("Error deleting team: ", error);
    } finally {
      setLoading(false);
    }
  };

  const handleBack = () => {
    navigate("/teams");
  };

  return (
    <div>
      <h2 className="mb-4">Détails de l'équipe</h2>
      {loading ? (
        <p>Chargement...</p>
      ) : error ? (
        <div className="alert alert-danger">{error}</div>
      ) : (
        <div className="card mb-4">
          <div className="card-body">
            <h5 className="card-title">{team.name}</h5>
            <p className="card-text">{team.slogan}</p>
            <button onClick={handleDeleteTeam} className="btn btn-danger me-2">
              Supprimer
            </button>
            <button
              onClick={() => navigate(`/teams/${id}/edit`)}
              className="btn btn-primary me-2"
            >
              Modifier
            </button>
            <button onClick={handleBack} className="btn btn-secondary">
              Retour
            </button>
          </div>
        </div>
      )}
    </div>
  );
};

export default TeamDetails;
