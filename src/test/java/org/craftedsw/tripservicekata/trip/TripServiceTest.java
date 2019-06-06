package org.craftedsw.tripservicekata.trip;

import org.craftedsw.tripservicekata.exception.UserNotLoggedInException;
import org.craftedsw.tripservicekata.user.User;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * TODO LIST:
 * + No hay un usuario loggeado
 * + El usuario logeado y el objetivo son amigos
 * + El usuario logeado y el objetivo no son amigos
 * - El usuario logeado y el objetivo son amigo y el objetivo tiene viajes
 */
public class TripServiceTest {

    @Test(expected = UserNotLoggedInException.class)
    public void should_throw_an_exception_when_user_is_not_logged() {
        TripService sut = new TestableTripService(null);
        sut.getTripsByUser(null);
    }

    @Test
    public void should_return_any_trips_when_user_is_logged_and_are_not_friends() {
        User aUserNotFriend = new User();

        List<Trip> tripsByUser = new TestableTripService(new User()).getTripsByUser(aUserNotFriend);

        assertThat(tripsByUser.size(), is(0));
    }

    @Test
    public void should_return_trips_when_users_are_friends() {
        User aUserNotFriendWithTrips = new User();
        aUserNotFriendWithTrips.addTrip(new Trip());

        List<Trip> tripsByUser = new TestableTripService(new User()).getTripsByUser(aUserNotFriendWithTrips);

        assertThat(tripsByUser.size(), is(0));
    }

    private class TestableTripService extends TripService {

        private User loggedUserReturn;

        public TestableTripService(User loggedUserReturn) {
            this.loggedUserReturn = loggedUserReturn;
        }

        @Override
        protected User obtainLoggedUser() {
            return this.loggedUserReturn;
        }
    }
}
