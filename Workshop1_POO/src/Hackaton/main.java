package Hackaton;

import java.util.Scanner;

public class main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingrese su usuario");
        String user = scanner.nextLine();
        System.out.print("Ingrese su contraseña");
        String password = scanner.nextLine();
        System.out.println("");


        String[] users = new String[]{"A", "B", "C", "D"};
        String[] userNames = new String[]{"Ana","Carlos","Juan","María"};
        String[] passwords = new String[]{"1","2","3","4"};
        int totalIncomes = 0;
        int totalExpenses = 0;
        int usersLen = users.length;

        for(int i = 0; i <= usersLen-1; i = i + 1) {
            if (users[i].equals(user) && passwords[i].equals(password)) {
                System.out.println("Hola " + userNames[i]);
                int incomes[] = new int[4];
                int expenses[] = new int[4];
                for (int j = 0; j <= 4 - 1; j = j + 1) {
                    incomes[j] = (int) (Math.random() * 1000000 + 1);
                    expenses[j] = (int) (Math.random() * 1000000 + 1);
                    System.out.println("Mes " + (j + 1) + ": ingreso " + incomes[j] + " egresos " + expenses[j]);
                    totalIncomes += incomes[j];
                    totalExpenses += expenses[j];
                }
            } else {
                continue;
            }
        }




        System.out.println("Los ingresos totales son "+ totalIncomes);
        System.out.println("Los egresos totales son "+ totalExpenses);
    }
}
