package umc.spring.service.ReviewService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import umc.spring.apiPayload.code.status.ErrorStatus;
import umc.spring.apiPayload.exception.handler.MemberHandler;
import umc.spring.apiPayload.exception.handler.StoreHandler;
import umc.spring.converter.ReviewConverter;
import umc.spring.domain.Member;
import umc.spring.domain.Review;
import umc.spring.domain.Store;
import umc.spring.repository.MemberRepository2;
import umc.spring.repository.ReviewRepository2;
import umc.spring.repository.StoreRepository2;
import umc.spring.web.dto.ReviewRequestDTO;

@Service
@RequiredArgsConstructor
public class ReviewCommandServiceImpl implements ReviewCommandService{

    private final ReviewRepository2 reviewRepository;
    private final MemberRepository2 memberRepository;
    private final StoreRepository2 storeRepository;

    @Override
    public Review joinReview(ReviewRequestDTO.JoinDto request) {

        Member member = memberRepository.findById(request.getMemberId())
                .orElseThrow(() -> new MemberHandler(ErrorStatus.MEMBER_NOT_FOUND));

        Store store = storeRepository.findById(request.getStoreId())
                .orElseThrow(() -> new StoreHandler(ErrorStatus.STORE_NOT_FOUND));

        Review newReview = ReviewConverter.toReview(request, member, store);

        return reviewRepository.save(newReview);
    }
}
