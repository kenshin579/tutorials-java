package kr.pe.advenoh.repository;

import kr.pe.advenoh.model.CelluarPhone;
import kr.pe.advenoh.model.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
@RunWith(SpringRunner.class)
@DataJpaTest
public class UserRepositoryTest {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PhoneRepository phoneRepository;

    @Test
    public void save_user_phone() {
        CelluarPhone celluarPhone = CelluarPhone.builder()
                .model("android")
                .phoneNumber("010-2342-5234")
                .build();
        phoneRepository.save(celluarPhone);

        User user = User.builder()
                .name("Frank")
                .email("sdf@sdf.com")
                .username("id1234")
                .password("1234")
                .build();
        user.setCelluarPhone(celluarPhone);

        userRepository.save(user);

        List<User> users = userRepository.findAll();
        assertThat(users.get(0).getName()).isEqualTo("Frank");
        assertThat(users.get(0).getCelluarPhone().getPhoneNumber()).isEqualTo("010-2342-5234");
    }
}