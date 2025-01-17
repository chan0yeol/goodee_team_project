package com.min.edu.ctrl;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.min.edu.dto.StockDto;
import com.min.edu.service.IManagerService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequiredArgsConstructor
@Slf4j
public class ManagerController {
	
	private final IManagerService managerService;
	
	@GetMapping("/managerStockIn.do")
	public String ManagerStockIn(Model model) {
		List<StockDto> lists = managerService.selectStockInList();
		model.addAttribute("StockInList",lists);
		return "managerStockIn";
	}
	@GetMapping("/managerStockOut.do")
	public String ManagerStockOut(Model model) {
		List<StockDto> lists = managerService.selectStockOutList();
		model.addAttribute("StockOutList",lists);
		return "managerStockOut";
	}
	
}
