package kr.pe.advenoh.repository;

import kr.pe.advenoh.model.CellularPhone;
import kr.pe.advenoh.model.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
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

    @PersistenceContext
    private EntityManager em;

    @Test
    public void save_user_phone() {
        CellularPhone cellularPhone = CellularPhone.builder()
                .model("android")
                .phoneNumber("010-2342-5234")
                .build();

        User user = User.builder()
                .name("Frank")
                .email("sdf@sdf.com")
                .username("id1234")
                .password("1234")
                .build();
        user.setCellularPhone(cellularPhone);

        cellularPhone.setUser(user);
        userRepository.save(user);
        phoneRepository.save(cellularPhone);

        List<User> users = userRepository.findAll();
        assertThat(users.get(0).getName()).isEqualTo("Frank");
        assertThat(users.get(0).getCellularPhone().getPhoneNumber()).isEqualTo("010-2342-5234");

        assertThat(cellularPhone.getUser().getName()).isEqualTo("Frank");
    }


    @Test
    public void 일대일관계에서_지연로딩_테스트() {
        saveUserWithPhones(1, 1);

        List<User> users = userRepository.findAll();
        assertThat(users.get(0).getCellularPhone().getModel()).startsWith("android"); //지연로딩으로 동작한다

//        List<CellularPhone> phones = phoneRepository.findAll(); //User도 같이 조회된다.
    }

    private void saveUserWithPhones(int maxUsers, int maxPhones) {
        CellularPhone cellularPhone;
        User user;

        for (int i = 1; i <= maxUsers; i++) {
            user = User.builder()
                    .name("Frank" + i)
                    .email("sdf@sdf.com" + i)
                    .username("id1234" + i)
                    .password("1234" + i)
                    .build();

            for (int j = 1; j <= maxPhones; j++) {
                cellularPhone = CellularPhone.builder()
                        .model("android" + i)
                        .phoneNumber("010-2342-523" + i)
                        .build();
                user.setCellularPhone(cellularPhone);
                cellularPhone.setUser(user);
                em.persist(user);
                em.persist(cellularPhone);
            }
        }
        em.flush();
        em.clear();
    }
}