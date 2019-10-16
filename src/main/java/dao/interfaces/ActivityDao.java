package dao.interfaces;

import model.Activity;
import model.enums.ActivityStatus;


import java.util.List;


public interface ActivityDao {
    public int create(Activity activity);

    public boolean update(Activity activity);

    public boolean delete(int id);

    public List<Activity> findAll();

    public List<Activity> getActivityByUserId(int id);

    public List<Activity> getActivityByStatus(ActivityStatus status);

    public boolean finishActivity(int id, ActivityStatus status, String dateFinish);

    public List<Activity> getActivityByStatusAndUserId(ActivityStatus status, int userId);
}
