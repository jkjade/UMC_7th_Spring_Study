package umc.spring.service.MissionService;

import umc.spring.domain.Mission;

import java.util.List;
import java.util.Optional;

public interface MissionQueryService {

    Optional<Mission> findMission(Long id);
    List<Mission> findCompleteMissionsByRegion(Long member_id, String region);
    List<Mission> findChallengingMissionsByRegion(Long member_id, String region);

}
