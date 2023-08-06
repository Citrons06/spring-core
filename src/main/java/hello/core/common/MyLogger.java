package hello.core.common;

import java.util.UUID;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope(value = "request")
public class MyLogger {
	
	private String uuid;
	private String requestURL;
	
	public void setRequestURL(String requestURL) {
		this.requestURL = requestURL;
	}
	
	// 로그 남기기
	public void log(String message) {
		System.out.println("[" + uuid + "]" + "[" + requestURL + "] " + message);
	}
	
	@PostConstruct
	public void init() {
		String uuid = UUID.randomUUID().toString();	// unigue한 아이디 생성
		System.out.println("[" + uuid + "] request scope bean create:" + this);
	}
	
	@PreDestroy
	public void close() {
		System.out.println("[" + uuid + "] request scope bean close:" + this);
	};
	

}
