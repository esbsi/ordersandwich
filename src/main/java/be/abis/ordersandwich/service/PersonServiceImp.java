package be.abis.ordersandwich.service;

import be.abis.ordersandwich.exception.*;
import be.abis.ordersandwich.model.Person;
import be.abis.ordersandwich.repository.PersonJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonServiceImp implements PersonService{

    @Autowired
    OrderTodayService orderTodayService;
    @Autowired
    PersonJpaRepository personRepository;


    @Override
    public List<Person> getPersonList() {
        return personRepository.findAll();
    }

    @Override
    public void addPerson(Person person) throws PersonAlreadyInExistException {
        Person p=  personRepository.findPersonById(person.getId());
        if (p!=null) throw new PersonAlreadyInExistException("Person with this id already exists");
       Person person1 = personRepository.findPersonByFirstNameAndLastName(person.getFirstName(), person.getLastName());
        if (person1!=null) throw new PersonAlreadyInExistException("Person with this name already exists");
        personRepository.save(person);
    }

    @Override
    public void removePerson(Person person) throws PersonNotFoundException {
        Person p =findPerson(person.getId());
        if (p==null) throw new PersonNotFoundException("Person with this id doesn't exists");
        Person person1 = personRepository.findPersonByFirstNameAndLastName(person.getFirstName(), person.getLastName());
        if (person1==null) throw new PersonNotFoundException("Person with this name doesn't exists");
        if (p.equals(person1)) {
            personRepository.delete(person);
        }else{throw new PersonNotFoundException("id and name don't match");}
    }

    @Override
    public void removePerson(int id) throws PersonNotFoundException {
        Person p =findPerson(id);
        if (p==null) throw new PersonNotFoundException("Person with this id doesn't exists");

        personRepository.delete(p);

    }

    @Override
    public Person findPerson(int id) throws PersonNotFoundException {
        Person p=  personRepository.findPersonById(id);
        if (p==null) throw new PersonNotFoundException("person not found");
        return p;
    }

    @Override
    public Person update(Person person) throws PersonNotFoundException {
        findPerson(person.getId());
        return personRepository.save(person);
    }

    @Override
    public Person findPerson(String firstname,String lastname) throws PersonNotFoundException {
        Person p= personRepository.findPersonByFirstNameAndLastName(firstname,lastname);
        if (p==null ) throw new PersonNotFoundException("person not found");
        return p;
    }



    /*

    public void removeMyOrder(Person person, OrderToday orderToday) throws TooLateException, NullInputException {
        if (person==null || orderToday==null ) throw new NullInputException("null input");
        List<Integer> integerList=checkMyOrderToday(person,orderToday);
        if (integerList.size()==0){
            System.out.println("nothing ordered");
        }else {
            Scanner scan = new Scanner(System.in);
            System.out.println("do you want to delete an order?");
            System.out.println("Y/N");
            String toDelete = scan.nextLine();
            if (toDelete.equals("Y")) {
                System.out.println("which order do you want to delete?");
                System.out.println("Choose from 1 to "+integerList.size());
                int oderToDelete = scan.nextInt();
                int correctPos=oderToDelete-1;
                orderTodayService.removeOrder(correctPos,orderToday);
            } else {
                System.out.println("nothing will be deleted");
            }
        }
    }

     */
}
