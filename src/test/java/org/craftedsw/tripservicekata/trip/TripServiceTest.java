package org.craftedsw.tripservicekata.trip;

import org.craftedsw.tripservicekata.exception.UserNotLoggedInException;
import org.craftedsw.tripservicekata.user.User;
import org.junit.Test;

import java.util.LinkedList;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertNotNull;
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
        sut.getTripsByUser(null, null);
    }

    @Test
    public void should_return_any_trips_when_user_is_logged_and_are_not_friends() {
        User aUserNotFriend = new User();

        List<Trip> tripsByUser = new TestableTripService(new User()).getTripsByUser(aUserNotFriend, null);

        assertThat(tripsByUser.size(), is(0));
    }

    @Test
    public void should_return_trips_when_users_are_friends() {
        User aUserNotFriendWithTrips = new User();
        aUserNotFriendWithTrips.addTrip(new Trip());

        List<Trip> tripsByUser = new TestableTripService(new User()).getTripsByUser(aUserNotFriendWithTrips, null);

        assertThat(tripsByUser.size(), is(0));
    }


    @Test
    public void should_return_user_trip_list_when_logged_userWithTrips_and_user_are_friend() {
        User loggedUser = new User();
        User userWithTrips = new User();
        userWithTrips.addTrip(new Trip());
        userWithTrips.addFriend(loggedUser);
        TripService sut = new TestableTripService(loggedUser);

        List<Trip> tripsByUser = sut.getTripsByUser(userWithTrips, null);

        assertNotNull(tripsByUser);
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

        @Override
        protected List<Trip> findTripsBy(User user) {
            List<Trip> fake = new LinkedList<>();
            fake.add(new Trip());
            return fake;
        }
    }
}
