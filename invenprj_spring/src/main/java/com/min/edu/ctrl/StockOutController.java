package com.min.edu.ctrl;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.min.edu.dto.EmpDto;
import com.min.edu.dto.ProductInfoDto;
import com.min.edu.dto.StockDto;
import com.min.edu.service.IProductService;
import com.min.edu.service.IStockOutService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequiredArgsConstructor
@Slf4j
public class StockOutController {
	private final IStockOutService stockOutService;
	private final IProductService productService;
	@GetMapping("/stockOutAllInfo.do")
	public String stockOutAllInfo(Model model, HttpSession session) {
		log.info("StockOutController /stockOutAllInfo.do GET 출고목록조회");
		List<StockDto> StockOutList = stockOutService.selectStockOutAll();
		model.addAttribute("StockOutList",StockOutList);
		return "stockOutAllInfo";
	}
	@GetMapping("/stockOutInfo.do")
	public String stockOutInfo(int id, Model model) {
		log.info("StockOutController /stockOutInfo.do GET 출고상세조회");
		StockDto stockOut = stockOutService.selectStockOutDetail(id);
		ProductInfoDto product = productService.product_select2(stockOut.getProduct_id());
		model.addAttribute("stockOut",stockOut);
		model.addAttribute("product",product);
		return "stockOutInfoServlet";
	}
	@GetMapping("/stockOutForm.do")
	public String stockOutForm(Model model) {
		log.info("StockOutController /stockOutForm.do GET 출고등록Form");
		List<ProductInfoDto> productList = productService.product_select();
		model.addAttribute("productList",productList);
		return "stockOutServlet";
	}
	
	@PostMapping("/stockOutInsert.do")
	public String stockOutInsert(StockDto dto, HttpSession session) {
		log.info("StockOutController /stockOutInsert.do POST 출고등록요청");
		EmpDto loginDto = (EmpDto) session.getAttribute("emp");
		int row;  
		if(loginDto.getJob().equals("팀장")) {
			dto.setStock_mgr(loginDto.getEmpno());
			dto.setIs_accepted("Y");
			row = stockOutService.insertStockOut(dto);
		} else {
			dto.setStock_mgr(loginDto.getEmpno());
			dto.setIs_accepted("N");
			row = stockOutService.insertStockOut(dto);
		}
		if(row == 1) {
			return "redirect:/stockOutAllInfo.do";
		}
		return "";
	}
	@GetMapping("/stockOutUpdate.do")
	public String stockOutUpdate(int id, Model model) {
		log.info("StockOutController /stockOutUpdate.do GET 출고수량업데이트Form");
		StockDto dto = stockOutService.selectStockOutDetail(id);
		model.addAttribute("stock",dto);
		return "stockOutUpdate";
	}
	@PostMapping("/stockOutUpdate.do")
	public String stockOutUpdateInsert(int id, int amount) {
		log.info("StockOutController /stockOutUpdate.do POST 출고수량업데이트 : {} ", amount);
		StockDto dto = stockOutService.selectStockOutDetail(id);
		dto.setStock_amount(amount);
		int row = stockOutService.updateStockOutAmount(dto);
		if(row == 1) {
			return "redirect:/stockOutInfo.do?id="+id;
		}
		return "";
		
	}
	@GetMapping("/stockAmount.do")
	public String stockAmountRank(Model model) {
		List<ProductInfoDto> lists = stockOutService.selectStockAmount();
		model.addAttribute("lists",lists);
		return "stockAmount";
	}
	@GetMapping("/stockSales.do")
	public String stockSalesRank(Model model) {
		List<ProductInfoDto> lists = stockOutService.selectStockSales();
		model.addAttribute("lists",lists);
		return "stockSales";
	}
	
	@GetMapping("/stockAllInfo.do")
	public String stockAllInfo(Model model) {
		log.info("재고전체조회");
		List<StockDto> lists = stockOutService.selectStockInventory();
		model.addAttribute("stockDto",lists);
		return "stockAllInfo";
	}
}
