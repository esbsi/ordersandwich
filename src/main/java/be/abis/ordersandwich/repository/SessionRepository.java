package be.abis.ordersandwich.repository;

import be.abis.ordersandwich.exception.SessionNotFoundException;
import be.abis.ordersandwich.model.Session;

import java.util.List;

public interface SessionRepository {

    List<Session> getCourses();
    Session findMostRecentCourse(String courseName) throws SessionNotFoundException;
    void addCourse(String name, String startDate, String endDate);

}
