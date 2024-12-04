package umc.spring.config.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import umc.spring.domain.Member;
import umc.spring.domain.enums.Gender;
import umc.spring.domain.enums.Role;
import umc.spring.repository.MemberRepoository.MemberRepository;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = super.loadUser(userRequest);

        Map<String, Object> attributes = oAuth2User.getAttributes();
        Map<String, Object> properties = (Map<String, Object>) attributes.get("properties");

        String nickname = (String) properties.get("nickname");
        String email = nickname + "@kakao.com"; // 임시 이메일 생성

        // 사용자 정보 저장 또는 업데이트
        Member member = saveOrUpdateUser(email, nickname);

        // 이메일을 Principal로 사용하기 위해 attributes 수정
        Map<String, Object> modifiedAttributes = new HashMap<>(attributes);
        modifiedAttributes.put("email", email);

        return new DefaultOAuth2User(
                oAuth2User.getAuthorities(),
                modifiedAttributes,
                "email"  // email Principal로 설정
        );
    }

    private Member saveOrUpdateUser(String email, String nickname) {
        Member member = memberRepository.findByEmail(email)
                .orElse(Member.builder()
                        .email(email)
                        .name(nickname)
                        .password(passwordEncoder.encode("OAUTH_USER_" + UUID.randomUUID()))
                        .gender(Gender.NONE)  // 기본값 설정
                        .address("소셜로그인")  // 기본값 설정
                        .specAddress("소셜로그인")  // 기본값 설정
                        .role(Role.USER)
                        .build());

        return memberRepository.save(member);
    }
}



//@Service
//@RequiredArgsConstructor
//public class CustomOAuth2UserService extends DefaultOAuth2UserService {
//
//    private final MemberRepository memberRepository;
//    private final PasswordEncoder passwordEncoder;
//
//    @Override
//    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
//        OAuth2User oAuth2User = super.loadUser(userRequest);
//
//        // 소셜 로그인 제공자 구분 (카카오, 네이버 등)
//        Map<String, Object> attributes = oAuth2User.getAttributes();
//        String registrationId = userRequest.getClientRegistration().getRegistrationId();
//
//        String email = "";
//        String nickname = "";
//
//        if ("kakao".equals(registrationId)) {
//            Map<String, Object> properties = (Map<String, Object>) attributes.get("properties");
//            nickname = (String) properties.get("nickname");
//            email = nickname + "@kakao.com"; // 임시 이메일 생성
//        } else if ("naver".equals(registrationId)) {
//            Map<String, Object> response = (Map<String, Object>) attributes.get("response");
//            nickname = (String) response.get("name");
//            email = nickname + "@naver.com";
//        }
//
//        // 사용자 정보 저장 또는 업데이트
//        Member member = saveOrUpdateUser(email, nickname);
//
//        // 이메일을 Principal로 사용하기 위해 attributes 수정
//        Map<String, Object> modifiedAttributes = new HashMap<>(attributes);
//        modifiedAttributes.put("email", email);
//
//        return new DefaultOAuth2User(
//                oAuth2User.getAuthorities(),
//                modifiedAttributes,
//                "email"  // email Principal로 설정
//        );
//    }
//
//    private Member saveOrUpdateUser(String email, String nickname) {
//        Member member = memberRepository.findByEmail(email)
//                .orElse(Member.builder()
//                        .email(email)
//                        .name(nickname)
//                        .password(passwordEncoder.encode("OAUTH_USER_" + UUID.randomUUID()))
//                        .gender(Gender.NONE)  // 기본값 설정
//                        .address("소셜로그인")  // 기본값 설정
//                        .specAddress("소셜로그인")  // 기본값 설정
//                        .role(Role.USER)
//                        .build());
//
//        return memberRepository.save(member);
//    }
//}

//@Service
//@RequiredArgsConstructor
//public class CustomOAuth2UserService extends DefaultOAuth2UserService {
//
//    private final MemberRepository memberRepository;
//    private final PasswordEncoder passwordEncoder;
//
//    @Override
//    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
//        OAuth2User oAuth2User = super.loadUser(userRequest);
//
//        Map<String, Object> attributes = oAuth2User.getAttributes();
//        String registrationId = userRequest.getClientRegistration().getRegistrationId();
//
//        if ("kakao".equals(registrationId)) {
//            // 카카오 로그인 처리
//            return processKakaoUser(attributes);
//        } else if ("naver".equals(registrationId)) {
//            // 네이버 로그인 처리
//            return processNaverUser(attributes);
//        }
//
//        return oAuth2User;
//    }
//
//    private OAuth2User processKakaoUser(Map<String, Object> attributes) {
//        Map<String, Object> properties = (Map<String, Object>) attributes.get("properties");
//
//        String nickname = (String) properties.get("nickname");
//        String email = nickname + "@kakao.com"; // 임시 이메일 생성
//
//        // 사용자 정보 저장 또는 업데이트
//        Member member = saveOrUpdateUser(email, nickname);
//
//        // 이메일을 Principal로 사용하기 위해 attributes 수정
//        Map<String, Object> modifiedAttributes = new HashMap<>(attributes);
//        modifiedAttributes.put("email", email);
//
//        return new DefaultOAuth2User(
//                oAuth2User.getAuthorities(),
//                modifiedAttributes,
//                "email"  // email Principal로 설정
//        );
//    }
//
//    private OAuth2User processNaverUser(Map<String, Object> attributes) {
//        Map<String, Object> response = (Map<String, Object>) attributes.get("response");
//
//        String nickname = (String) response.get("name");
//        String email = (String) response.get("email");
//
//        // 사용자 정보 저장 또는 업데이트
//        Member member = saveOrUpdateUser(email, nickname);
//
//        // 이메일을 Principal로 사용하기 위해 attributes 수정
//        Map<String, Object> modifiedAttributes = new HashMap<>(attributes);
//        modifiedAttributes.put("email", email);
//
//        return new DefaultOAuth2User(
//                oAuth2User.getAuthorities(),
//                modifiedAttributes,
//                "email"  // email Principal로 설정
//        );
//    }
//
//    private Member saveOrUpdateUser(String email, String nickname) {
//        Member member = memberRepository.findByEmail(email)
//                .orElse(Member.builder()
//                        .email(email)
//                        .name(nickname)
//                        .password(passwordEncoder.encode("OAUTH_USER_" + UUID.randomUUID()))
//                        .gender(Gender.NONE)  // 기본값 설정
//                        .address("소셜로그인")  // 기본값 설정
//                        .specAddress("소셜로그인")  // 기본값 설정
//                        .role(Role.USER)
//                        .build());
//
//        return memberRepository.save(member);
//    }
//}
