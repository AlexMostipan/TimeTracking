package dao.interfaces;

import model.ActivityRequest;
import model.enums.RequestType;

import java.util.List;

public interface ActivityRequestDao {

    public int create(ActivityRequest activityRequest);

    public boolean update(ActivityRequest activityRequest);

    public List<ActivityRequest> findAll();

    public boolean delete(int id);

    public List<ActivityRequest> findByUserId(int userId);

    public List<ActivityRequest> findByType(RequestType requestType);
}
