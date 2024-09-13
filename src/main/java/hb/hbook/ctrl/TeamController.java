package hb.hbook.ctrl;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hb.hbook.domain.TeamRequestDTO;
import hb.hbook.domain.TeamResponseDTO;
import hb.hbook.service.TeamService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;




@RestController
@RequestMapping("/team")
public class TeamController {
  
  @Autowired
  private TeamService teamService;

  @GetMapping
  public ResponseEntity<List<TeamResponseDTO>> getTemaName() {
    List<TeamResponseDTO> result = teamService.selectTeamName();
    return new ResponseEntity<>(result, HttpStatus.OK);
  }

  @PostMapping
  public ResponseEntity<Void> createTeam(@RequestBody TeamRequestDTO params) {
      teamService.postTeam(params);
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }
  
  
}
