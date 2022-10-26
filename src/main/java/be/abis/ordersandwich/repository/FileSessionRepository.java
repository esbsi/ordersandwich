package be.abis.ordersandwich.repository;

import be.abis.ordersandwich.exception.SessionNotFoundException;
import be.abis.ordersandwich.model.Session;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FileSessionRepository implements SessionRepository {

    private List<Session> sessionList = new ArrayList<>();

    public List<Session> getCourses(){
       // List<Session> sessionList = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("be/abis/ordersandwich/resource/SessionRepository.csv"))){
            String line;
            while ((line = reader.readLine()) != null) {
                String[] attributes = line.split(";");
                Session session = new Session(attributes[0], attributes[1], attributes[2]);
                this.sessionList.add(session);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } return this.sessionList;
    }

    public Session findMostRecentCourse(String courseName) throws SessionNotFoundException {
        return getCourses().stream()
                .filter(course -> courseName.equals(course.getName()))
                .sorted(Collections.reverseOrder())
                .findFirst().orElseThrow(SessionNotFoundException::new);
    }

    public void addCourse(String name, String startDate, String endDate) {
        try (PrintWriter writer = new PrintWriter(new FileWriter("be/abis/ordersandwich/resource/SessionRepository.csv", true))){
            writer.append(name + ";" + startDate + ";" + endDate + ";\n");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

}
