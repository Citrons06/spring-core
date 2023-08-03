package hello.core.beanfind;

import org.junit.jupiter.api.Test;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import hello.core.AppConfig;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;

import static org.junit.jupiter.api.Assertions.*;

public class ApplicationContextBasicFindTest {
	
	AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
	
	@Test
	@DisplayName("빈 이름으로 조회")
	void findBeanByName() {
		MemberService memberService = ac.getBean("memberService", MemberService.class);
		System.out.println("memberService = " + memberService);
		System.out.println("memberService.getClass() = " + memberService.getClass());
		// memberService가 MemberServiceImpl의 인스턴스면 성공
		Assertions.assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
	}
	
	@Test
	@DisplayName("이름 없이 타입으로만 조회")
	void findBeanByType() {
		MemberService memberService = ac.getBean(MemberService.class);
		Assertions.assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
	}

	@Test
	@DisplayName("구체 타입으로 조회")
	void findBeanByName2() {										// 구현에 의존
		MemberServiceImpl memberService = ac.getBean("memberService", MemberServiceImpl.class);
		Assertions.assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
	}
	
	@Test
	@DisplayName("빈 이름으로 조회 실패")
	void findBeanByNameX() {
		// ac.getBean("xxxxx", MemberService.class);
		
		// 무조건 예외가 발생해야 테스트 성공
		assertThrows(NoSuchBeanDefinitionException.class, 
				() -> ac.getBean("xxxxx", MemberService.class));	// 로직을 실행하면 예외가 발생해야 테스트 성공
																	// NoSuchBeanDefinitionException
	}
}