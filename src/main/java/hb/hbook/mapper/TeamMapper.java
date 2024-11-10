package hb.hbook.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import hb.hbook.domain.TeamRequestDTO;
import hb.hbook.domain.TeamResponseDTO;

@Mapper
public interface TeamMapper {

  public List<TeamResponseDTO> selectTeamNames();

  public void postTeam(TeamRequestDTO params);

  public void delTeam(String teamname);
  
}
