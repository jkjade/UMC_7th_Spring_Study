package umc.spring.repository.ReviewRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.spring.domain.Review;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ReviewRepositoryImpl /*implements ReviewRepositoryCustom */{

//    private final ReviewRepository reviewRepository;
//
//    @Override
//    public List<Review> findReviewByStore(Long store_id) {
//        List<Review> filteredReviews = reviewRepository.findReviewByStore(store_id);
//
//        filteredReviews.forEach(review -> System.out.println("Reivew: " + review));
//
//        return filteredReviews;
//    }
}
