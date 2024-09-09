package hb.hbook.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import hb.hbook.domain.ReportRequestDTO;
import hb.hbook.domain.ReportResponseDTO;

@Mapper
public interface ReportMapper {

  public void reportSave(ReportRequestDTO report);

  public List<ReportResponseDTO> getReportSameTeam(Map<String, String> map);

  public void updateReport(ReportRequestDTO report);

  
  
}
