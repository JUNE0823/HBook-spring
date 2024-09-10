package hb.hbook.ctrl;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.HashMap;

import hb.hbook.domain.UserRequestDTO;
import hb.hbook.domain.UserResponseDTO;
import hb.hbook.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;




@RestController
@RequestMapping("/user")
public class UserController {

  @Autowired
  private UserService userService;


  // 2. 사용자 등록
  @PostMapping
  public ResponseEntity<UserRequestDTO> createUser(@RequestBody UserRequestDTO params) {
    System.out.println("debug >>> createUser : " + params);
    userService.saveUser(params);

    return new ResponseEntity<>(params, HttpStatus.OK);
  }
  
  // 3. 사용자 전체 조회
  @GetMapping
  public ResponseEntity<List<UserResponseDTO>> selectUser() {
    System.out.println("debug >>> selectUser excute");
    List<UserResponseDTO> response = userService.getUser();
    return new ResponseEntity<>(response, HttpStatus.OK);
  }
  

  // 4. 팀별 사용자 조회
  @GetMapping("/{teamname}")
  public ResponseEntity<List<UserResponseDTO>> selectSameTeam(@PathVariable("teamname") String teamname) {
    System.out.println("debug >>> selectUser excute");
    Map<String, String> map = new HashMap<>();
    map.put("teamname", teamname);
    List<UserResponseDTO> response = userService.getSameTeam(map);
    System.out.println("debug >>> response :" + response);

    return new ResponseEntity<>(response, HttpStatus.OK);
  }
  

  // 5. 사용자 삭제
  @DeleteMapping("{id}")
  public ResponseEntity<String> deleteUser(@PathVariable("id") Integer id){
    Map<String, Integer> map = new HashMap();
    map.put("id", id);
    userService.deleteUser(map);
    return new ResponseEntity<String>(id+" 번쨰 데이터 삭제", HttpStatus.OK);
  }

}
