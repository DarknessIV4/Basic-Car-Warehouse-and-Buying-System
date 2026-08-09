import java.io.*;
import java.util.ArrayList;

public class Car {

    short Year;
    String Brand;
    String Model;

    public Car () {

    }

    public Car (String Brand, String Model, short Year) {

        this.Brand = Brand;
        this.Model = Model;
        this.Year = Year;

    }

    public void CallCars () {
    
        System.out.println(Brand + ": " + Model + ", " + Year);

    }

    public void saveCars (ArrayList<Car> WareHouse) {

        try {

        PrintWriter CarsWriter = new PrintWriter(new FileWriter("WareHouse.txt"));
        for (Car C : WareHouse) {CarsWriter.println(C.Brand + ", " + C.Model + ", " + C.Year);}

        CarsWriter.close();

        } catch (IOException e) {System.out.println(e);} 
}
} 