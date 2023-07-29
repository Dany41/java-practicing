package org.practicalunittesting;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

class UserListTest {

    UserList userList;

    User userA = mock(User.class);
    User userB = mock(User.class);

    @BeforeEach
    void setUp() {
        userList = new UserList();
    }

    @Test
    void emptyListOfUsersIsReturnedIfNoUsersHaveBeenAdded() {
        assertThat(userList.getUsers())
                .isNotNull()
                .isEmpty();
    }

    @Test
    void exactlyOneUserIsReturnedIfOnlyOneHaveBeenAdded() {
        userList.addUser(userA);
        assertThat(userList.getUsers())
                .isNotNull()
                .isNotEmpty()
                .hasSize(1)
                .contains(userA);
    }

    @Test
    void twoUsersAreReturnedIfTwoHaveBeenAdded() {
        userList.addUser(userA);
        userList.addUser(userB);
        assertThat(userList.getUsers())
                .isNotNull()
                .isNotEmpty()
                .hasSize(2)
                .contains(userA, userB);
    }

}