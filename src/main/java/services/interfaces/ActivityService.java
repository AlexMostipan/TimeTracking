package services.interfaces;

import model.Activity;
import model.ActivityType;
import model.User;
import model.enums.ActivityStatus;


import java.util.List;

public interface ActivityService {
    public List<Activity> getActivityByUserId(int userId) ;
    public List<Activity> getAllActivities() ;
    public void create(User user, ActivityType activityType , String description);
    public void delete (int userId);
    public void finishActivity (int id);
    public List<Activity> getActivityByStatusAndUserId(ActivityStatus activityStatus ,int userId);
    public List<Activity> getActivityByStatus(ActivityStatus activityStatus);
}

