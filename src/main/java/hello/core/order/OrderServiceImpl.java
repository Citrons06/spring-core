package hello.core.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import hello.core.discount.DiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;

@Component
public class OrderServiceImpl implements OrderService {
	
	private final MemberRepository memberRepository;
	private final DiscountPolicy discountPolicy;
	
	@Autowired
	public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
		this.memberRepository = memberRepository;
		this.discountPolicy = discountPolicy;
	}
	
	/*
	 * @Autowired public void init(MemberRepository memberRepository, DiscountPolicy
	 * discountPolicy) { this.memberRepository = memberRepository;
	 * this.discountPolicy = discountPolicy; }
	 */
	
	@Override
	public Order createOrder(Long memberId, String itemName, int itemPrice) {
		Member member = memberRepository.findById(memberId);			// 회원 정보 조회
		int discountPrice = discountPolicy.discount(member, itemPrice);	// 할인 정책에 회원 정보 넘김
		
		return new Order(memberId, itemName, itemPrice, discountPrice);	// Order 객체 생성
	}
	
	// 테스트 용도
	public MemberRepository getMemberRepository() {
		return memberRepository;
	}

}
