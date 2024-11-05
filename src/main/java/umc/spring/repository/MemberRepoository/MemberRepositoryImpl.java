package umc.spring.repository.MemberRepoository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import umc.spring.domain.Member;
import umc.spring.domain.QMember;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class MemberRepositoryImpl implements MemberRepositoryCustom{
    private final JPAQueryFactory jpaQueryFactory;
    private final QMember member = QMember.member;

    @Override
    public Member findMemberById(Long id) {
        return jpaQueryFactory
                .selectFrom(member)
                .where(member.id.eq(id))
                .fetchOne(); // 단일 결과를 반환
    }
}
