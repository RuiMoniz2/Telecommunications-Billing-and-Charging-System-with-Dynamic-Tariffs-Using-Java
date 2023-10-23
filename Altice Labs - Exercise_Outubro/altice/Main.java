import java.util.Scanner;
import java.util.ArrayList;
import java.util.Date;
import java.sql.Timestamp;




public class Main {
    public static void main(String[] args) {
        //INSTANCIA A CLASSE INTERFACE SISTEMA E INICIALIZA ALGUNS DADOS DE TESTE
        SystemInterface system = new SystemInterface();
        system.initializeData();
        Scanner scanner = new Scanner(System.in);
        //CONSOLA DO UTILIZADOR
        System.out.println("---------------------- Bem Vindo --------------------------");
        while (true) {
            System.out.print("Insira o comando:");
            String input = scanner.nextLine();
            String[] command = input.split(" ");
            int size;
            switch (command[0]) {
                case "search_cdr":
                    size = command.length;
                    if (size != 2) {
                        System.out.println("Introduziu os parâmetros errados!");
                    } else {
                        ArrayList<CDR> cdrs = system.findBillingAccountCDRs(command[1]);
                        if (cdrs.size()==0) {
                            System.out.println("Nao existem registos com este MSISDN!");
                            break;
                        } else {
                           
                            for (CDR cdr : cdrs) {
                                System.out.println("----------------------------------------------");
                                System.out.println(cdr);
                             }
                        }
                    }
                    break;
                case "search":
                    size = command.length;
                    if (size != 2) {
                        System.out.println("Introduziu os parâmetros errados!");
                    } else {
                        BillingAccount account = system.findBillingAccount(command[1]);
                        if (account == null) {
                            System.out.println("Nao existe nenhum utilizador com este msdisn!");
                            break;
                        } else {
                            System.out.println(account);
                        }
                    }
                    break;
                case "list_cdr":
                    system.printCDRs();
                    break;
                case "list_requests":
                    system.printChargingRequests();
                    break;
                case "list_replies":
                    system.printChargingReplies();
                    break;
                case "list_accounts":
                    system.printBillingAccounts();
                    break;
                case "request":
                    size = command.length;
                    if (size != 5) {
                        System.out.println("Introduziu os parâmetros errados!");
                    } else {
                        String service = command[1];
                        if (!service.equals("A") && !service.equals("B")) {
                            System.out.println("Introduza um serviço válido (A ou B)!");
                        } else {
                            if (!command[2].equals("true") & !command[2].equals("false")) {
                                System.out.println("Introduza o roaming corretamente (true ou false)!");
                            } else {
                                boolean roaming = Boolean.parseBoolean(command[2]);
                                String msisdn = command[3];
                                int rsu = 0;
                                try {
                                    rsu = Integer.parseInt(command[4]);
                                    if (rsu <= 0) {
                                        System.out.println("Introduza um valor de RSU maior do que 0");
                                        break;
                                    }
                                } catch (NumberFormatException e) {
                                    System.out.println("Introduza um valor de RSU válido.");
                                    break;
                                }
                                Date today = new Date();
                                Timestamp todayTimestamp = new Timestamp(today.getTime());
                                ChargingRequest newRequest = new ChargingRequest(system.getRequestIdMax(),todayTimestamp, service.charAt(0), roaming, msisdn,rsu);
                                system.processRequest(newRequest);

                            }

                        }
                    }
                    break;
                case "exit":
                    System.out.println("O Sistema vai encerrar");
                    scanner.close();
                    return;
                default:
                    System.out.println("Input Inválido");
            }

        }

        // System.out.println(" \nBILLING ACCOUNS\n");
        // system.printBillingAccounts();
        // System.out.println("-------------------------------------------");
        // .out.println(" \nCHARGING REQUESTS\n");
        // system.printChargingRequests();
        // System.out.println("-------------------------------------------");
        // System.out.println(" \nCHARGING REPLYS\n");
        // system.printChargingReplies();
        // System.out.println("-------------------------------------------");
        // System.out.println(" \nCDRS\n");
        // system.printCDRs();
        // System.out.println("-------------------------------------------");
        // System.out.println(chargingRequest1);
        // System.out.println(tarifario.calculateRating(chargingRequest1,account2));
        // system.processRequest(chargingRequest1);
        // system.printBillingAccounts();

    }
}
