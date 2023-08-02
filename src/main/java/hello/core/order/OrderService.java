package hello.core.order;

public interface OrderService {
	// 회원 id, 상품명, 상품가격 파라미터로 넘김
	Order createOrder(Long memberId, String itemName, int itemPrice);

}
