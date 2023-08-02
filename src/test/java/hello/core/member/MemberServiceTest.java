package hello.core.member;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import hello.core.AppConfig;

public class MemberServiceTest {
	
	MemberService memberService;
	
	@BeforeEach
	public void beforeEach() {
		AppConfig appConfig = new AppConfig();
		memberService = appConfig.memberService();
	}
	
	@Test
	void join() {
		// given : 멤버 서비스에 아이디가 1L인 회원 가입
		Member member = new Member(1L, "memberA", Grade.VIP);
		
		// when : 1L 회원이 가입했을 때
		memberService.join(member);
		Member findMember = memberService.findMember(1L);
		
		// then : 1L 회원과 가입된 회원이 같은지 찾음
		Assertions.assertThat(member).isEqualTo(findMember);
		
	}
	
}
