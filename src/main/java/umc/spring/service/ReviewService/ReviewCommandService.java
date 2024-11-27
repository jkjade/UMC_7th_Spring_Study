package umc.spring.service.ReviewService;

import umc.spring.domain.Review;
import umc.spring.domain.Store;
import umc.spring.web.dto.ReviewRequestDTO;
import umc.spring.web.dto.StoreRequestDTO;

public interface ReviewCommandService {

    Review joinReview(ReviewRequestDTO.JoinDto request);
}
