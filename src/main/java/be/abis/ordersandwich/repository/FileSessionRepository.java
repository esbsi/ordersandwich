package be.abis.ordersandwich.repository;

import be.abis.ordersandwich.exception.SessionNotFoundException;
import be.abis.ordersandwich.model.Session;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Repository
public class FileSessionRepository implements SessionRepository {

    private List<Session> sessions = new ArrayList<>();
    private String fileName = "src/main/resources/SessionRepository.csv";

    public FileSessionRepository() {
        loadSessions();
    }

    public void loadSessions(){
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))){
            String line;
            while ((line = reader.readLine()) != null) {
                String[] attributes = line.split(";");
                Session session = new Session(attributes[0], attributes[1], attributes[2]);
                this.sessions.add(session);
            }
        } catch (IOException e) {
            System.out.println("FileSessionRepository csv file not found.");
            throw new RuntimeException(e);
        }
    }






    @Override
    public Session findMostRecentSession(String sessionName) throws SessionNotFoundException {
        return sessions.stream()
                .filter(session -> sessionName.equals(session.getName()))
                .sorted(Collections.reverseOrder())
                .findFirst().orElseThrow(SessionNotFoundException::new);
    }

    @Override
    public void addSession(String name, String startDate, String endDate) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(fileName, true))){
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
