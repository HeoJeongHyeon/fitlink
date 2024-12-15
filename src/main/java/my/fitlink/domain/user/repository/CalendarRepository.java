package my.fitlink.domain.user.repository;
import my.fitlink.domain.user.entity.Calendar;
import my.fitlink.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CalendarRepository extends JpaRepository<Calendar,Long> {

    Calendar findByUserId(Long userId);

}
