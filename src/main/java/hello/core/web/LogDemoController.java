package hello.core.web;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import hello.core.common.MyLogger;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class LogDemoController {
	
	private final LogDemoService logDemoService;
	private final MyLogger myLogger;				// 의존관계 주입이 일어날 때 myLogger를 내놓아야 하는데 request가 없음
	
	@RequestMapping("log-demo")
	@ResponseBody	// 화면 없이 문자로 바로 반환
	public String logDemo(HttpServletRequest request) {
		String requestURL = request.getRequestURL().toString();
		myLogger.setRequestURL(requestURL);			// 로그에 url 남김
		
		myLogger.log("controller test");
		logDemoService.logic("testId");
		return "OK";
	}
	

}
