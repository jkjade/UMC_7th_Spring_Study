package umc.spring.repository.MissionRepository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import umc.spring.domain.Mission;
import umc.spring.domain.QMission;
import umc.spring.domain.enums.MissionStatus;

import java.util.List;

import static umc.spring.domain.QStore.store;
import static umc.spring.domain.mapping.QMemberMission.memberMission;

@Repository
@RequiredArgsConstructor
public class MissionRepositoryImpl implements MissionRepositoryCustom{
    private final JPAQueryFactory jpaQueryFactory;
    private final QMission mission = QMission.mission;

    @Override
    public List<Mission> findChallengingByRegionAndMember(Long member_id, String region) {
        BooleanBuilder predicate = new BooleanBuilder();

        if (member_id != null) {
            predicate.and(memberMission.member.id.eq(member_id));
        }

        // status가 CHALLENGING인 조건 추가
        predicate.and(memberMission.status.eq(MissionStatus.CHALLENGING));

        // region에 따라 필터링 (region이 null이 아닐 경우)
        if (region != null) {
            predicate.and(mission.store.region.name.eq(region));
        }

        return jpaQueryFactory
                .select(mission)
                .from(memberMission)
                .join(memberMission.mission, mission) // MemberMission과 Mission을 조인
                .where(predicate)
                .fetch();
    }

    @Override
    public List<Mission> findCompleteByAndMember(Long member_id, String region) {
        BooleanBuilder predicate = new BooleanBuilder();

        if (member_id != null) {
            predicate.and(memberMission.member.id.eq(member_id));
        }

        // status가 COMPLETE 조건 추가
        predicate.and(memberMission.status.eq(MissionStatus.COMPLETE));

        // region에 따라 필터링 (region이 null이 아닐 경우)
        if (region != null) {
            predicate.and(mission.store.region.name.eq(region));
        }

        return jpaQueryFactory
                .select(mission)
                .from(memberMission)
                .join(memberMission.mission, mission) // MemberMission과 Mission을 조인
                .where(predicate)
                .fetch();
    }

    @Override
    public List<Mission> findByRegion(String region) {
        BooleanBuilder predicate = new BooleanBuilder();

        // region에 따라 필터링 (region이 null이 아닐 경우)
        if (region != null) {
            predicate.and(mission.store.region.name.eq(region));
        }

        return jpaQueryFactory
                .selectFrom(mission)
                .join(mission.store, store).fetchJoin()         // store와 함께 조회
                .join(store.region).fetchJoin()
                .where(predicate)
                .fetch();
    }
}
