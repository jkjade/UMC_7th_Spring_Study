package umc.spring.service.StoreService;

import umc.spring.domain.Member;
import umc.spring.domain.Region;
import umc.spring.domain.Store;
import umc.spring.web.dto.MemberRequestDTO;
import umc.spring.web.dto.StoreRequestDTO;

public interface StoreCommandService {

    Store joinStore(StoreRequestDTO.JoinDto request);
}
