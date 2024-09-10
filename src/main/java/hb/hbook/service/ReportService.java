package hb.hbook.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hb.hbook.domain.ReportRequestDTO;
import hb.hbook.domain.ReportResponseDTO;
import hb.hbook.mapper.ReportMapper;

@Service
public class ReportService {

  @Autowired
  public ReportMapper reportMapper;

  public void reportSave(ReportRequestDTO report) {
    reportMapper.reportSave(report);
    
  }

  public List<ReportResponseDTO> getReportSameTeam(Map<String, String> map) {
    return reportMapper.getReportSameTeam(map);
  }

  public void updateReport(ReportRequestDTO report) {
    reportMapper.updateReport(report);
  }

  public String randomDiscussPick(Map<String, String> map) {
    List<ReportResponseDTO> response = reportMapper.getReportSameTeam(map);
    List<Integer> nums = new ArrayList<>();
    for(ReportResponseDTO obj : response){
      nums.add(obj.getUser_id());
    }

    Integer seletedNum = null ;
    
    if (!nums.isEmpty()) {
    // nums 리스트에서 랜덤하게 하나의 값을 선택
      int randomIndex = ThreadLocalRandom.current().nextInt(nums.size());
      seletedNum = nums.get(randomIndex);
      System.out.println("랜덤으로 선택된 값: " + seletedNum);
    }

    String seletedDiscuss = null;
    for(ReportResponseDTO obj : response){
      if(obj.getUser_id() == seletedNum){
      seletedDiscuss = obj.getDiscuss();
      break;
      }
    }
    System.out.println("랜덤으로 선택된 값: " + seletedDiscuss);


    // // Stream API로 찾아보기
    // Optional<String> discussValue = response.stream()
    // .filter(obj -> obj.getUser_id() == randomValue)  // user_id가 randomValue와 동일한 객체 필터링
    // .map(ReportResponseDTO::getDiscuss)  // discuss 필드 추출
    // .findFirst();  // 첫 번째 일치하는 값 찾기

    // // 값이 존재하는지 확인 후 출력
    // discussValue.ifPresentOrElse(
    // value -> System.out.println("user_id가 " + randomValue + "인 discuss 값: " + value),
    //   () -> System.out.println("해당 user_id를 찾을 수 없습니다.")
    // );

    return seletedDiscuss;
  }

  public void delReport(Map<String, Integer> map) {
    reportMapper.deleteReport(map);
  }
  

}
