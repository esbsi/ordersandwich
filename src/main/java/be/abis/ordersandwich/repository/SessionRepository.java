package be.abis.ordersandwich.repository;

import be.abis.ordersandwich.exception.SessionNotFoundException;
import be.abis.ordersandwich.model.Session;

import java.util.List;

public interface SessionRepository {

    void addCourse(String name, String startDate, String endDate);
    List<Session> getCourses();
    Session findMostRecentCourse(String courseName) throws SessionNotFoundException;

}
