package umc.spring.service.MissionService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.spring.domain.Mission;
import umc.spring.repository.MissionRepository.MissionRepository;

import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MissionQueryServiceImpl implements MissionQueryService{

   private final MissionRepository missionRepository;

    @Override
    public Optional<Mission> findMission(Long id) {
        return missionRepository.findById(id);
    }

    @Override
    public List<Mission> findCompleteMissionsByRegion(Long member_id, String region) {
        List<Mission> filteredMissions = missionRepository.findCompleteMissionsByRegion(member_id, region);

        filteredMissions.forEach(mission -> System.out.println("CompleteMission: " + mission));

        return filteredMissions;
    }

    @Override
    public List<Mission> findChallengingMissionsByRegion(Long member_id, String region) {
        List<Mission> filteredMissions = missionRepository.findChallengingMissionsByRegion(member_id, region);

        filteredMissions.forEach(mission -> System.out.println("ChallengingMission: " + mission));

        return filteredMissions;
    }
}
