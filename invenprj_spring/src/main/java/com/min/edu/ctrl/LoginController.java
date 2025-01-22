package com.min.edu.ctrl;

import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.resource.HttpResource;

import com.min.edu.dto.EmpDto;
import com.min.edu.repository.IEmpDao;
import com.min.edu.service.IEmpService;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class LoginController {
	
	@Autowired
	private IEmpDao dao;
	
	@GetMapping("/login.do")
	public String loginGet(HttpSession session) {
		session.invalidate();
		return "login";
	}
	@GetMapping("/logout.do")
	public String logout(HttpSession session) {
		session.invalidate();
		return "login";
	}
	
	@PostMapping("/loginServlet.do")
	public String login(@RequestParam Map<String, Object> map, Model model , HttpSession session,HttpServletResponse resp) throws IOException {
		resp.setContentType("text/html;charset=UTF-8");
		log.info("login POST ");
		System.out.println(map);
		EmpDto loginDto = dao.login(map);
		if(loginDto != null) {
			session.setAttribute("emp", loginDto);
			session.setMaxInactiveInterval(60*10*6);
			if(loginDto.getDeptno() == 50 ) {
				return "redirect:/stockInAllpage.do";
			} else if(loginDto.getDeptno() == 60) {
				return "redirect:/stockInAllpage.do";
			} else if(loginDto.getDeptno() == 70) {
				return "redirect:./stockOutAllInfo.do";
			}
		} else {
//			resp.getWriter().print("<script>Swal.fire('사원 정보가 없습니다.'); location.href='./login.do';</script>");
			resp.getWriter().print("<script>alert('로그인 정보가 없습니다.');location.href='./login.do';</script>");
//			String fail = "로그인 실패";
//			model.addAttribute("fail",fail);
		}
		return null;
	}
}
