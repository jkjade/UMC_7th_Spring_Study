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
    public List<Mission> findCompleteMissionsByRegionAndMember(Long member_id, String region) {
        List<Mission> filteredMissions = missionRepository.findCompleteByAndMember(member_id, region);

        filteredMissions.forEach(mission -> System.out.println("CompleteMission: " + mission));

        return filteredMissions;
    }

    @Override
    public List<Mission> findChallengingByRegionAndMember(Long member_id, String region) {
        List<Mission> filteredMissions = missionRepository.findChallengingByRegionAndMember(member_id, region);

        filteredMissions.forEach(mission -> System.out.println("ChallengingMission: " + mission));

        return filteredMissions;
    }

    @Override
    public List<Mission> findByRegion(String region) {
        List<Mission> filteredMissions = missionRepository.findByRegion(region);

        filteredMissions.forEach(mission -> System.out.println("RegionMission: " + mission));

        return filteredMissions;
    }
}
