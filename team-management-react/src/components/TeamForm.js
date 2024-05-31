import React, { useState, useEffect } from "react";
import { useNavigate, useParams } from "react-router-dom";
import teamAPI from "../services/teamAPI";

const TeamForm = () => {
  const { id } = useParams();
  const navigate = useNavigate();
  const [name, setName] = useState("");
  const [slogan, setSlogan] = useState("");
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState("");

  useEffect(() => {
    if (id) {
      fetchTeam(id);
    }
  }, [id]);

  const fetchTeam = async (id) => {
    setLoading(true);
    setError("");
    try {
      const response = await teamAPI.getTeamById(id);
      const { name, slogan } = response.data;
      setName(name);
      setSlogan(slogan);
    } catch (error) {
      setError(
        "Erreur lors de la récupération de l'équipe. Veuillez réessayer plus tard.."
      );
      console.error("Error fetching team: ", error);
    } finally {
      setLoading(false);
    }
  };

  const handleSubmit = async (event) => {
    event.preventDefault();
    const team = { name, slogan };
    setLoading(true);
    setError("");
    try {
      if (id) {
        await teamAPI.updateTeam(id, team);
      } else {
        await teamAPI.createTeam(team);
      }
      navigate("/teams");
    } catch (error) {
      setError(
        "Erreur lors de la création ou la mise à jour de l'équipe. Veuillez réessayer plus tard."
      );
      console.error("Error saving team: ", error);
    } finally {
      setLoading(false);
    }
  };

  return (
    <div>
      <h2 className="mb-4">{id ? "Modifier l'équipe" : "Créer une équipe"}</h2>
      {loading ? (
        <p>Chargement...</p>
      ) : (
        <form onSubmit={handleSubmit}>
          {error && <div className="alert alert-danger">{error}</div>}
          <div className="mb-3">
            <label htmlFor="name" className="form-label">
              Nom:
            </label>
            <input
              type="text"
              className="form-control"
              id="name"
              value={name}
              onChange={(e) => setName(e.target.value)}
              required
            />
          </div>
          <div className="mb-3">
            <label htmlFor="slogan" className="form-label">
              Slogan:
            </label>
            <input
              type="text"
              className="form-control"
              id="slogan"
              value={slogan}
              onChange={(e) => setSlogan(e.target.value)}
              required
            />
          </div>
          <button type="submit" className="btn btn-primary me-2">
            {id ? "Mettre à jour" : "Créer"}
          </button>
          <button
            type="button"
            className="btn btn-secondary"
            onClick={() => navigate("/teams")}
          >
            Annuler
          </button>
        </form>
      )}
    </div>
  );
};

export default TeamForm;
