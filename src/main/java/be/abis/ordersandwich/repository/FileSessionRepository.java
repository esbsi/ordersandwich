package be.abis.ordersandwich.repository;

import be.abis.ordersandwich.exception.SessionNotFoundException;
import be.abis.ordersandwich.model.Session;
import org.springframework.stereotype.Repository;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

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
            int id = 0;
            while ((line = reader.readLine()) != null) {
                id++;
                String[] attributes = line.split(";");
                Session session = new Session(attributes[0], LocalDate.parse(attributes[1]), LocalDate.parse(attributes[2]));
                session.setId(id);
                this.sessions.add(session);
            }
        } catch (IOException e) {
            System.out.println("FileSessionRepository csv file not found.");
            throw new RuntimeException(e);
        }
    }


    // business

    // Redundant method?
    @Override
    public Session findMostRecentSession(String sessionName) throws SessionNotFoundException {
        return sessions.stream()
                .filter(session -> sessionName.equals(session.getName()))
                .sorted(Comparator.comparing(Session::getStartDate).reversed())
                .findFirst().orElseThrow(SessionNotFoundException::new);
    }

    @Override
    public Session findSession(int id) throws SessionNotFoundException {
        return sessions.stream()
                .filter(session -> id == session.getId())
                .findFirst().orElseThrow(SessionNotFoundException::new);
    }

    @Override
    public Session findSession(String sessionName, LocalDate startDate) throws SessionNotFoundException {
        return sessions.stream()
                .filter(session -> sessionName.equals(session.getName()) && startDate.isEqual(session.getStartDate()))
                .findFirst().orElseThrow(SessionNotFoundException::new);
    }

    @Override
    public List<Session> findSessionsByName(String sessionName) throws SessionNotFoundException {
        List<Session> sessionsFiltered = sessions.stream()
                .filter(session -> sessionName.equals(session.getName()))
                .collect(Collectors.toList());
         if (sessionsFiltered.size() == 0){
             throw new SessionNotFoundException();
         } else return sessionsFiltered;
    }

    @Override
    public List<Session> findSessionsByDate(LocalDate localDate) throws SessionNotFoundException {
        List<Session> sessionsFiltered = sessions.stream()
                .filter(s -> s.getStartDate().minusDays(1).isBefore(localDate) && s.getEndDate().plusDays(1).isAfter(localDate))
                .collect(Collectors.toList());
        if (sessionsFiltered.size() == 0){
            throw new SessionNotFoundException();
        } else return sessionsFiltered;
    }

    public void appendSessionToFile(Session session){
        try (PrintWriter writer = new PrintWriter(new FileWriter(fileName, true))){
            writer.append(session.getName() + ";" + session.getStartDate() + ";" + session.getEndDate() + ";\n");
        } catch (IOException e) {
            throw new RuntimeException(this.getClass().getSimpleName() + " cannot write to file.");
        }
    }

    @Override
    public void addSession(Session session){
        try {appendSessionToFile(session);
            sessions.add(session);
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void removeSession(Session session) throws SessionNotFoundException {
        loadSessions();
        if (!sessions.remove(session)) {
            throw new SessionNotFoundException();
        } else {
            try (PrintWriter writer = new PrintWriter(new FileWriter(fileName, false))) {
                writer.append("");
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
            for (Session s : sessions){
                appendSessionToFile(s);
            }
        }
    }


    // getset

    @Override
    public List<Session> getSessions() {
        return sessions;
    }

}
