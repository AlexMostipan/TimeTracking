package model;

import model.enums.ActivityStatus;

import java.util.Objects;

public class Activity {
    private int id;
    private User user;
    private ActivityType activityType;
    private ActivityStatus status;
    private String description;
    private String dateStart;
    private String dateFinish;

    public Activity() {
    }


    public Activity(User user, ActivityType activityType, String description, ActivityStatus status, String dateStart) {
        this.user = user;
        this.activityType = activityType;
        this.description = description;
        this.status = status;
        this.dateStart = dateStart;
    }


    public Activity(int id, User user, ActivityType activityType, ActivityStatus status, String description, String dateStart) {
        this.id = id;
        this.user = user;
        this.activityType = activityType;
        this.status = status;
        this.description = description;
        this.dateStart = dateStart;
    }

    public Activity(int id, User user, ActivityType activityType, ActivityStatus status, String description, String dateStart, String dateFinish) {
        this.id = id;
        this.user = user;
        this.activityType = activityType;
        this.status = status;
        this.description = description;
        this.dateStart = dateStart;
        this.dateFinish = dateFinish;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public ActivityType getActivityType() {
        return activityType;
    }

    public void setActivityType(ActivityType activityType) {
        this.activityType = activityType;
    }

    public ActivityStatus getStatus() {
        return status;
    }

    public void setStatus(ActivityStatus status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDateStart() {
        return dateStart;
    }

    public void setDateStart(String dateStart) {
        this.dateStart = dateStart;
    }

    public String getDateFinish() {
        return dateFinish;
    }

    public void setDateFinish(String dateFinish) {
        this.dateFinish = dateFinish;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Activity activity = (Activity) o;
        return id == activity.id &&
                Objects.equals(user, activity.user) &&
                Objects.equals(activityType, activity.activityType) &&
                status == activity.status &&
                Objects.equals(description, activity.description) &&
                Objects.equals(dateStart, activity.dateStart) &&
                Objects.equals(dateFinish, activity.dateFinish);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, user, activityType, status, description, dateStart, dateFinish);
    }

    @Override
    public String toString() {
        return "Activity{" +
                "id=" + id +
                ", user=" + user +
                ", activityType=" + activityType +
                ", status=" + status +
                ", description='" + description + '\'' +
                ", dateStart='" + dateStart + '\'' +
                ", dateFinish='" + dateFinish + '\'' +
                '}';
    }
}