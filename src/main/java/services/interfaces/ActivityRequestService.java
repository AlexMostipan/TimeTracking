package services.interfaces;


import model.ActivityRequest;
import model.User;
import model.enums.RequestType;

import java.util.List;

public interface ActivityRequestService {
    public List<ActivityRequest> getAllRequest();

    void create(User user, RequestType requestType);
}


