package hb.hbook.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ReportRequestDTO {
  
  private String  bookname;
  private String  content;
  private String  discuss;
  private Integer user_id;
  private String  teamname;
}
