package model;

import model.enums.RequestType;

import java.util.Objects;

public class ActivityRequest {
    private int id;
    private User user;
    private RequestType requestType;
    private String creationDate;

    public ActivityRequest() {
    }

    public ActivityRequest(int id, User user, RequestType requestType, String creationDate) {
        this.id = id;
        this.user = user;
        this.requestType = requestType;
        this.creationDate = creationDate;
    }

    public ActivityRequest(User user, RequestType requestType, String creationDate) {
        this.user = user;
        this.requestType = requestType;
        this.creationDate = creationDate;
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

    public RequestType getRequestType() {
        return requestType;
    }

    public void setRequestType(RequestType requestType) {
        this.requestType = requestType;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ActivityRequest that = (ActivityRequest) o;
        return id == that.id &&
                Objects.equals(user, that.user) &&
                requestType == that.requestType &&
                Objects.equals(creationDate, that.creationDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, user, requestType, creationDate);
    }

    @Override
    public String toString() {
        return "ActivityRequest{" +
                "id=" + id +
                ", user=" + user +
                ", requestType=" + requestType +
                ", creationDate='" + creationDate + '\'' +
                '}';
    }
}