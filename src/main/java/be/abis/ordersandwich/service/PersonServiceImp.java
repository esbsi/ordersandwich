package be.abis.ordersandwich.service;

import be.abis.ordersandwich.exception.NullInputException;
import be.abis.ordersandwich.exception.SandwichTypeNotFoundException;
import be.abis.ordersandwich.exception.TooLateException;
import be.abis.ordersandwich.exception.TooManySandwichesException;
import be.abis.ordersandwich.model.OrderToday;
import be.abis.ordersandwich.model.Person;
import be.abis.ordersandwich.model.SandwichOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Service
public class PersonServiceImp implements PersonService{

    @Autowired
    OrderTodayService orderTodayService;


    public List<Integer> checkMyOrderToday(Person person,OrderToday orderToday){
        int i=0;
        int j=0;
        List<Integer> integerList= new ArrayList<>();
        for(SandwichOrder order:orderToday.getOrder()){
            if(order.getPerson()==person){
                j++;
                System.out.println(person.getName()+" order "+j+": "+ order.getSandwichType().getName()+(order.isRauwkost() ? " club" : "") + (order.isWhite() ? " wit" : " grijs") + ".\n "  + ((order.getComment().equals("") ? "" : (": " + order.getComment()))));
                integerList.add(i);
            }
            i++;
        }
        return integerList;
    }

    public void removeMyOrder(Person person, OrderToday orderToday) throws TooLateException, NullInputException {
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
}
