package org.practicalunittesting;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class UserServiceImplTest {

    public static final String ANY_MD5_STRING = "321";
    public static final String ANY_STRING = "123";
    User user = new User(ANY_STRING);
    SecurityService securityService = mock(SecurityService.class);
    UserDAO userDAO = mock(UserDAO.class);
    UserServiceImpl userService = new UserServiceImpl(userDAO, securityService);

    @Test
    void passwordIsSetToUser() throws Exception {
        when(securityService.md5(any())).thenReturn(ANY_MD5_STRING);

        userService.assignPassword(user);

        assertThat(user.getPassword()).isEqualTo(ANY_MD5_STRING);
    }

    @Test
    void userIsUpdatedWhenPasswordIsAssigned() throws Exception {
        userService.assignPassword(user);
        verify(userDAO).updateUser(user);
    }

}