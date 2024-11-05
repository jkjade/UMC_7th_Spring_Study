package umc.spring.repository.MissionRepository;

import umc.spring.domain.Mission;
import umc.spring.domain.enums.MissionStatus;

import java.util.List;

public interface MissionRepositoryCustom {
    List<Mission> findChallengingMissionsByRegion(Long member_id, String region);

    List<Mission> findCompleteMissionsByRegion(Long member_id, String region);

}
