package ua.kiev.prog;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AdvRepository extends JpaRepository<Advertisement, Long> {
    List<Advertisement> findByShortDescLike(String pattern);
}