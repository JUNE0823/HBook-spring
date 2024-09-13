package hb.hbook.domain.bestbookdto;

import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@AllArgsConstructor // 스페셜 생성자들은 원래 자동으로 실행하지 않는데 이어노테이션이 가능하게함
public class BestBookItemsDTO {

  @JsonProperty("item")
  private List<BestBookItemDTO> items;
  
  @JsonCreator
  public BestBookItemsDTO(@JsonProperty("version") JsonNode node) {
      try {
          ObjectMapper mapper = new ObjectMapper();
          JsonNode itemNode = node.get("item"); // 최상위 노드에서 item 찾기
          
          if (itemNode != null && itemNode.isArray()) {
              this.items = Arrays.stream(mapper.treeToValue(itemNode, BestBookItemDTO[].class))
                                .toList();
          } else { 
              this.items = new ArrayList<>(); // itemNode가 없거나 배열이 아니면 빈 리스트 처리
          }
      } catch (Exception e) {
          e.printStackTrace();
      }
  }

}
