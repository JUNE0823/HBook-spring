package hb.hbook.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import hb.hbook.domain.UserRequestDTO;
import hb.hbook.domain.UserResponseDTO;

@Mapper
public interface UserMapper {

  public Integer saveUser(UserRequestDTO params);

  public List<UserResponseDTO> selectUser();

  public List<UserResponseDTO> getSameTeam(Map<String, String> map);

  public void deleteUser(Map<String, Integer> map);

  public UserResponseDTO selectUserRow(Map<String, Integer> map);

}
