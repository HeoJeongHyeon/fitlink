package my.fitlink.domain.user.entity;

import my.fitlink.domain.user.dto.request.UserRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class UserTest {

    @Autowired
    private TestEntityManager em;

    @Test
    void userCreate() {

        /* given */
        UserRequest.SignUp request = new UserRequest.SignUp(
                "heojeonghyeon",
                "123345",
                "hello@naver.com"
        );
        

        /* when */
        User user = User.toUser(request);
        User savedUser = em.persist(user);
        em.flush();
        em.clear();

        /* then */
        User foundUser = em.find(User.class, savedUser.getId());
        assertThat(foundUser.getNickname()).isEqualTo("heojeonghyeon");
        assertThat(foundUser.getEmail()).isEqualTo("hello@naver.com");
        assertThat(foundUser.getRole()).isEqualTo(Role.USER);

    }
}
