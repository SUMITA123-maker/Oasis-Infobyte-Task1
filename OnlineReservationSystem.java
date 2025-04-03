import java.util.*;

class Reservation {
    static int nextPNR = 1000;
    int pnr;
    String name;
    String trainNo;
    String trainName;
    String classType;
    String doj;
    String source;
    String destination;

    public Reservation(String name, String trainNo, String trainName, String classType, String doj, String source, String destination) {
        this.pnr = nextPNR++;
        this.name = name;
        this.trainNo = trainNo;
        this.trainName = trainName;
        this.classType = classType;
        this.doj = doj;
        this.source = source;
        this.destination = destination;
    }

    public void display() {
        System.out.println("PNR: " + pnr);
        System.out.println("Name: " + name);
        System.out.println("Train No: " + trainNo);
        System.out.println("Train Name: " + trainName);
        System.out.println("Class Type: " + classType);
        System.out.println("Date of Journey: " + doj);
        System.out.println("From: " + source);
        System.out.println("To: " + destination);
    }
}

public class OnlineReservationSystem {
    static Scanner scanner = new Scanner(System.in);
    static Map<String, String> users = new HashMap<>();
    static List<Reservation> reservations = new ArrayList<>();

    public static void main(String[] args) {
        // Dummy user for login
        users.put("admin", "1234");

        if (login()) {
            showMenu();
        }
    }

    public static boolean login() {
        System.out.println("=== LOGIN ===");
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        if (users.containsKey(username) && users.get(username).equals(password)) {
            System.out.println("Login successful!\n");
            return true;
        } else {
            System.out.println("Invalid username or password.");
            return false;
        }
    }

    public static void showMenu() {
        while (true) {
            System.out.println("=== MAIN MENU ===");
            System.out.println("1. Make Reservation");
            System.out.println("2. Cancel Reservation");
            System.out.println("3. View All Reservations");
            System.out.println("4. Exit");
            System.out.print("Choose option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    makeReservation();
                    break;
                case 2:
                    cancelReservation();
                    break;
                case 3:
                    viewAllReservations();
                    break;
                case 4:
                    System.out.println("Thank you for using the system!");
                    System.exit(0);
                default:
                    System.out.println("Invalid option.");
            }
        }
    }

    public static void makeReservation() {
        System.out.println("=== MAKE RESERVATION ===");
        System.out.print("Enter your name: ");
        String name = scanner.nextLine();

        System.out.print("Enter train number: ");
        String trainNo = scanner.nextLine();

        System.out.print("Enter train name: ");
        String trainName = scanner.nextLine();

        System.out.print("Enter class type (e.g., Sleeper, AC): ");
        String classType = scanner.nextLine();

        System.out.print("Enter date of journey (DD-MM-YYYY): ");
        String doj = scanner.nextLine();

        System.out.print("From (source): ");
        String source = scanner.nextLine();

        System.out.print("To (destination): ");
        String destination = scanner.nextLine();

        Reservation res = new Reservation(name, trainNo, trainName, classType, doj, source, destination);
        reservations.add(res);

        System.out.println("Reservation successful! Your PNR is: " + res.pnr + "\n");
    }

    public static void cancelReservation() {
        System.out.println("=== CANCEL RESERVATION ===");
        System.out.print("Enter PNR number: ");
        int pnr = scanner.nextInt();
        scanner.nextLine(); // consume newline

        boolean found = false;
        Iterator<Reservation> itr = reservations.iterator();
        while (itr.hasNext()) {
            Reservation r = itr.next();
            if (r.pnr == pnr) {
                r.display();
                System.out.print("Confirm cancellation? (yes/no): ");
                String confirm = scanner.nextLine();
                if (confirm.equalsIgnoreCase("yes")) {
                    itr.remove();
                    System.out.println("Reservation cancelled successfully!\n");
                } else {
                    System.out.println("Cancellation aborted.\n");
                }
                found = true;
                break;
            }
        }
        if (!found) {
            System.out.println("PNR not found.\n");
        }
    }

    public static void viewAllReservations() {
        System.out.println("=== ALL RESERVATIONS ===");
        if (reservations.isEmpty()) {
            System.out.println("No reservations found.\n");
        } else {
            for (Reservation r : reservations) {
                r.display();
                System.out.println("-------------------------");
            }
        }
    }
}
