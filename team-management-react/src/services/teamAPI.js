// services/teamAPI.js
import axios from "axios";

const API_BASE_URL = "http://localhost:8080/api/teams";

const teamAPI = {
  getAllTeams: () => axios.get(API_BASE_URL),
  getTeamById: (id) => axios.get(`${API_BASE_URL}/${id}`),
  createTeam: (team) => axios.post(API_BASE_URL, team),
  updateTeam: (id, team) => axios.put(`${API_BASE_URL}/${id}`, team),
  deleteTeam: (id) => axios.delete(`${API_BASE_URL}/${id}`),
};

export default teamAPI;
