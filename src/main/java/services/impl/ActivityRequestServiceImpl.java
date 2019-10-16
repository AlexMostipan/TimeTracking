package services.impl;

import dao.TransactionManager;
import dao.interfaces.ActivityRequestDao;
import model.ActivityRequest;
import model.User;
import model.enums.RequestType;
import services.interfaces.ActivityRequestService;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class ActivityRequestServiceImpl implements ActivityRequestService {
    private ActivityRequestDao activityRequestDao;
    private TransactionManager transactionManager;

    public ActivityRequestServiceImpl(ActivityRequestDao activityRequestDao, TransactionManager transactionManager) {
        this.activityRequestDao = activityRequestDao;
        this.transactionManager = transactionManager;
    }

    @Override
    public List<ActivityRequest> getAllRequest() {
        transactionManager.getConnection();
        try {
            return activityRequestDao.findAll();
        } finally {
            transactionManager.closeConnection();
        }
    }

    @Override
    public void create(User user, RequestType requestType) {

        try {
            ActivityRequest activityRequest = new ActivityRequest(user, requestType, new SimpleDateFormat("HH:mm yyyy.MM.dd").format(new Date()));
            transactionManager.getConnection();
            activityRequestDao.create(activityRequest);
        } finally {
            transactionManager.closeConnection();
        }
    }
}
