package hb.hbook.ctrl;

import java.util.List;
import java.util.Map;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hb.hbook.domain.ReportRequestDTO;
import hb.hbook.domain.ReportResponseDTO;
import hb.hbook.service.ReportService;


import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;





@RestController
@RequestMapping("/report")
public class ReportController {

  @Autowired
  private ReportService reportService;

  
  // 6. 글 작성 
  @PostMapping
  public ResponseEntity<ReportRequestDTO> createReport(@RequestBody ReportRequestDTO report) {
      reportService.reportSave(report);
      
      return new ResponseEntity<>(report, HttpStatus.OK);
  }
  

  // 7. 글 조회
  @GetMapping("/{teamname}")
  public ResponseEntity<List<ReportResponseDTO>> selectReportSameTeam(@PathVariable("teamname") String teamname) {
    System.out.println("debug >>> selectUser excute");
    Map<String, String> map = new HashMap<>();
    map.put("teamname", teamname);
    List<ReportResponseDTO> response = reportService.getReportSameTeam(map);
    System.out.println("debug >>> response :" + response);

    return new ResponseEntity<>(response, HttpStatus.OK);
  }
  
  // 8. 글 수정
  @PatchMapping
  public ResponseEntity<String> patchReport(@RequestBody ReportRequestDTO report){
    reportService.updateReport(report);
    return new ResponseEntity<>("수정 완료", HttpStatus.OK);
  }

  // 9. 글 삭제(굳이 안만들어도 8번기능으로 해결 될듯)
  @DeleteMapping("{userid}")
  public ResponseEntity<Void> deleteReport(@PathVariable("userid") Integer id){
    Map<String, Integer> map = new HashMap<>();
    map.put("id", id);
    reportService.delReport(map);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }


  // 10. 대화 주제 정하기
  @GetMapping("/report/{teamname}")
  public ResponseEntity<String> randomDiscuss(@PathVariable("teamname") String teamname) {
    Map<String, String> map = new HashMap<>();
    map.put("teamname", teamname);
    String response = reportService.randomDiscussPick(map);
    return new ResponseEntity<>(response, HttpStatus.OK);
  }
  


}
