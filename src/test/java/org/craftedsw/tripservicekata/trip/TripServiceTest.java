package org.craftedsw.tripservicekata.trip;

import org.craftedsw.tripservicekata.exception.UserNotLoggedInException;
import org.craftedsw.tripservicekata.user.User;
import org.junit.Test;

/**
 * TODO LIST:
 * + No hay un usuario loggeado
 * - El usuario logeado y el objetivo son amigos
 * - El usuario logeado y el objetivo no son amigos
 * - El usuario logeado y el objetivo son amigo y el objetivo tiene viajes
 */
public class TripServiceTest {

    @Test(expected = UserNotLoggedInException.class)
    public void should_throw_an_exception_when_user_is_not_logged() {
        TripService sut = new TestableTripService();
        sut.getTripsByUser(null);
    }

    private class TestableTripService extends TripService {
        @Override
        protected User obtainLoggedUser() {
            return null;
        }
    }
}
