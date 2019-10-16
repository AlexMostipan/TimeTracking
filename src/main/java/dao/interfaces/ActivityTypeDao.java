package dao.interfaces;


import model.ActivityType;

import java.util.List;

public interface ActivityTypeDao {
    int create(ActivityType activityType);

    boolean update(ActivityType activityType);

    boolean delete(int id);

    List<ActivityType> findAll();

    ActivityType findByTitle(String title);

    ActivityType findById(int id);
}

