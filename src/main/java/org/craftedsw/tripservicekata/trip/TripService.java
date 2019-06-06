package org.craftedsw.tripservicekata.trip;

import org.craftedsw.tripservicekata.exception.UserNotLoggedInException;
import org.craftedsw.tripservicekata.user.User;
import org.craftedsw.tripservicekata.user.UserSession;

import java.util.ArrayList;
import java.util.List;

public class TripService {

    public List<Trip> getTripsByUser(User user, User loggedInUser) throws UserNotLoggedInException {
        User loggedUser = loggedInUser;
        if (loggedUser == null) {
            throw new UserNotLoggedInException();
        }
        List<Trip> tripList = new ArrayList<Trip>();
        if (user.isFriend(loggedUser)) {
            tripList = findTripsBy(user);
        }
        return tripList;
    }

    protected List<Trip> findTripsBy(User user) {
        return TripDAO.findTripsByUser(user);
    }

    protected User obtainLoggedUser() {
        return UserSession.getInstance().getLoggedUser();
    }

}
