package be.abis.ordersandwich.repository;

import be.abis.ordersandwich.exception.SessionNotFoundException;
import be.abis.ordersandwich.model.Session;
import org.springframework.stereotype.Repository;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Repository
public class FileSessionRepository implements SessionRepository {

    private List<Session> sessions = new ArrayList<>();

    public FileSessionRepository() {
        loadSessions();
    }

    public List<Session> loadSessions(){
        try (BufferedReader reader = new BufferedReader(new FileReader("be/abis/ordersandwich/resource/SessionRepository.csv"))){
            String line;
            while ((line = reader.readLine()) != null) {
                String[] attributes = line.split(";");
                Session session = new Session(attributes[0], attributes[1], attributes[2]);
                this.sessions.add(session);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } return this.sessions;
    }

    @Override
    public Session findMostRecentCourse(String courseName) throws SessionNotFoundException {
        return getSessions().stream()
                .filter(course -> courseName.equals(course.getName()))
                .sorted(Collections.reverseOrder())
                .findFirst().orElseThrow(SessionNotFoundException::new);
    }

    @Override
    public void addCourse(String name, String startDate, String endDate) {
        try (PrintWriter writer = new PrintWriter(new FileWriter("be/abis/ordersandwich/resource/SessionRepository.csv", true))){
            writer.append(name + ";" + startDate + ";" + endDate + ";\n");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }


    // getset

    @Override
    public List<Session> getSessions() {
        return sessions;
    }

}
