package services.interfaces;

import model.ActivityType;

import java.util.List;

public interface ActivityTypeService {
    List<ActivityType> getAllActivityTypes();

    ActivityType findByTitle(String title);

    ActivityType findById(int id);
}
