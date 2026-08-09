import java.io.*;
import java.util.ArrayList;

public class Deal {

    Car car = new Car();
    Person buyer = new Person();


    public Deal () {

    }

    public Deal (Car car, Person buyer) {

    
        this.car = car;
        this.buyer = buyer;

    }

    public void CallDeal () {
        System.out.println(this.buyer.name + "," + this.buyer.age + " Has Bought a " + this.car.Brand + " " + this.car.Model + "," + this.car.Year);
    }

    public void saveDeals (ArrayList<Deal> Deals) {

        try {

        PrintWriter DealsWriter = new PrintWriter(new FileWriter("Deals.txt"));
        for (Deal D : Deals) {DealsWriter.println(D.buyer.name + ", " + D.buyer.age + ", " + D.car.Brand + ", " + D.car.Model + ", " + D.car.Year);}

        DealsWriter.close();

        } catch (IOException e) {System.out.println(e);} 
    }
}
