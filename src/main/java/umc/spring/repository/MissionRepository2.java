package umc.spring.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import umc.spring.domain.Member;
import umc.spring.domain.Mission;
import umc.spring.domain.Store;

public interface MissionRepository2 extends JpaRepository<Mission, Long> {

    Page<Mission> findAllByStore(Store store, PageRequest pageRequest);

    @Query("SELECT m FROM Mission m WHERE m.id IN (SELECT mm.mission.id FROM MemberMission mm WHERE mm.member = :member)")
    Page<Mission> findAllByMember(@Param("member") Member member, PageRequest pageRequest);
}
