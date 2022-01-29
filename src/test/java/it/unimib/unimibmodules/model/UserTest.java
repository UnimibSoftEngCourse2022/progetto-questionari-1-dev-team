package it.unimib.unimibmodules.model;

import it.unimib.unimibmodules.exception.EmptyFieldException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = User.class)
public class UserTest {

    @Test
    public void testUserWithEmptyUsername() {
        try {
            User user = new User();
            user.setEmail("test@gmail.com");
            user.setPassword("pass");
            user.setName("name");
            user.setSurname("surname");
            user.setUsername("");
            Assert.fail();
        } catch (EmptyFieldException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testUserWithNullUsername() {
        try {
            User user = new User();
            user.setEmail("test@gmail.com");
            user.setPassword("pass");
            user.setName("name");
            user.setSurname("surname");
            user.setUsername(null);
            Assert.fail();
        } catch (EmptyFieldException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testUserWithUsername() {
        try {
            User user = new User();
            user.setEmail("test@gmail.com");
            user.setPassword("pass");
            user.setName("name");
            user.setSurname("surname");
            user.setUsername("username");
        } catch (EmptyFieldException e) {
            Assert.fail();
            e.printStackTrace();
        }
    }
}
