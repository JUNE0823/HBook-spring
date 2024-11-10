package hb.hbook.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hb.hbook.domain.TeamRequestDTO;
import hb.hbook.domain.TeamResponseDTO;
import hb.hbook.mapper.TeamMapper;

@Service
public class TeamService {
  
  @Autowired
  public TeamMapper teamMapper;

  public List<TeamResponseDTO> selectTeamName() {
    return teamMapper.selectTeamNames();
  }

  public void postTeam(TeamRequestDTO params) {
    teamMapper.postTeam(params);
    
  }

  public void delTeam(String teamname) {
    
    teamMapper.delTeam(teamname);
  }
}
