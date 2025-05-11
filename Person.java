import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Scanner;
import java.lang.reflect.Array;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.YearMonth;


// Person Class
public class Person{
    static Scanner in = new Scanner(System.in);
    String name;
    String CNIC;
    String phoneNumber;
    String email;
    public Person(){
        this.name = "Null";
        this.CNIC = "XXXXX-XXXXXXX-X";
        this.phoneNumber = "Null";
        this.email = "Null";
    }
    public Person(String name,String CNIC,String phoneNumber,String email){
        this.name = name;
        this.CNIC = CNIC;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }
    public void changeNumber(String phoneNumber){
        this.phoneNumber = phoneNumber;
    }
    public void changeEmail(String email){
        this.email = email;
    }
    public void showDetails(){
        System.out.println(name + "\n"+CNIC + "\n"+phoneNumber + "\n"+email);
    }



// Main Function
    public static void main(String[] args) throws InterruptedException {
        HashMap<String,Student> students = new HashMap<>();
        Student obj = null;
        while(true){
            System.out.print("\033[H\033[2J");
            System.out.flush();
        System.out.println("Welcome to Hostel Management System!");
        System.out.println("1. Login\n2. Register\n3. Exit");
        int choice = in.nextInt();
        in.nextLine();
        if(choice==1){
            System.out.print("\033[H\033[2J");
            System.out.flush();
            if(students.isEmpty()){
                System.out.println("No accounts Registered");
                break;
            }
            System.out.println("Enter the Email : ");
            String loginEmail = in.nextLine();
            if(students.containsKey(loginEmail)){
            Student newStudent = students.get(loginEmail);
            newStudent.loginStudent();
            }
            else{
                System.out.println("Email isnt Registered");
                break;
            }
        }
        else if(choice==2){
            System.out.print("\033[H\033[2J");
            System.out.flush();
            obj = new Student();
            obj.registerStudent();
            if(students.containsKey(obj.email)){
                System.out.println("Account Already Registered , Try Logging in");
                Thread.sleep(1000);
            }
            else{
                students.put(obj.email,obj);
                System.out.println("Email Registration Succesful");
                Thread.sleep(1000);
            }
        }
        else if(choice==3){
            break;
        }
        else{
            System.out.println("Invalid Choice , Exitting the System");
        }
    }
    }
}
// ***************************************************************************************************************************************
// Student Class
class Student extends Person{
LinkedList<Complaint> listOfComplaints = new LinkedList<>();
HashMap<String,Integer> visitorToStudent = new HashMap<>();
static LinkedList<Visitor> listOfVisitors = new LinkedList<>();
static LinkedList<String> WaitingList = new LinkedList<>();
static LinkedList<Integer> Rooms = new LinkedList<>();
private String id;
private double roomRent;
private int roomNumber;
private String roomType;
private String department;
private int term;
private String password;
boolean login;
Fee Fee;
// Default Constructor
    public Student(){
        this.id = "Null";
        this.roomNumber = 0;
        this.department = "Null";
    }
// Registering Student
    public void registerStudent(){
        System.out.println("Enter the Name : ");
        this.name = in.nextLine();
        System.out.println("Enter the CNIC : (XXXXX-XXXXXXX-X) ");
        this.CNIC = in.nextLine();
        System.out.println("Enter the Phone Number : ");
        this.phoneNumber = in.nextLine();
        System.out.println("Enter the Email : ");
        this.email = in.nextLine();
        System.out.println("Enter the Password : ");
        this.password = in.nextLine();
        System.out.print("\033[H\033[2J");
        System.out.flush();
        
    }
// Login Student
    public void loginStudent() throws InterruptedException{
        login = false;    
        System.out.println("Enter the Password : ");
        String loginPassword = in.nextLine();
        if(this.password.equals(loginPassword)){
            login = true;
            System.out.print("\033[H\033[2J");
            System.out.flush();
            System.out.println("Login Succesful");
            menu();
        }
        else{
            System.out.print("\033[H\033[2J");
            System.out.flush();
            System.out.println("Incorrect Email or Password");
            Thread.sleep(1000);
        }
    }
// Displaying Menu after Login
    public void menu() throws InterruptedException{
        while(true){
        Thread.sleep(2000);
        System.out.print("\033[H\033[2J");
        System.out.flush();
        System.out.println("1.Apply For Accomodation\n2.File Complaint\n3.View Complaint Status\n4.Register Visitor\n5.View Room Details\n6.Check Fee Status\n7.Log Out");
        int choice = in.nextInt();
        in.nextLine();
        System.out.print("\033[H\033[2J");
        System.out.flush();
        if(choice==1){
            assignRoom();
        }
        else if(choice==2){
            fileComplaint();
        }
        else if(choice==3){
            viewComplaintStatus();
        }
        else if(choice==4){
            registerVisitor();
        }
        else if(choice==5){
            viewRoomDetails();
        }
        else if(choice==6){
            checkFeeStatus();
        }
        else if(choice==7){
            login=false;
            return;
        }
        else{
            System.out.println("Invalid Input");
        }
    }
}
    public void viewComplaintStatus() throws InterruptedException{
        if(listOfComplaints.isEmpty()){
            System.out.println("You have No Registered Complaints!");
        }
        else{
            System.out.println("The following are your Complaints : ");
            for(Complaint iterable : listOfComplaints){
                iterable.viewComplaintStatus();
            }
            Thread.sleep(2000);
        }
    }
// Assignment OF room
    public void assignRoom() throws InterruptedException{
        if(roomNumber==0){
            System.out.print("\033[H\033[2J");
            System.out.flush();
            int start=0,end=0;
            System.out.println("Enter your Term : (1,2,3,4)");
            term = in.nextInt();
            in.nextLine();
            switch(term){
                case 1:
                start =150;end=200;
                roomType = "3 Seater Occupation";
                roomRent = 1500;
                break;
                case 2:
                start = 100;end=149;
                roomType = "2 Seater Occupation";
                roomRent = 3000;
                break;
                case 3:
                start = 50;end=99;
                roomType = "1 Seater Occupation";
                roomRent = 4500;
                break;
                case 4:
                start=1;end =49;
                roomType = "1 Seater Occupation";
                roomRent = 4500;
                break;
                default:
                System.out.println("Invalid Term");
            }
            ArrayList<Integer> availableRooms = new ArrayList<>();
            for(int i=start;i<=end;i++){
                if(!Rooms.contains(i)){
                    availableRooms.add(i);
                }
            }
            if(availableRooms.isEmpty()){
                System.out.print("\033[H\033[2J");
                System.out.flush();
                System.out.println("No Rooms are Available , You are being forwarded to the Waiting List");
                WaitingList.add(this.email);
            }
            else{
                int randomIndex = (int)(Math.random()*availableRooms.size());
                roomNumber = availableRooms.get(randomIndex);
                Rooms.add(roomNumber);
                System.out.println("Successfully Alloted Room Number " + this.roomNumber + " To Student " + this.name);
                
            }
        }
    else{
        System.out.print("\033[H\033[2J");
        System.out.flush();
        System.out.println("You are Already Assigned a Room");
        }
    }
// Complaint System
    public void fileComplaint() throws InterruptedException{
        System.out.println("Enter your Complaint : ");
        String complaint = in.nextLine();
        Complaint newComplaint = new Complaint(complaint);
        listOfComplaints.add(newComplaint);
        System.out.println("Complaint Succesfully Filed Against ID : " + newComplaint.complaintID + " on " + newComplaint.date);

    }
// Registration Of Vistors
    public void registerVisitor(){
        
        System.out.println("Enter the Checkout Time (hh:mm am/pm) : ");
        String checkout = in.nextLine();
        Visitor visitor = new Visitor(checkout.toLowerCase());
        System.out.println("Enter the Following Details : ");
        System.out.print("Name : ");
        visitor.name = in.nextLine();
        System.out.print("\nCNIC : ");
        visitor.CNIC = in.nextLine();
        System.out.print("\nPhone Number : ");
        visitor.phoneNumber = in.nextLine();
        listOfVisitors.add(visitor);
        visitorToStudent.put(visitor.CNIC, this.roomNumber);
    }
// Check Room Details
    public void viewRoomDetails() throws InterruptedException{
        if(roomNumber==0){
            System.out.println("You Havent Been alloted a room yet");
        }
        else{
        System.out.println("The following are the Details of Your Occupation : ");
        System.out.println("Name : " + this.name);
        System.out.println("Room Number : " + this.roomNumber);
        System.out.println("Room Occupancy : " + this.roomType);
        System.out.println("Room Rent : " + this.roomRent);
        }
    }
// Check Fee Status
    public void checkFeeStatus(){
        if(roomNumber==0){
         System.out.println("Room Not Found. No Fee Generated");
         return;
        }
        double total =0;
        Fee bill = new Fee(roomRent);
        Messing bill2 = new Messing();
        total+=bill.calculateFee();
        total+=bill2.calculateBilling();
            System.out.println("Room Rent : " + bill.amount);
            System.out.println("Messing Bill : " + bill2.calculateBilling());
            System.out.println("Total Bill : " + total);
            System.out.println("Due Date : " + bill.dueDate);
            System.out.println("Status : " + bill.isPaid);
    }
}
// Complaint Class
class Complaint{
    static HashMap<Integer,String> complaintID2Des = new HashMap<>();
    static HashMap<Integer,LocalDate> complaintID2Date = new HashMap<>();
    static LinkedList<Integer> Complaints = new LinkedList<>();
    int complaintID;
     String description;
     LocalDate date;
     boolean status;
// Parametrized Constructor
    public Complaint(String description){
        this.description = description;
        this.date = LocalDate.now();
        while(true){
        complaintID = (int)(Math.random()*1000);
            if(!complaintID2Des.containsKey(complaintID)){
                complaintID2Des.put(complaintID, description);
                complaintID2Date.put(complaintID, date);
                Complaints.add(complaintID);
                break;
            }
        }
        this.date = LocalDate.now();
        this.status = false;
    }
// Complaint Status
    public void viewComplaintStatus() throws InterruptedException{
                System.out.print(this.complaintID + " ");
                System.out.print(this.description + " ");
                System.out.println(this.date);
        }
    }
// Visitor Class
class Visitor extends Person{
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh:mm a");
    private String checkInTime = (LocalDateTime.now()).format(formatter);
    private String checkOutTime;
// Default Constructor
    public Visitor(String checkOutTime){
        LocalTime checkout = LocalTime.parse(checkOutTime, formatter);
        this.checkOutTime = checkout.format(formatter);
    }
    public void showDetails(){
        System.out.println(checkInTime + "\n"+checkOutTime);
    }
}
// Fee Class
class Fee{
    double amount;
    LocalDate dueDate = LocalDate.now();
    boolean isPaid;
// Parametrized Constructor
    public Fee(double amount){
        this.amount = amount;
    }
// Fee Status
    public double calculateFee(){
        return amount;
    }
}
// Messing Class
class Messing{
    static Scanner in = new Scanner(System.in);
    int month=5;
    int year =2025;
    ArrayList<Integer> daysOut = new ArrayList<>();
    double[] charges = {100,200,200,500,200,50,125};
    //double[] charges = new double[7];
    public void setCharges(){
        for(int i =0;i<7;i++){
            charges[i] = in.nextDouble();
        }
    }
    public void messDaysOut(){
        while(true){
            System.out.println("Enter The Days for Messing Out (0 to exit)");
            int day = in.nextInt();
            if(day>0 && day<32){
                daysOut.add(day);
            }
            else if(day==0){
                break;
            }
            else{
                System.out.println("Invalid Enter Again");
            }
        }
    }
    public int daysInMonth(int month,int year){
        YearMonth ym = YearMonth.of(year, month);
        return ym.lengthOfMonth();
    }
    public double calculateBilling(){
        double total = 0;
        for (int day = 1; day <= daysInMonth(month, year); day++) {
            if (!daysOut.contains(day)) {
        LocalDate date = LocalDate.of(year, month, day);
        int weekday = date.getDayOfWeek().getValue() - 1;
        total += charges[weekday];
    }
    }
        return total;

    }
}
