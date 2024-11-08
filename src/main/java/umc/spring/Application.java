package umc.spring;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import umc.spring.domain.Member;
import umc.spring.service.MemberService.MemberQueryService;
import umc.spring.service.MissionService.MissionQueryService;
import umc.spring.service.ReviewService.ReviewQueryService;
import umc.spring.service.StoreService.StoreQueryService;

import java.util.Optional;

@SpringBootApplication
@EnableJpaAuditing
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);

	}

	@Bean
	public CommandLineRunner run(ApplicationContext context) {
		return args -> {
			StoreQueryService storeService = context.getBean(StoreQueryService.class);

			// 파라미터 값 설정
			String name = "요아정";
			Float score = 4.0f;

			Long id = 1L;
			String region = "서울";

			// 쿼리 메서드 호출 및 쿼리 문자열과 파라미터 출력
			System.out.println("Executing findStoresByNameAndScore with parameters:");
			System.out.println("Name: " + name);
			System.out.println("Score: " + score);

			storeService.findStoresByNameAndScore(name, score)
					.forEach(System.out::println);


			MemberQueryService memberService = context.getBean(MemberQueryService.class);


			System.out.println("id: " + id);

			System.out.println("======================================================== 마이페이지 ========================================================");

			// 마이페이지
			Optional<Member> member = memberService.findMember(id);
			if (member.isPresent()) {
				Member foundMember = member.get();
				System.out.println("멤버 이름 = " + foundMember.getName() + ", 포인트 = " + foundMember.getPoint() + ", 이메일 = " + foundMember.getEmail());
			} else {
				System.out.println("멤버를 찾을수 없음");
			}

			System.out.println("======================================================== complete ========================================================");



			// complete 미션 조회
			MissionQueryService missionService = context.getBean(MissionQueryService.class);
			missionService.findCompleteMissionsByRegionAndMember(id, region)
					.forEach(mission -> {
						System.out.printf("미션 ID: %d, 보상: %d, 기한: %s, 상세: %s%n",
								mission.getId(),
								mission.getReward(),
								mission.getDeadline(),
								mission.getMissionSpec());
					});

			System.out.println("======================================================== challenging ========================================================");

			// challenging 미션 조회
			missionService.findChallengingByRegionAndMember(id, region)
					.forEach(mission -> {
						System.out.printf("미션 ID: %d, 보상: %d, 기한: %s, 상세: %s%n",
								mission.getId(),
								mission.getReward(),
								mission.getDeadline(),
								mission.getMissionSpec());
					});

			Long store_id = 1L;

			System.out.println("======================================================== 홈 화면 ========================================================");

			missionService.findByRegion(region)
					.forEach(mission -> {
						System.out.printf("미션 ID: %d, 보상: %d, 기한: %s, 상세: %s%n",
								mission.getId(),
								mission.getReward(),
								mission.getDeadline(),
								mission.getMissionSpec());
					});

//			// 가게 리뷰 조회
//			ReviewQueryService reviewService = context.getBean(ReviewQueryService.class);
//			reviewService.findReviewByStore(store_id)
//					.forEach(review -> {
//						System.out.printf("리뷰 ID: %d, 내용: %s 작성자 이름: %s",
//								review.getId(),
//								review.getBody(),
//								review.getMember().getName());
//					});

		};
	}

}
