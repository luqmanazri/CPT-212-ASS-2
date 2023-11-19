import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
//CPT212
class TransportationGraph {
    private Map<String, Map<String, Integer>> adjacencyList;

    // Constructor
    public TransportationGraph() {
        adjacencyList = new HashMap<>();
    }

    // Method to add a connection between two locations with a given distance
    public void addConnection(String source, String destination, int distance) {
        // Convert the locations to uppercase for consistency
        String uppercaseSource = source.toUpperCase();
        String uppercaseDestination = destination.toUpperCase();

        // Add the connection in both directions (undirected graph)
        adjacencyList.computeIfAbsent(uppercaseSource, k -> new HashMap<>()).put(uppercaseDestination, distance);
        adjacencyList.computeIfAbsent(uppercaseDestination, k -> new HashMap<>()).put(uppercaseSource, distance);
    }

    // Method to find a path between two locations using depth-first search (DFS)
    public List<String> findPath(String start, String end) {
        List<String> visited = new ArrayList<>();
        List<String> path = new ArrayList<>();

        dfs(start, end, visited, path);

        if (path.isEmpty()) {
            System.out.println("No path from " + start + " to " + end + " in the transportation network.");
        } else {
            // Print the path and total distance
            System.out.println("-------------------------------------------------------------------");
            System.out.println("Path from " + start + " to " + end + " in the transportation network:");
            System.out.println("-------------------------------------------------------------------");

            for (int i = 0; i < path.size() - 1; i++) {
                String current = path.get(i);
                String next = path.get(i + 1);
                int distance = adjacencyList.get(current).get(next);
                System.out.println(current + " to " + next + " (" + distance + " km)");
            }
            System.out.println("-------------------------------------------------------------------");
            System.out.println("Total distance: " + getTotalDistance(path) + " km");
        }

        return path;
    }

    // Method to get the distance between two locations
    public int getDistance(String source, String destination) {
        return adjacencyList.get(source).get(destination);
    }

    // Method to calculate the total distance along a given path
    public int getTotalDistance(List<String> path) {
        int totalDistance = 0;
        for (int i = 0; i < path.size() - 1; i++) {
            String current = path.get(i);
            String next = path.get(i + 1);
            totalDistance += adjacencyList.get(current).get(next);
        }
        return totalDistance;
    }

    // Method to print all available locations in the transportation network
    public void printAvailableLocations() {
        for (String location : adjacencyList.keySet()) {
            System.out.println(location);
        }
    }

    // Method to retrieve the adjacency list of the graph
    public Map<String, Map<String, Integer>> getAdjacencyList() {
        return adjacencyList;
    }

    // Depth-first search (DFS) implementation for finding a path
    private boolean dfs(String vertex, String end, List<String> visited, List<String> path) {
        visited.add(vertex);
        path.add(vertex);

        if (vertex.equals(end)) {
            return true; // Path found
        }

        // Check neighbors of the current vertex
        Map<String, Integer> neighbors = adjacencyList.get(vertex);
        if (neighbors != null) {
            for (Map.Entry<String, Integer> neighbor : neighbors.entrySet()) {
                String neighborVertex = neighbor.getKey();
                if (!visited.contains(neighborVertex)) {
                    if (dfs(neighborVertex, end, visited, path)) {
                        return true; // Path found
                    }
                }
            }
        }

        // Backtrack if no path found
        path.remove(path.size() - 1);
        visited.remove(vertex);

        return false;
    }
}
