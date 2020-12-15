package jeju.increpas.www.controller;


import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.*;

import jeju.increpas.www.dao.*;
import jeju.increpas.www.service.*;
import jeju.increpas.www.vo.*;

@Controller
public class MainController {
	
	@Autowired
	MainService mService;
	@Autowired
	MainDao mDao;
	
	// < 메인 페이지 - 메인 폼 요청 >
	@RequestMapping("/main.jeju")
	public ModelAndView getMain(ModelAndView mv, MapVO mVO) {
			mService.getMain(mv, mVO);
		return mv;
	}
	// < 메인 페이지 - 로그인 Proc 요청 >
	@RequestMapping("/loginProc.jeju")
	public ModelAndView loginProc(ModelAndView mv, HttpSession session, MemberVO memberVO) {
		int cnt = mDao.loginProc(memberVO);
		if(cnt == 1) {
			//로그인 성공
			String id = memberVO.getId();
			session.setAttribute("SID", id);
		}else {
			//로그인 실패
		}
		mv.setViewName("redirect:/main.jeju");
		return mv;
	}
	// < 메인 페이지 - 로그아웃 Proc 요청 >
	@RequestMapping("/logoutProc.jeju")
	public ModelAndView logoutProc(ModelAndView mv, HttpSession session) {
		session.removeAttribute("SID");
		mv.setViewName("redirect:/main.jeju");
		return mv;
	}
	// < 메인 페이지 - 지도 markerlist 요청 >
	@ResponseBody
	@RequestMapping(value="/markerlist.jeju", method=RequestMethod.POST, produces="application/text; charset=UTF8")
	public String markerlist() {
		String str = mService.markerlist();
		return str;
	}
}
