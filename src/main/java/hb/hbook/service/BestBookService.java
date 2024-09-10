package hb.hbook.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

import hb.hbook.domain.bestbookdto.BestBookItemDTO;
import hb.hbook.domain.bestbookdto.BestBookItemsDTO;


@Service
public class BestBookService {

  /*
  ObjectMapper 객체를 이용해서 json 문자열을 java객체로 변경(역직렬화 json --> java)
  직렬화 : java --> Json
   */
  public List<BestBookItemDTO> parsingJson(String str) {
    // json -> 자바객체로 변환하는 기능
    ObjectMapper mapper = new ObjectMapper();

    List<BestBookItemDTO> list = null;
    try{
      // ForecastItemsDTO의 생성자로 보내준다
      BestBookItemsDTO items = mapper.readValue(str, BestBookItemsDTO.class);
      list = items.getItems();
    
    } catch(Exception e){
      e.printStackTrace();
    }
    return list;
  }
  
}
