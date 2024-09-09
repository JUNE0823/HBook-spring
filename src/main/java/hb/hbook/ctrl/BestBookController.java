package hb.hbook.ctrl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hb.hbook.service.BestBookService;

@RestController
@RequestMapping("/bestbook")
public class BestBookController {
  @Autowired
  private BestBookService bestBookService;
  
  // 1. 베스트 셀러 조회


}
