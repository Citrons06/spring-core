package hello.core.member;

public class MemberServiceImpl implements MemberService {
	
	private final MemberRepository memberRepository;
	
	public MemberServiceImpl(MemberRepository memberRepository) {
		this.memberRepository = memberRepository;
	}

	@Override		// 다형성에 의해 오버라이딩된 save 호출
	public void join(Member member) {
		memberRepository.save(member);				// join에서 save 호출
	}

	@Override
	public Member findMember(Long memberId) {
		return memberRepository.findById(memberId); // findMember에서 findBtyId 호출
	}

}
