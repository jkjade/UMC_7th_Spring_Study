package umc.spring.repository.MemberRepoository;

import umc.spring.domain.Member;


public interface MemberRepositoryCustom {
    Member findMemberById(Long id);
}
