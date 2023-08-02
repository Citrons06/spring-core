package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;

public class OrderServiceImpl implements OrderService {
	
	private final MemberRepository memberRepository;
	private final DiscountPolicy discountPolicy;
	
	public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
		this.memberRepository = memberRepository;
		this.discountPolicy = discountPolicy;
	}

	@Override	// OrderService가 DiscountPolicy에 의존하지 않고 discountPrice로 반환 값만을 받아 할인만 변경되도록 함(SRP)
	public Order createOrder(Long memberId, String itemName, int itemPrice) {
		Member member = memberRepository.findById(memberId);			// 회원 정보 조회
		int discountPrice = discountPolicy.discount(member, itemPrice);	// 할인 정책에 회원 정보 넘김
		
		return new Order(memberId, itemName, itemPrice, discountPrice);	// Order 객체 생성
	}

}
