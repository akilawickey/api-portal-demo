package assesment.apiportal.publisher.demo.services.impl;

import assesment.apiportal.publisher.demo.models.User;
import assesment.apiportal.publisher.demo.repositories.UserRepository;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import javax.validation.constraints.AssertTrue;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@WebMvcTest
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = UserDetailsServiceImpl.class)
class UserDetailsServiceImplTest {

    @MockBean
    UserRepository userRepository;

    @Autowired
    UserDetailsServiceImpl userDetailsServiceimpl;

    @BeforeEach
    void setUp() {
        User dummyUser = new User();
        when(userRepository.findByUsername(anyString())).thenReturn(java.util.Optional.of(dummyUser));
    }

    @Test
    void loadUserByUsername() {
        UserDetails user = userDetailsServiceimpl.loadUserByUsername("danny");

        Assert.assertTrue(user != null);
    }
}