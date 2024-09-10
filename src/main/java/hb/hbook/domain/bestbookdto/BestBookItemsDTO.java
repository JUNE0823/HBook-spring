package hb.hbook.domain.bestbookdto;

import java.util.Arrays;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor // 스페셜 생성자들은 원래 자동으로 실행하지 않는데 이어노테이션이 가능하게함
public class BestBookItemsDTO {

  @JsonProperty("item")
  private List<BestBookItemDTO> items;
  
  @JsonCreator
  public BestBookItemsDTO(@JsonProperty("version") JsonNode node) { //version을 기준점 node로 찾기
    try{
      ObjectMapper mapper = new ObjectMapper();
      JsonNode itemNode = node.findValue("item"); //item 노드 찾기
      this.items = Arrays.stream(mapper.treeToValue(itemNode, BestBookItemDTO[].class))
                  .toList();
    } catch(Exception e) {
      e.printStackTrace();
    }
  }
}
