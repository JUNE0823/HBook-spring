package hb.hbook.ctrl.aladinapi;

import java.io.InputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import java.time.LocalDate;
import java.time.temporal.ChronoField;
import java.time.temporal.TemporalAdjusters;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hb.hbook.domain.bestbookdto.BestBookItemDTO;
import hb.hbook.service.BestBookService;
import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.annotation.GetMapping;



@RestController
@RequestMapping("/bestbook")
@Slf4j
public class BestBookController {

  @Autowired
  private BestBookService bestBookService;

  // application.properties의 키값들을 가져오는 어노테이션
  // Request URL 만들기
  @Value("${openApi.url}")
  private String url;

  @Value("${openApi.serviceKey}")
  private String serviceKey;

  @Value("${openApi.dataType}")
  private String dataType;

  @Value("${openApi.version}")
  private String version;

  @Value("${openApi.queryType}")
  private String queryType;

  @Value("${openApi.start}")
  private String start;

  @Value("${openApi.maxResults}")
  private String maxResults;

  @Value("${openApi.searchTarget}")
  private String searchTarget;

  @GetMapping
  public ResponseEntity<List<BestBookItemDTO>> getBestbook() {
        // 현재 날짜를 기준으로 랜덤 날짜 생성
      String randomDate = generateRandomDateWithin3Years();

        // URL 생성
      String requestURL = url +
                            "?ttbkey=" + serviceKey +
                            "&QueryType=" + queryType +
                            "&MaxResults=" + maxResults +
                            "&start=" + start +
                            "&SearchTarget=" + searchTarget +
                            "&output=" + dataType +
                            "&Version=" + version +
                            "&" + randomDate;

                            log.info("Generated Request URL: {}", requestURL);

    // 외부 API와 연결
    HttpURLConnection http   = null ; 
    InputStream       stream = null ; 
    String            result = null ;

    List<BestBookItemDTO> list  = null ;
    try {
      // URL 객체 생성
      URL url = new URL(requestURL);
      // HttpURLConnection객체와 해당 url과 연결
      http = (HttpURLConnection)url.openConnection();
      // 요청 보내서 연결확인  -> 요청이 성공하면 200코드 
      int code = http.getResponseCode() ;   
      log.info("conection code : ", code);
      System.out.println(code);

      // 응답 데이터 처리
      if( code == 200 ) {
        // 데이터를 읽어와서
        stream = http.getInputStream() ; 
        log.info("debug >>> check stream", stream);
        System.out.println(stream);

        // string으로 역직렬화
        result = readString(stream) ;
        // BestBookItemsDTO로 변환
        list = bestBookService.parsingJson(result); 

      } else {

      }
    } catch (Exception e) {
      e.printStackTrace();
    } finally {

    }
        
    return new ResponseEntity<>(list , HttpStatus.OK); 
  }
  
  private String readString(InputStream stream) throws IOException{
    BufferedReader    br = new BufferedReader(new InputStreamReader(stream, "UTF-8"));
    String         input = null;
    StringBuilder result = new StringBuilder();
    String lineSeparator = System.lineSeparator(); // 시스템에 따라 줄바꿈 문자를 가져옴
    
    while((input = br.readLine()) != null){
      result.append(input).append(lineSeparator);
    }
    br.close();
    return result.toString() ;    
  }


   //베스트셀러를 조회할 주간 “Year=2022&Month=5&Week=3”형식으로 요청
  public static String generateRandomDateWithin3Years() {
    Random random = new Random();
    LocalDate now = LocalDate.now();
    
    // 3년 이내의 랜덤 연도 선택 (현재 연도 포함)
    int randomYear = now.getYear() - random.nextInt(4); // 0부터 3년 전까지 랜덤 선택

    int randomMonth = 1;
    // 1월부터 12월까지 랜덤 월 선택
    if(randomYear == now.getYear()){
      randomMonth = random.nextInt(now.getMonthValue())+1;
    } else {
      randomMonth = random.nextInt(12) + 1; // 1~12 사이의 값
    }
    
    // 해당 연도와 월의 첫 번째 날 구하기
    LocalDate firstDayOfMonth = LocalDate.of(randomYear, randomMonth, 1);
    
    // 해당 월의 마지막 날 구하기
    LocalDate lastDayOfMonth = firstDayOfMonth.with(TemporalAdjusters.lastDayOfMonth());
    
    // 해당 월의 총 주 수 구하기 (월의 첫날 ~ 마지막 날의 주 계산)
    int maxWeeksInMonth = lastDayOfMonth.get(ChronoField.ALIGNED_WEEK_OF_MONTH);
    
    // 1부터 해당 월의 총 주 수까지 랜덤 선택
    int randomWeek = random.nextInt(maxWeeksInMonth) + 1;

    // "Year=YYYY&Month=M&Week=W" 형식으로 반환
    return "Year=" + randomYear + "&Month=" + randomMonth + "&Week=" + randomWeek;
}
}
