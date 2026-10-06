package com.salesdashboard;

import com.salesdashboard.model.Sale;
import com.salesdashboard.service.SalesService;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static final Scanner scanner =
            new Scanner(System.in);

    private static final SalesService service =
            new SalesService();

    public static void main(String[] args) {

        try {

            service.initialize();

            System.out.println(
                    "================================"
            );

            System.out.println(
                    "      SALES ANALYTICS SYSTEM"
            );

            System.out.println(
                    "================================"
            );

            while (true) {

                showMenu();

                int choice =
                        Integer.parseInt(
                                scanner.nextLine()
                        );

                switch (choice) {

                    case 1:
                        addSale();
                        break;

                    case 2:
                        viewSales();
                        break;

                    case 3:
                        searchSale();
                        break;

                    case 4:
                        deleteSale();
                        break;

                    case 5:
                        salesSummary();
                        break;

                    case 0:
                        System.out.println("Thank you!");
                        return;

                    default:
                        System.out.println(
                                "Invalid choice"
                        );
                }
            }

        } catch (Exception e) {

            System.out.println(
                    "Error: " + e.getMessage()
            );
        }
    }

    private static void showMenu() {

        System.out.println();
        System.out.println("1. Add Sale");
        System.out.println("2. View All Sales");
        System.out.println("3. Search Sale");
        System.out.println("4. Delete Sale");
        System.out.println("5. Sales Summary");
        System.out.println("0. Exit");

        System.out.print("Enter choice: ");
    }

    private static void addSale()
            throws Exception {

        System.out.print("Sale ID: ");
        int id = Integer.parseInt(scanner.nextLine());

        System.out.print("Date (YYYY-MM-DD): ");
        LocalDate date =
                LocalDate.parse(scanner.nextLine());

        System.out.print("Product: ");
        String product = scanner.nextLine();

        System.out.print("Category: ");
        String category = scanner.nextLine();

        System.out.print("Region: ");
        String region = scanner.nextLine();

        System.out.print("Quantity: ");
        int quantity =
                Integer.parseInt(scanner.nextLine());

        System.out.print("Unit Price: ");
        BigDecimal price =
                new BigDecimal(scanner.nextLine());

        System.out.print("Unit Cost: ");
        BigDecimal cost =
                new BigDecimal(scanner.nextLine());

        System.out.print("Customer Type: ");
        String customerType = scanner.nextLine();

        System.out.print("Payment Method: ");
        String payment = scanner.nextLine();

        Sale sale = new Sale(
                id,
                date,
                product,
                category,
                region,
                quantity,
                price,
                cost,
                customerType,
                payment
        );

        if (service.addSale(sale)) {

            System.out.println(
                    "Sale added successfully!"
            );
        }
    }

    private static void viewSales()
            throws Exception {

        List<Sale> sales =
                service.getAllSales();

        if (sales.isEmpty()) {

            System.out.println(
                    "No sales available."
            );

            return;
        }

        for (Sale sale : sales) {

            System.out.println(sale);
        }
    }

    private static void searchSale()
            throws Exception {

        System.out.print("Enter Sale ID: ");

        int id =
                Integer.parseInt(
                        scanner.nextLine()
                );

        Sale sale =
                service.getSale(id);

        if (sale == null) {

            System.out.println(
                    "Sale not found."
            );

        } else {

            System.out.println(sale);
        }
    }

    private static void deleteSale()
            throws Exception {

        System.out.print("Enter Sale ID: ");

        int id =
                Integer.parseInt(
                        scanner.nextLine()
                );

        if (service.deleteSale(id)) {

            System.out.println(
                    "Sale deleted successfully!"
            );

        } else {

            System.out.println(
                    "Sale not found."
            );
        }
    }

    private static void salesSummary()
            throws Exception {

        List<Sale> sales =
                service.getAllSales();

        BigDecimal totalSales =
                BigDecimal.ZERO;

        BigDecimal totalProfit =
                BigDecimal.ZERO;

        int totalQuantity = 0;

        for (Sale sale : sales) {

            totalSales =
                    totalSales.add(
                            sale.getSalesAmount()
                    );

            totalProfit =
                    totalProfit.add(
                            sale.getProfit()
                    );

            totalQuantity +=
                    sale.getQuantity();
        }

        System.out.println();
        System.out.println(
                "========= SALES SUMMARY ========="
        );

        System.out.println(
                "Total Orders : " + sales.size()
        );

        System.out.println(
                "Units Sold   : " + totalQuantity
        );

        System.out.println(
                "Total Sales  : ₹" + totalSales
        );

        System.out.println(
                "Total Profit : ₹" + totalProfit
        );

        if (totalSales.compareTo(
                BigDecimal.ZERO) > 0) {

            BigDecimal margin =
                    totalProfit
                            .multiply(
                                    BigDecimal.valueOf(100)
                            )
                            .divide(
                                    totalSales,
                                    2,
                                    java.math.RoundingMode.HALF_UP
                            );

            System.out.println(
                    "Profit Margin: " + margin + "%"
            );
        }
    }
}