package umc.spring.service.MemberService;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.spring.domain.Member;
import umc.spring.domain.Mission;
import umc.spring.domain.Review;
import umc.spring.repository.MemberRepoository.MemberRepository;
import umc.spring.repository.MissionRepository2;
import umc.spring.repository.ReviewRepository2;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberQueryServiceImpl implements MemberQueryService{

    private final MemberRepository memberRepository;

    private final ReviewRepository2 reviewRepository;

    private final MissionRepository2 missionRepository;

    @Override
    public Optional<Member> findMember(Long id) {
        return memberRepository.findById(id);
    }

    @Override
    public Page<Review> getReviewList(Long MemberId, Integer page) {
        Member member = memberRepository.findById(MemberId).get();

        Page<Review> MemberPage = reviewRepository.findAllByMember(member, PageRequest.of(page, 10));
        return MemberPage;
    }

    @Override
    public Page<Mission> getMissionList(Long MemberId, Integer page) {
        Member member = memberRepository.findById(MemberId).get();

        Page<Mission> MemberPage = missionRepository.findAllByMember(member, PageRequest.of(page, 10));
        return MemberPage;
    }
}
