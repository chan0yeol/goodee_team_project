package com.min.edu.ctrl;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.min.edu.dto.EmpDto;
import com.min.edu.dto.StockDto;
import com.min.edu.service.IEmpService;
import com.min.edu.service.IManagerService;
import com.min.edu.service.IStockInService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequiredArgsConstructor
@Slf4j
public class UserRestController {
	private final IEmpService empService;
	
	private final IStockInService iStockInService;
	
	private final IManagerService managerService; 
	@PostMapping("/login.do")
	public String loginChk(@RequestParam Map<String, Object> map, HttpSession session) {
		log.info("{}", map);
		EmpDto loginDto = empService.login(map);
		String rtnURL = "";
		if(loginDto != null) {
			session.setAttribute("emp", loginDto);
			session.setMaxInactiveInterval(60*10*6);
			if(loginDto.getDeptno() == 50 ) {
				rtnURL += "./stockInAllpage.do";
			} else if(loginDto.getDeptno() == 60) {
				rtnURL += "./stockInAllpage.do";
			} else if(loginDto.getDeptno() == 70) {
				rtnURL += "./stockOutAllInfo.do";
			}
		}
//		log.info("{}",loginDto);
		return loginDto != null?"{\"isc\":\"true\",\"url\":\""+rtnURL+"\"}":"{\"isc\":\"false\"}";
//		return null;
	}
	@PostMapping("/selectDateRange.do")
	public String rangeStockIn(@RequestParam Map<String, Object> map){
		List<StockDto> stockList = iStockInService.selectDateRange(map);
		log.info("{}", map);
		Gson gson = new GsonBuilder().create();
		String stockListJson = gson.toJson(stockList);
		log.info("{}",stockListJson);
		return stockListJson;
	}
	
	@GetMapping("/managerAccept.do")
	public String ManagerAccept(@RequestParam String id) {
		System.out.println(id);
		StockDto updateDto = managerService.findByStockId(id);
		updateDto.setIs_accepted("Y");
		int row = managerService.updateStockOutRequest(updateDto);
//		return "true";
		return row>0?"true":"false";
	}
	
	@GetMapping("/managerAcceptList.do")
	public String managerAcceptList(@RequestParam List<String> chkid) {
		
		return "";
	}
	
	

}
