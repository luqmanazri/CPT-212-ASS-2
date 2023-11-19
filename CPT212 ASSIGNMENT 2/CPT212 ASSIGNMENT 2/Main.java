import java.util.Scanner;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        TransportationGraph graph = new TransportationGraph();

        // Adding connections between locations
        graph.addConnection("KUALA LUMPUR", "PENANG", 329);
        graph.addConnection("PENANG", "IPOH", 132);
        graph.addConnection("IPOH", "MELAKA", 361);
        graph.addConnection("JOHOR BAHRU", "MELAKA", 220);
        graph.addConnection("KUALA LUMPUR", "JOHOR BAHRU", 329);
        graph.addConnection("JOHOR BAHRU", "KUANTAN", 346);
        graph.addConnection("KUANTAN", "KOTA BHARU", 356);
        graph.addConnection("KUANTAN", "JITRA", 687);
        graph.addConnection("IPOH", "JITRA", 250);
        

        Scanner scanner = new Scanner(System.in);

        while (true) {
            clearScreen();
            System.out.println("----------------------------------------------------");
            System.out.println("Welcome to the Bus Transportation System in Malaysia");
            System.out.println("----------------------------------------------------");
            System.out.println("Menu:");
            System.out.println("Please Enter your choice from 1 - 4");
            System.out.println("1. Find a Route to a Location");
            System.out.println("2. Add Connection");
            System.out.println("3. Display All Available Locations");
            System.out.println("4. Exit the Program");
            System.out.println("----------------------------------------------------");

            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            clearScreen();

            switch (choice) {
                case 1:
                    // Finding a route
                    System.out.println("-----------------------------------");
                    System.out.println("Available Locations:");
                    System.out.println("-----------------------------------");
                    graph.printAvailableLocations();
                    System.out.println("-----------------------------------");
                    System.out.print("Enter the start location: ");
                    String start = scanner.nextLine().toUpperCase(); // Convert input to uppercase
                    System.out.print("Enter the end location: ");
                    String end = scanner.nextLine().toUpperCase(); // Convert input to uppercase
                    clearScreen();
                    if (!graph.getAdjacencyList().containsKey(start) || !graph.getAdjacencyList().containsKey(end)) {
                        System.out.println("Invalid source or destination location. \nPlease enter valid locations.");
                        break;
                    }

                    List<String> path = graph.findPath(start, end);
                    break;
                case 2:
                    // Adding a connection
                    System.out.println("Add Connection");
                    System.out.println("--------------");
                    System.out.print("Enter the source location: ");
                    String source = scanner.nextLine();
                    System.out.print("Enter the destination location: ");
                    String destination = scanner.nextLine();
                    System.out.print("Enter the distance between the locations: ");
                    int distance = scanner.nextInt();
                    scanner.nextLine(); // Consume the newline character

                    graph.addConnection(source, destination, distance);
                    System.out.println("Connection added successfully!");
                    break;
                case 3:
                    // Displaying the graph
                    System.out.println("------------------------------------------------------");
                    System.out.println("All Available Locations within Transportation Network:");
                    System.out.println("------------------------------------------------------");
                    displayGraph(graph);
                    break;
                case 4:
                    // Exiting the program
                    System.out.println("Exiting the program...");
                    System.out.println("Thank You For Using Our Program!");
                    System.out.println("Goodbye!");
                    return;
                default:
                    System.out.println("Invalid choice! Please enter a valid option.");
            }

            System.out.println();
            System.out.print("Press enter to continue...");
            scanner.nextLine(); // Wait for the user to press enter
        }
    }

    // Method to display the adjacency list representation of the graph
    private static void displayGraph(TransportationGraph graph) {
        Map<String, Map<String, Integer>> adjacencyList = graph.getAdjacencyList();

        for (String source : adjacencyList.keySet()) {
            System.out.println(source);
            System.out.println(" |");

            Map<String, Integer> destinations = adjacencyList.get(source);
            for (String destination : destinations.keySet()) {
                int distance = destinations.get(destination);
                System.out.println(" --> " + destination + " (" + distance + " km)");
            }

            System.out.println();
        }
    }

    // Method to clear the console screen
    private static void clearScreen() {
        System.out.print("\033[H\033[2J"); // ANSI escape sequence to clear the screen
        System.out.flush();
    }
}
