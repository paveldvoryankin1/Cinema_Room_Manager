package cinema;
import java.util.Scanner;

public class Cinema {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the number of rows:");
        int rows = scanner.nextInt();
        System.out.println("Enter the number of seats in each row:");
        int seats = scanner.nextInt();
        String[][] cinema = new String[rows + 1][seats + 1];
        cinema[0][0] = " ";
        for (int i = 1; i < rows + 1; i++) {
            cinema[i][0] = String.valueOf(i);
        }
        for (int j = 1; j < seats + 1; j++) {
            cinema[0][j] = String.valueOf(j);
        }
        for (int i = 1; i < rows + 1; i++) {
            for (int j = 1; j < seats + 1; j++) {
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
                int row1 = scanner.nextInt();
                System.out.println("Enter a seat number in that row: ");
                int seat1 = scanner.nextInt();
                boolean invalidCoordinates = false;
                if (row1 > rows || seat1 > seats) {
                    invalidCoordinates = true;
                } else if (cinema[row1][seat1].equals("B")) {
                    invalidCoordinates = true;
                }
                while (invalidCoordinates) {
                    if (row1 > rows || seat1 > seats) {
                        System.out.println("Wrong input!");
                    } else if (cinema[row1][seat1].equals("B")) {
                        System.out.println("That ticket has already been purchased!");
                    }
                    System.out.println();
                    System.out.println("Enter a row number: ");
                    row1 = scanner.nextInt();
                    System.out.println("Enter a seat number in that row: ");
                    seat1 = scanner.nextInt();
                    if (row1 <= rows && seat1 <= seats) {
                        if (!cinema[row1][seat1].equals("B")) {
                            invalidCoordinates = false;
                        }
                    }
                }

                cinema[row1][seat1] = "B";
                purchasedTickets++;
                //purchasedPercent = ((float) Math.round(((float) purchasedTickets / totalSeats) * 100)) / 100;
                purchasedPercent = (float) purchasedTickets / totalSeats * 100;
                currentIncome += ticketPrice(rows, seats, row1);
                System.out.println("Ticket price: $" + ticketPrice(seats, rows, row1));
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

    public static int ticketPrice(int rows, int seats, int row1) {
        if (seats * rows <= 60) {
            return 10;
        } else if (row1 <= rows / 2) {
                return 10;
        } else {
            return 8;
        }
    }

    public static void printCinema(String[][] cinema) {
        System.out.println("Cinema:");
        for (int i = 0; i < cinema.length; i++) {
            for (int j = 0; j < cinema[0].length; j++) {
                System.out.print(cinema[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
}
