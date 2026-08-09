import java.util.Scanner;
import java.util.ArrayList;
import java.io.*;

public class Main {
    

    public static void main (String[] args) {

        Boolean isMenuOpen;
        Boolean workerMenu;
        Boolean ChooseCar;
        Boolean ChooseBrand;
        Boolean ChooseModel;
        Boolean ChooseYear;
        Boolean nameCheck;
        Boolean ageCheck;
        Boolean brandCheck;
        Boolean modelCheck;
        Boolean yearCheck;
        String ChoosenBrand = " ";
        String ChoosenModel = " ";
        short ChoosenYear = 0;
        String Choice;
        String workerChoice;
        String newName = " ";
        byte newAge;
        String NewAge;
        String newBrand = " ";
        String newModel = " ";
        short newYear = 0;
        String NewYear;
        Person savePerson = new Person();
        Car saveCar = new Car();
        Deal saveDeal = new Deal();
        String Buy = "Bought A Car";
        String noBuy = "Didn't Buy A Car";

            //=============Console Colors==============
            final String green = "\u001B[32m";
            final String red = "\u001B[31m";
            final String yellow = "\u001B[33m";
            final String cyan = "\u001B[36m";
            final String reset = "\u001B[0m";


        ArrayList<Car> WareHouse = new ArrayList<>();
        ArrayList<Person> People = new ArrayList<>();
        ArrayList<Deal> Deals = new ArrayList<>();


            //============ Load Cars From File ==============
        try {
             File WareHouseData = new File ("WareHouse.txt");
            if (WareHouseData.exists()) {
            Scanner uploadCarsData = new Scanner(WareHouseData);

            while (uploadCarsData.hasNextLine()) {

            String carLine = uploadCarsData.nextLine();

            String[] carParts = carLine.split(", ");

            if (carParts.length == 3) {

                String loadBrand = carParts[0];
                String loadModel = carParts[1];
                short loadYear = Short.parseShort(carParts[2]);

                Car newCar = new Car();
                newCar.Brand = loadBrand;
                newCar.Model = loadModel;
                newCar.Year = loadYear;
                WareHouse.add(newCar);

            }
          } uploadCarsData.close();
        }
            
    } catch (FileNotFoundException e) {System.out.println(e); }


            //============ Load People From File ==============
        try {
             File PeopleData = new File("People.txt");
            if (PeopleData.exists()) {
            Scanner uploadPeopleData = new Scanner(PeopleData);

            while (uploadPeopleData.hasNextLine()) {
            String personLine = uploadPeopleData.nextLine();

            String[] personParts = personLine.split(", ");

            if (personParts.length == 3) {

                String loadName = personParts[0];
                byte loadAge = Byte.parseByte(personParts[1]);
                String loadStatus = personParts[2];

                Person newPerson = new Person();
                newPerson.name = loadName;
                newPerson.age = loadAge;
                newPerson.status = loadStatus;

                People.add(newPerson);
            }
          } uploadPeopleData.close();
        }
            
    } catch (FileNotFoundException e) {System.out.println(e); }

               //============ Load Deals From File ==============
        try {
            File DealsData = new File("Deals.txt");
            if (DealsData.exists()) {
            Scanner uploadDealsData = new Scanner(DealsData);

            while (uploadDealsData.hasNextLine()) {
            String DealLine = uploadDealsData.nextLine();

            String[] DealParts = DealLine.split(", ");

            if (DealParts.length == 5) {

                String loadDealName = DealParts[0];
                byte loadDealAge = Byte.parseByte(DealParts[1]);
                String loadDealBrand = DealParts[2];
                String loadDealModel = DealParts[3];
                short loadDealYear = Short.parseShort(DealParts[4]);

                Deal newDeal = new Deal();
                newDeal.buyer.name = loadDealName;
                newDeal.buyer.age = loadDealAge;
                newDeal.car.Brand = loadDealBrand;
                newDeal.car.Model = loadDealModel;
                newDeal.car.Year = loadDealYear;
                Deals.add(newDeal);

            }
          } uploadDealsData.close();
        }
            
    } catch (FileNotFoundException e) {System.out.println(e); }

        Scanner Input = new Scanner(System.in);

        try {
            
            isMenuOpen = true;

            while (isMenuOpen) {

                Person newPerson = new Person();
                Deal newDeal = new Deal();

                System.out.println(cyan + "Choose: " + reset);
                System.out.println("1- Buy A Car");
                System.out.println("2- View Cars");
                System.out.println("3- Quit");
                Choice = Input.nextLine();

            switch (Choice) {
                        //==========Buy Car==========
                case "1":

                nameCheck = true;
                System.out.println(yellow + "Enter Your Name: " + reset);

                while (nameCheck) {
                newName = Input.nextLine();
                if (newName.matches(".*\\d.*") || newName.isEmpty()) {
                System.out.println(red + "Enter A Valid Name!" + reset);
                } else {newPerson.name = newName; newDeal.buyer.name = newName; nameCheck = false;}    
            }

                ageCheck = true;
                System.out.println(yellow + "Enter Your Age" + reset);

                while (ageCheck) {
                NewAge = Input.nextLine();
                if (NewAge.isEmpty()) {
                    System.out.println(red + "Enter A Valid Age" + reset); continue;
                }

                try {
                newAge = Byte.parseByte(NewAge);
                if (newAge <= 0) {
                System.out.println(red + "Age Cannot Be Negative or Zero" + reset);
                } else if (newAge < 18) {
                System.out.println(red + "Must Be 18+" + reset);
                } else {
                newPerson.age = newAge;
                newDeal.buyer.age = newAge;
                ageCheck = false;
                }
                    
                } catch (NumberFormatException e) {System.out.println(red + "Enter A Valid Age" + reset);}
            }

                //=======Choose Car=======
                if (WareHouse.isEmpty()) {
                    System.out.println(yellow + "There is Currently No Cars, Come Back Later" + reset);
                    newPerson.status = noBuy;
                    People.add(newPerson);
                    newPerson.savePeople(People);
                } else {
                System.out.println(yellow + "Choose A Brand: " + reset);
                for (Car C: WareHouse) {C.CallCars();}
            
                //============Choose All Car Things============
                ChooseCar = true;
                while (ChooseCar) {

                    Car newCar = new Car();

                    ChooseBrand = true;

                    while (ChooseBrand) {
                    ChoosenBrand = Input.nextLine();

                    for (Car C: WareHouse) {
                    if (ChoosenBrand.equalsIgnoreCase(C.Brand)) {
                    newCar.Brand = ChoosenBrand;
                    newDeal.car.Brand = ChoosenBrand;
                    
                    ChooseBrand = false;
                    } else {System.out.println(red + "Choose A Valid Brand" + reset);}
                }
            }

                    System.out.println(yellow + "Choose The Model: " + reset);

                    ChooseModel = true;
                    while (ChooseModel) {
                    ChoosenModel = Input.nextLine();

                    for (Car C: WareHouse) {
                    if (ChoosenModel.equalsIgnoreCase(C.Model)) {
                    newCar.Model = ChoosenModel;
                    newDeal.car.Model = ChoosenModel;

                    ChooseModel = false;
                    } else {System.out.println(red + "Choose A Valid Model" + reset);} 
                }
            }

                    System.out.println(yellow + "Choose The Year: " + reset);

                    ChooseYear = true;
                    while (ChooseYear) {

                    ChoosenYear = Input.nextShort();
                    Input.nextLine();

                    for (Car C: WareHouse) {
                    if (ChoosenYear == C.Year) {
                    newCar.Year = ChoosenYear;
                    newDeal.car.Year = ChoosenYear;

                    ChooseYear = false;
                    } else {System.out.println(red + "Choose A Valid Year" + reset);}   
                }
            }
                    //=========Remove Car From Warehouse=========

                    Car carToRemove = null;

                    for (Car C: WareHouse) {
                    if (C.Brand.equalsIgnoreCase(ChoosenBrand) && C.Model.equalsIgnoreCase(ChoosenModel) && C.Year == ChoosenYear) {
                        carToRemove = C;
                    }
                }   

                    if (carToRemove != null) {
                    WareHouse.remove(carToRemove);
                    } else {System.out.println(red + "Could Not Find Car To Remove" + reset);}

                    Deals.add(newDeal);
                    ChooseCar = false;
                    isMenuOpen = true;
                }

                newPerson.status = Buy;
                People.add(newPerson);
                newPerson.savePeople(People);

                saveCar.saveCars(WareHouse);
                saveDeal.saveDeals(Deals);


                System.out.println(ChoosenBrand + ", " + ChoosenModel + ", " + ChoosenYear + " | " + green + "Has Been Bought By " + reset + newName);
            }
                    
                    break;

                case "2":

                if (WareHouse.isEmpty()) {System.out.println(yellow + "There Is No Cars Available" + reset);}
                else {for (Car C : WareHouse) {C.CallCars();}}
                    
                    break;


                case "3":


                savePerson.savePeople(People);
                saveCar.saveCars(WareHouse);
                saveDeal.saveDeals(Deals);

                Input.close();
                isMenuOpen = false;
                System.out.println(green + "Have A Nice Day" + reset);

                    break;

                case "7548":

                try {

                workerMenu = true;

                while (workerMenu) {

                Car newCar = new Car();

                System.out.println("1- Add a Car");
                System.out.println("2- View People");
                System.out.println("3- View Deals");
                System.out.println("4- Back");

                workerChoice = Input.nextLine();

                switch (workerChoice) {
                    case "1":

                brandCheck = true;
                System.out.println(yellow + "Enter The Brand: " + reset);

                while (brandCheck) {
                newBrand = Input.nextLine();
                if (newBrand.matches(".*\\d.*") || newBrand.isEmpty()) {
                System.out.println(red + "Enter A Valid Brand!" + reset);
                } else {newCar.Brand = newBrand; brandCheck = false;}    
            }

                modelCheck = true;
                System.out.println(yellow + "Enter The Model: " + reset);

                while (modelCheck) {
                newModel = Input.nextLine();
                if (newModel.isEmpty()) {
                System.out.println(red + "Enter A Valid Model!" + reset);
                } else {newCar.Model = newModel; modelCheck = false;}    
            }   

                yearCheck = true;
                System.out.println(yellow + "Enter The Year: " + reset);

                while (yearCheck) {
                NewYear = Input.nextLine();
                if (NewYear.isEmpty()) {
                    System.out.println(red + "Enter A Valid Year" + reset); continue;
                }

                try {
                newYear = Short.parseShort(NewYear);
                if (newYear <= 0) {
                System.out.println(red + "Year Cannot Be Negative or Zero" + reset);
                } else {
                newCar.Year = newYear;
                yearCheck = false;
                }
                    
                } catch (NumberFormatException e) {System.out.println(red + "Enter A Valid Year" + reset);}
            }

                    newCar.Brand = newBrand;
                    newCar.Model = newModel;
                    newCar.Year = newYear;

                    WareHouse.add(newCar);

                    saveCar.saveCars(WareHouse);

                    System.out.println(newBrand + ", " + newModel + ", " + newYear + " | " + green + "Has Been Added Sucessfully" + reset);

                    workerMenu = true;

                        break;

                    case "2":

                    if (People.isEmpty()) {System.out.println(yellow + "There Is No Customers" + reset);}
                    else {for (Person P : People) {P.CallPeople();}}
                        
                        break;

                    case "3":

                    if (Deals.isEmpty()) {System.out.println(yellow + "There Is No Deals" + reset);}
                    else {for (Deal D : Deals) {D.CallDeal();}}

                        break;

                    case "4":

                    workerMenu = false;
                        
                        break;
                
                    default: System.out.println(red + "Enter a Valid Option" + reset);
                        break;
                }
            }

        } catch (NumberFormatException e) {System.out.println(red + "Enter a Valid Option" + reset);}

                    break;
            
                default:
                    System.out.println(red + "Enter A Valid Option" + reset);
                    break;
            }
                
            }

        } catch (Exception e) {
        }


    }
}
