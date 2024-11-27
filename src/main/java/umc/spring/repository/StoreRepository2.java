package umc.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import umc.spring.domain.Store;

public interface StoreRepository2 extends JpaRepository<Store, Long> {
}
