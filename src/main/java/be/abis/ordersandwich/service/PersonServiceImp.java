package be.abis.ordersandwich.service;

import be.abis.ordersandwich.exception.*;
import be.abis.ordersandwich.model.OrderToday;
import be.abis.ordersandwich.model.Person;
import be.abis.ordersandwich.model.SandwichOrder;
import be.abis.ordersandwich.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Service
public class PersonServiceImp implements PersonService{

    @Autowired
    OrderTodayService orderTodayService;
    @Autowired
    PersonRepository personRepository;


    public List<Integer> checkMyOrderToday(Person person,OrderToday orderToday) throws NullInputException {
        if (person==null || orderToday==null ) throw new NullInputException("null input");
        int i=0;
        int j=0;
        List<Integer> integerList= new ArrayList<>();
        for(SandwichOrder order:orderToday.getOrder()){
            if(order.getPerson()==person){
                if(order.getSandwichType()!=null){
                    j++;
                    System.out.println(person.getName()+" order "+j+": "+ order.getSandwichType().getName()+(order.isRauwkost() ? " club" : "") + (order.isWhite() ? " wit" : " grijs") + ".\n "  + ((order.getComment().equals("") ? "" : (": " + order.getComment()))));
                    integerList.add(i);
                }else {
                    integerList.add(i);
                    System.out.println("doesn't want anything today");
                }
            }
            i++;
        }
        return integerList;
    }

    @Override
    public List<Person> getPersonList() {
        return personRepository.getPersonList();
    }

    @Override
    public void addPerson(String personName) {
        personRepository.addPerson(personName);
    }

    @Override
    public void removePerson(String personName) throws PersonNotFoundException {
        personRepository.removePerson(personName);

    }

    @Override
    public Person findPerson(String personName) throws PersonNotFoundException {
        return personRepository.findPerson(personName);
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
