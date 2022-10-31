package Task3;
import java.util.HashMap;
import java.util.Scanner;

class Account {
    private Scanner sc;
    private String name;
    private int pin;
    private String userId;
    private String accNum;
    private HashMap<String, Integer> details;
    private HashMap<String, String> detailsName;
    private HashMap<String, String> transactionHistory;
    private HashMap<String, String> detailsAccNo;
    private HashMap<String, Integer> detailsBalance;
    private String checkUserId;
    private int checkPin;

    Account() {
        sc = new Scanner(System.in);
        details = new HashMap<>();
        detailsName = new HashMap<>();
        detailsBalance = new HashMap<>();
        detailsAccNo = new HashMap<>();
        transactionHistory = new HashMap<>();
    }

    public void register() {
        System.out.println("\n\t***** Registration *****");
        System.out.println("\nPlease enter your name : ");
        name = sc.nextLine();

        while (true) {
            System.out.println("Please enter your UserId : ");
            userId = sc.nextLine();

            if (!details.containsKey(userId)) {
                break;
            } else {
                System.out.println("Please enter valid userId!");
            }
        }

        while (true) {
            System.out.println("Please enter your Account number : ");
            accNum = sc.nextLine();

            if (!details.containsKey(accNum) && accNum != name && accNum != userId) {
                break;
            } else {
                System.out.println("Please enter valid Account number!");
            }
        }

        while (true) {
            System.out.println("Please enter your pin : ");
            pin = sc.nextInt();
            if (pin >= 1000) {
                break;
            } else {
                System.out.println("Your pin must be of 4 digits!");
            }
        }

        details.put(userId, pin);
        detailsName.put(userId, name);
        detailsAccNo.put(accNum, userId);
        sc.nextLine();

        System.out.println("\nRegistration Successful..!");
        System.out.println("--------------------------------------------");
    }

    public void logIn() {
        Boolean exit = true;
        int attempts = 0;

        System.out.println("\n\t***** LogIn *****");
        while (exit) {
            System.out.println("\nPlease enter your UserId : ");
            checkUserId = sc.nextLine();
            attempts++;

            if (!details.containsKey(checkUserId)) {
                if (attempts == 3) {
                    System.out.println("Many Attempts!");
                }
                System.out.println("Please enter a valid UserId!");
            } else {
                exit = false;
            }
        }
        exit = true;
        attempts = 0;

        while (exit) {
            System.out.println("Please enter your pin : ");
            checkPin = sc.nextInt();
            attempts++;
            if (details.get(checkUserId) != checkPin) {
                if (attempts == 3) {
                    System.out.println("Many attempts!");
                }
                System.out.println("Please enter A valid Pin!");
            } else {
                exit = false;
                System.out.println("Welcome " + this.getName(checkUserId) + " ..");
            }
        }

        System.out.println("--------------------------------------------");
    }

    String getName(String id) {
        return detailsName.get(id);
    }

    public void withdraw() {
        System.out.println("\n\t***** Withdraw *****");
        System.out.println("\nPlease enter the amount you want to withdraw:");
        int amount = sc.nextInt();

        if (detailsBalance.get(checkUserId) != null && amount <= detailsBalance.get(checkUserId)) {
            detailsBalance.put(checkUserId, detailsBalance.get(checkUserId) - amount);

            String his = amount + "   withdrawn from your acccount\n";
            if (transactionHistory.get(checkUserId) == null) {
                transactionHistory.put(checkUserId, his);
            } else {
                his = transactionHistory.get(checkUserId) + his;
                transactionHistory.put(checkUserId, his);
            }
            System.out.println(amount + "  successfully withdrawn from your acccount..");
        } else {
            System.out.println("Balance is insufficient!");
            System.out.println("Please enter the valid amount.");

        }

        System.out.println("--------------------------------------------");
    }

    public void deposit() {
        System.out.println("\n\t***** Deposit *****");
        System.out.println("\nPlease enter amount you want to deposit:");
        int amount = sc.nextInt();
        String his = amount + "   Deposited to your acccount..\n";

        if (transactionHistory.get(checkUserId) == null) {
            transactionHistory.put(checkUserId, his);
        } else {
            his = transactionHistory.get(checkUserId) + his;
            transactionHistory.put(checkUserId, his);
        }

        System.out.println(amount + "  successfully deposited to your acccount..");

        if (detailsBalance.get(checkUserId) != null) {
            amount += detailsBalance.get(checkUserId);
        }

        detailsBalance.put(checkUserId, amount);

        System.out.println("--------------------------------------------");
    }

    public void transfer() {
        System.out.println("\n\t***** Transfer money to account *****");
        System.out.println("Please enter the account number you want to transfer the amount :");
        String accNum = sc.next();

        System.out.println("Please enter the amount :");
        int transferAmount = sc.nextInt();
        int receivedAmount = transferAmount;

        if (transferAmount <= detailsBalance.get(checkUserId) && detailsBalance.get(checkUserId) != null) {
            detailsBalance.put(checkUserId, detailsBalance.get(checkUserId) - transferAmount);
            String his = transferAmount + "   transferred from your acccount..\n";

            if (transactionHistory.get(checkUserId) == null) {
                transactionHistory.put(checkUserId, his);
            } else {
                his = transactionHistory.get(checkUserId) + his;
                transactionHistory.put(checkUserId, his);
            }
            System.out.println(transferAmount + "  successfully transfered from your acccount..");
        } else {
            System.out.println("Balance is insufficient!");
            System.out.println("Please enter the valid amount.");
        }

        String id = detailsAccNo.get(accNum);

        if (detailsBalance.get(id) != null) {
            transferAmount += detailsBalance.get(checkUserId);
        }

        detailsBalance.put(id, transferAmount);
        String his = receivedAmount + "   received by other acccount..\n";

        if (transactionHistory.get(id) == null) {
            transactionHistory.put(id, his);
        } else {
            his = transactionHistory.get(id) + his;
            transactionHistory.put(id, his);
        }

        System.out.println("--------------------------------------------");

    }

    public void transactionHistory() {
        System.out.println("\n\t***** Transaction History *****");
        String history = transactionHistory.get(checkUserId);
        System.out.println(history);

        System.out.println("--------------------------------------------");
    }
}

public class ATM {
    public static void main(String[] args) {
        Account myAcc = new Account();
        try (Scanner sc = new Scanner(System.in)) {
            int choice;

            while (true) {
                System.out.println("\n\n\t******************** Welcome to ATM System ********************\n");
                System.out.println("--------------------------------------------\n->Press 1 to register your account.");
                System.out.println("->Press 2 to log in to your existing account.");
                System.out
                        .println("->Press 0 to exit from the system.\n--------------------------------------------\n");

                choice = sc.nextInt();

                if (choice == 1) {
                    myAcc.register();
                } else if (choice == 2) {
                    myAcc.logIn();
                } else {
                    System.out.println("Thank you for visiting..!");
                    break;
                }

                boolean exit = true;

                while (exit) {
                    System.out.println("\n\n1.Transaction History");
                    System.out.println("2.Withdraw");
                    System.out.println("3.Deposit");
                    System.out.println("4.Transfer");
                    System.out.println("5.Quit");

                    int ch = sc.nextInt();

                    switch (ch) {
                        case 1: {
                            myAcc.transactionHistory();
                            break;
                        }
                        case 2: {
                            myAcc.withdraw();
                            break;
                        }
                        case 3: {
                            myAcc.deposit();
                            break;
                        }
                        case 4: {
                            myAcc.transfer();
                            break;

                        }
                        default: {
                            exit = false;
                            System.out.println("Thank you for visiting..!");
                        }
                    }
                }
            }
        }

    }
}
