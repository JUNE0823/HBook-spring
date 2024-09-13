package hb.hbook.service;

import java.util.List;
import java.util.Map;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hb.hbook.domain.UserRequestDTO;
import hb.hbook.domain.UserResponseDTO;
import hb.hbook.mapper.UserMapper;


@Service
public class UserService {

  @Autowired
  private UserMapper userMapper;

  public Integer saveUser(UserRequestDTO params) {
    userMapper.saveUser(params);
    return params.getId();
  }

  public List<UserResponseDTO> getUser() {
    return userMapper.selectUser();
    
  }

  public List<UserResponseDTO> getSameTeam(Map<String, String> map) {
    return userMapper.getSameTeam(map);
  }

  public void deleteUser(Map<String, Integer> map) {
    
    userMapper.deleteUser(map);
  }

  public UserResponseDTO selectUser(Map<String, Integer> map) {
    return userMapper.selectUserRow(map);
  }
  
}
