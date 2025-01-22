package com.min.edu.ctrl;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.min.edu.bean.PageBean;
import com.min.edu.dto.EmpDto;
import com.min.edu.dto.ProductInfoDto;
import com.min.edu.dto.StockDto;
import com.min.edu.service.IProductService;
import com.min.edu.service.IStockInService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequiredArgsConstructor
@Slf4j
public class StockInController {

	private final IStockInService stockInService;
	private final IProductService productService;
	
	private final PageBean p;

	@GetMapping("/stockInAllpage.do")
	public String pagingStockInAll(@RequestParam(value="page", defaultValue = "1" ) String page, Model model) {
		int selPage = Integer.parseInt(page);
		p.setTotalCount(stockInService.cntStockIn());
		p.setCountList(3);
		
		p.setCountPage(5);
		p.setTotalPage(0);
		
		p.setPage(selPage);
		p.setStagePage(0);
		p.setEndPage(0);
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("first", p.getPage()*p.getCountList() - (p.getCountList()-1) ); // (2*10) - (10-1) = 11
		map.put("last", p.getPage()*p.getCountList());
		
		List<StockDto> StockInList = stockInService.selectPageAll(map);
		System.out.println(StockInList);
		model.addAttribute("StockInList", StockInList);
		model.addAttribute("page", p);
		return "stockInAllpage";
	}
	
//	/stockInAllInfo.do
	@GetMapping("/stockInAllInfo.do") // 전체 조회
	public String stockInAllInfo(Model model, HttpSession session) {
		log.info("StockInController /stockInAllInfo.do GET 요청");
		EmpDto loginDto = (EmpDto) session.getAttribute("emp");
		List<StockDto> lists = stockInService.selectAll();
		model.addAttribute("StockInList", lists);
		return "stockInAllInfo";
	}
	@GetMapping("/stockAllInfoMgr.do")
	public String stockInAllInfoMgr(int mgr, Model model, HttpSession session) {
		log.info("StockInController /stockInAllInfo.do GET 요청");
		EmpDto loginDto = (EmpDto) session.getAttribute("emp");
		List<StockDto> lists = stockInService.selectStockInByMgr(mgr);
		model.addAttribute("StockInList", lists);
		return "stockInAllInfo";
	}
	
//	/stockInInfo.do 
	@GetMapping("/stockInInfo.do")
	public String stockInInfo(Model model, int id, HttpSession session) {
		log.info("StockInController stockInInfo.do GET ");
		EmpDto loginDto = (EmpDto) session.getAttribute("emp");
		StockDto stock = stockInService.stockInDetail(id);
		ProductInfoDto product = productService.product_select2(stock.getProduct_id());

		model.addAttribute("stockIn", stock);
		model.addAttribute("product", product);
		return "stockInInfoServlet";
	}

	// stockInServlet.do
	@GetMapping("/stockInForm.do")
	public String stockInServlet(Model model, HttpSession session) {
		EmpDto loginDto = (EmpDto) session.getAttribute("emp");
		log.info("StockInController stockInServlet.do GET");
		List<ProductInfoDto> lists = productService.product_select();
		model.addAttribute("productList", lists);
		return "stockInServlet";
	}
	
	@PostMapping("/stockInInsert.do")
	public String stockInInsert(int stock_amount, String product_id, Model model, HttpSession session,HttpServletResponse response) throws IOException {
		response.setContentType("text/html;charset=UTF-8");
		EmpDto loginDto = (EmpDto) session.getAttribute("emp");
		int row;
		if (loginDto.getJob().equals("팀장")) {
			StockDto inDto = StockDto.builder().stock_amount(stock_amount).stock_mgr(loginDto.getEmpno())
					.is_accepted("Y").product_id(product_id).build();
			row = stockInService.insert(inDto);
		} else {
			StockDto inDto = StockDto.builder().stock_amount(stock_amount).stock_mgr(loginDto.getEmpno())
					.is_accepted("N").product_id(product_id).build();
			row = stockInService.insert(inDto);
		}
		if (row == 1) {
			return "redirect:/stockInAllInfo.do";
		}
		return "";
	}
	
	// 업데이트 폼
	@GetMapping("/stockInUpdate.do")
	public String stockInUpdateForm(int id, Model model) {
		StockDto dto = stockInService.selectById(id);
		model.addAttribute("stock", dto);
		return "stockInUpdate";
	}

	@PostMapping("/stockInUpdate.do")
	public String stockInUpdate(int id,int amount, Model model) {
		StockDto dto = stockInService.selectById(id);
		dto.setStock_amount(amount);
		int row = stockInService.updateStockInAmount(dto);
		if(row == 1) {
			return "redirect:/stockInInfo.do?id="+id;
		} else {
			return "redirect:/stockInUpdate.do?id="+id;
		}
	}
	
	
}
