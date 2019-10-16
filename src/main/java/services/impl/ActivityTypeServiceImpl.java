package services.impl;

import dao.TransactionManager;
import dao.interfaces.ActivityTypeDao;
import model.ActivityType;
import services.interfaces.ActivityTypeService;

import java.util.List;

public class ActivityTypeServiceImpl implements ActivityTypeService {
    private ActivityTypeDao activityTypeDao;
    private TransactionManager transactionManager;

    public ActivityTypeServiceImpl(ActivityTypeDao activityTypeDao, TransactionManager transactionManager) {
        this.activityTypeDao = activityTypeDao;
        this.transactionManager = transactionManager;
    }

    @Override
    public List<ActivityType> getAllActivityTypes() {
        try {
            return activityTypeDao.findAll();
        } finally {
            transactionManager.closeConnection();
        }
    }

    @Override
    public ActivityType findByTitle(String title) {
        try {
            return activityTypeDao.findByTitle(title);
        } finally {
            transactionManager.closeConnection();
        }
    }

    @Override
    public ActivityType findById(int id) {
        try {
            return activityTypeDao.findById(id);
        } finally {
            transactionManager.closeConnection();
        }
    }
}
