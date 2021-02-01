package cinema;
import java.util.Scanner;

public class Cinema {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the number of rows:");
        int rows = scanner.nextInt();
        System.out.println("Enter the number of seats in each row:");
        int seats = scanner.nextInt();
        String[][] cinema = new String[rows][seats];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < seats; j++) {
                cinema[i][j] = "S";
            }
        }

        System.out.println();
        int menuFlag = 1;
        int purchasedTickets = 0;
        float purchasedPercent = 0;
        int totalSeats = rows * seats;
        int currentIncome = 0;
        int totalIncome = totalSeats <= 60 ? totalSeats * 10 : (rows / 2) * seats * 10 + (rows - rows / 2) * seats * 8;

        while (menuFlag != 0) {
            System.out.println("1. Show the seats");
            System.out.println("2. Buy a ticket");
            System.out.println("3. Statistics");
            System.out.println("0. Exit");

            menuFlag = scanner.nextInt();

            if (menuFlag == 1) {
                printCinema(cinema);
            } else if (menuFlag == 2) {
                System.out.println("Enter a row number: ");
                int rowEnter = scanner.nextInt();
                int rowIn = rowEnter - 1;
                System.out.println("Enter a seat number in that row: ");
                int seatEnter = scanner.nextInt();
                int seatIn = seatEnter - 1;
                boolean invalidCoordinates = false;
                if (rowEnter > rows || seatEnter > seats) {
                    invalidCoordinates = true;
                } else if (cinema[rowIn][seatIn].equals("B")) {
                    invalidCoordinates = true;
                }
                while (invalidCoordinates) {
                    if (rowEnter > rows || seatEnter > seats) {
                        System.out.println("Wrong input!");
                    } else if (cinema[rowIn][seatIn].equals("B")) {
                        System.out.println("That ticket has already been purchased!");
                    }
                    System.out.println();
                    System.out.println("Enter a row number: ");
                    rowEnter = scanner.nextInt();
                    rowIn = rowEnter - 1;
                    System.out.println("Enter a seat number in that row: ");
                    seatEnter = scanner.nextInt();
                    seatIn = seatEnter - 1;
                    if (rowEnter <= rows && seatEnter <= seats) {
                        if (!cinema[rowIn][seatIn].equals("B")) {
                            invalidCoordinates = false;
                        }
                    }
                }

                cinema[rowIn][seatIn] = "B";
                purchasedTickets++;
                purchasedPercent = (float) purchasedTickets / totalSeats * 100;
                currentIncome += ticketPrice(rows, seats, rowEnter);
                System.out.println("Ticket price: $" + ticketPrice(rows, seats, rowEnter));
                System.out.println();
            } else if (menuFlag == 3) {
                System.out.println();
                System.out.println("Number of purchased tickets: " + purchasedTickets);
                System.out.printf("Percentage: %.2f%%", purchasedPercent);
                System.out.println();
                System.out.println("Current income: $" + currentIncome);
                System.out.println("Total income: $" + totalIncome);
                System.out.println();
            }
        }
    }

    public static int ticketPrice(int rows, int seats, int rowEnter) {
        if (seats * rows <= 60) {
            return 10;
        } else if (rowEnter <= rows / 2) {
                return 10;
        } else {
            return 8;
        }
    }

    public static void printCinema(String[][] cinema) {
        String[][] cinemaVisual = new String[cinema.length + 1][cinema[0].length + 1];
        cinemaVisual[0][0] = " ";
        for (int i = 1; i < cinemaVisual.length; i++) {
            cinemaVisual[i][0] = String.valueOf(i);
        }
        for (int j = 1; j < cinemaVisual[0].length; j++) {
            cinemaVisual[0][j] = String.valueOf(j);
        }
        for (int i = 1; i < cinemaVisual.length; i++) {
            for (int j = 1; j < cinemaVisual[0].length; j++) {
                cinemaVisual[i][j] = cinema[i - 1][j - 1];
            }
        }
        System.out.println("Cinema:");
        for (int i = 0; i < cinemaVisual.length; i++) {
            for (int j = 0; j < cinemaVisual[0].length; j++) {
                System.out.print(cinemaVisual[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
}
