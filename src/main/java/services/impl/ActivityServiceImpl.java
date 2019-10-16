package services.impl;

import dao.TransactionManager;
import dao.interfaces.ActivityDao;
import model.Activity;
import model.ActivityType;
import model.User;
import model.enums.ActivityStatus;
import services.interfaces.ActivityService;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class ActivityServiceImpl implements ActivityService {
    private ActivityDao activityDao;
    private TransactionManager transactionManager;

    public ActivityServiceImpl(ActivityDao activityDao, TransactionManager transactionManager) {
        this.activityDao = activityDao;
        this.transactionManager = transactionManager;
    }

    @Override
    public List<Activity> getActivityByUserId(int userId) {
        transactionManager.getConnection();
        try {
            return activityDao.getActivityByUserId(userId);
        } finally {
            transactionManager.closeConnection();
        }
    }

    @Override
    public List<Activity> getAllActivities() {
        transactionManager.getConnection();
        try {
            return activityDao.findAll();
        } finally {
            transactionManager.closeConnection();
        }
    }

    @Override
    public void create(User user, ActivityType activityType, String description) {
        transactionManager.getConnection();

        try {
            Activity activity = new Activity(user, activityType, description, ActivityStatus.DURING, new SimpleDateFormat("HH:mm yyyy.MM.dd").format(new Date()));
            activityDao.create(activity);
        } finally {
            transactionManager.closeConnection();
        }
    }

    @Override
    public void delete(int id) {
        try {
            activityDao.delete(id);
        } finally {
            transactionManager.closeConnection();
        }
    }

    @Override
    public void finishActivity(int activityId) {
        transactionManager.getConnection();

        try {
            activityDao.finishActivity(activityId, ActivityStatus.FINISHED, new SimpleDateFormat("HH:mm yyyy.MM.dd").format(new Date()));
        } finally {
            transactionManager.closeConnection();
        }
    }

    @Override
    public List<Activity> getActivityByStatusAndUserId(ActivityStatus activityStatus, int userId) {
        transactionManager.getConnection();

        try {
            return activityDao.getActivityByStatusAndUserId(activityStatus, userId);
        } finally {
            transactionManager.closeConnection();
        }
    }

    @Override
    public List<Activity> getActivityByStatus(ActivityStatus activityStatus) {
        transactionManager.getConnection();

        try {
            return activityDao.getActivityByStatus(activityStatus);
        } finally {
            transactionManager.closeConnection();
        }
    }
}
