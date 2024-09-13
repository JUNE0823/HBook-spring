package hb.hbook.domain.bestbookdto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true) // 없는 속성은 무시하도록 해주는 어노테이션
public class BestBookItemDTO {
  
  @JsonProperty("title")
  private String title;

  @JsonProperty("author")
  private String author;

  @JsonProperty("pubDate")
  private String pubDate;

  @JsonProperty("description")
  private String description;
  
  @JsonProperty("cover")
  private String cover;

  @JsonProperty("bestDuration")
  private String bestDuration;

}
