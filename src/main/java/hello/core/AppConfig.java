package hello.core;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.MemberRepository;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;

@Configuration
public class AppConfig {
	
	// @Bean memberService -> new MemoryMemberRepository() 호출
	// @Bean orderService -> new MemoryMemberRepository() 호출
	//  ---> 싱글톤 깨지는 게 아닌가?

	// call AppConfig.memberService
	// call AppConfig.memberRepository
	// call AppConfig.memberRepository
	// call AppConfig.orderService
	// call AppConfig.memberRepository
	
	// call AppConfig.memberService
	// call AppConfig.memberRepository
	// call AppConfig.orderService
	
	@Bean
	public MemberService memberService() {
		System.out.println("call AppConfig.memberService");
		return new MemberServiceImpl(memberRepository());
	}
	
	@Bean
	public OrderService orderService() {
		System.out.println("call AppConfig.orderService");
		return new OrderServiceImpl(memberRepository(),discountPolicy());
	}

	@Bean
	public MemberRepository memberRepository() {
		System.out.println("call AppConfig.memberRepository");
	 	return new MemoryMemberRepository();
	}

	@Bean
	public DiscountPolicy discountPolicy() {
		return new RateDiscountPolicy();
	}
}