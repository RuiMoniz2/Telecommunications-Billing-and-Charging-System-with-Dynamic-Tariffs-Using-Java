import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

public class SystemInterface {
   private ArrayList<BillingAccount> billingAccounts = new ArrayList();
   private ArrayList<CDR> cdrs = new ArrayList();
   private ArrayList<ChargingRequest> chargingRequests = new ArrayList();
   private ArrayList<ChargingReply> chargingReplies = new ArrayList();

   public SystemInterface() {
   }

   public void initializeData() {
      BillingAccount account1 = new BillingAccount("123456789", 100.0F, 200.0F, 300.0F, 99, 20, 30,
            new Timestamp(123, 9, 18, 12, 0, 0, 0), "Alfa1", "Beta1");

      BillingAccount account2 = new BillingAccount("987654321", 150.0F, 9.0F, 350.0F, 15, 25, 35,
            new Timestamp(123, 9, 18, 12, 0, 0, 0), "Alfa2", "Beta2");

      BillingAccount account3 = new BillingAccount("962459956", 150.0F, 250.0F, 30.3f, 15, 25, 35,
            new Timestamp(123, 9, 18, 12, 0, 0, 0), "Alfa3", "Beta3");
      this.addBillingAccount(account1);
      this.addBillingAccount(account2);
      this.addBillingAccount(account3);
   }

   public void processRequest(ChargingRequest request) {
      //TENTA ENCONTRAR A CONTA REFERENTE AO MSISDN
      BillingAccount account = this.findBillingAccount(request.getMsisdn());
      if (account == null) {
         System.out.println("A conta nao existe!");
      } else {
         Date date = new Date();
         Timestamp today = new Timestamp(date.getTime());
         account.setCounterD(today);
         if (request.getRoaming()) {
            account.setCounterC(account.getCounterC() + 1);
         }

         CDR cdr;
         ChargingReply reply;
         //DETERMINA QUAL TARIFÁRIO É PARA APLICAR E PROCESSA O REQUEST, POR FIM ADICINA OS REGISTOS NAS TABELAS DE DADOS
         if (request.getService() == 'A') {
            switch (account.getTariffServiceA()) {
               case "Alfa1":
                  Alpha1Tariff alfa1 = new Alpha1Tariff();
                  reply = alfa1.processRequest(request, account);
                  account.setCounterA((int) Math.floor(account.getCounterA() + reply.getGsu()));
                  cdr = new CDR(request.getTimeStamp(), request.getMsisdn(), request.getService(), request, reply,
                        account.getBucket1(), account.getBucket2(), account.getBucket3(), account.getCounterA(),
                        account.getCounterB(), account.getCounterC(), account.getCounterD(), "Alfa1");
                  this.addChargingRequest(request);
                  this.addChargingReply(reply);
                  this.addCDR(cdr);
                  break;
               case "Alfa2":
                  Alpha2Tariff alfa2 = new Alpha2Tariff();
                  reply = alfa2.processRequest(request, account);
                  account.setCounterA((int) Math.floor(account.getCounterA() + reply.getGsu()));
                  cdr = new CDR(request.getTimeStamp(), request.getMsisdn(), request.getService(), request, reply,
                        account.getBucket1(), account.getBucket2(), account.getBucket3(), account.getCounterA(),
                        account.getCounterB(), account.getCounterC(), account.getCounterD(), "Alfa2");
                  this.addChargingRequest(request);
                  this.addChargingReply(reply);
                  this.addCDR(cdr);
                  break;
               case "Alfa3":
                  Alpha3Tariff alfa3 = new Alpha3Tariff();
                  reply = alfa3.processRequest(request, account);
                  account.setCounterA((int) Math.floor(account.getCounterA() + reply.getGsu()));
                  cdr = new CDR(request.getTimeStamp(), request.getMsisdn(), request.getService(), request, reply,
                        account.getBucket1(), account.getBucket2(), account.getBucket3(), account.getCounterA(),
                        account.getCounterB(), account.getCounterC(), account.getCounterD(), "Alfa3");
                  this.addChargingRequest(request);
                  this.addChargingReply(reply);
                  this.addCDR(cdr);
                  break;
               default:
                  System.out.println("O tarifario nao existe!");
            }
         } else if (request.getService() == 'B') {
            switch (account.getTariffServiceB()) {
               case "Beta1":
                  account.setCounterB(account.getCounterB() + 1);
                  Beta1Tariff beta1 = new Beta1Tariff();
                  reply = beta1.processRequest(request, account);
                  cdr = new CDR(request.getTimeStamp(), request.getMsisdn(), request.getService(), request, reply,
                        account.getBucket1(), account.getBucket2(), account.getBucket3(), account.getCounterA(),
                        account.getCounterB(), account.getCounterC(), account.getCounterD(), "Beta1");
                  this.addChargingRequest(request);
                  this.addChargingReply(reply);
                  this.addCDR(cdr);
                  break;
               case "Beta2":
                  Beta2Tariff beta2 = new Beta2Tariff();
                  reply = beta2.processRequest(request, account);
                  cdr = new CDR(request.getTimeStamp(), request.getMsisdn(), request.getService(), request, reply,
                        account.getBucket1(), account.getBucket2(), account.getBucket3(), account.getCounterA(),
                        account.getCounterB(), account.getCounterC(), account.getCounterD(), "Beta2");
                  this.addChargingRequest(request);
                  this.addChargingReply(reply);
                  this.addCDR(cdr);
                  break;
               case "Beta3":
                  Beta3Tariff beta3 = new Beta3Tariff();
                  reply = beta3.processRequest(request, account);
                  cdr = new CDR(request.getTimeStamp(), request.getMsisdn(), request.getService(), request, reply,
                        account.getBucket1(), account.getBucket2(), account.getBucket3(), account.getCounterA(),
                        account.getCounterB(), account.getCounterC(), account.getCounterD(), "Beta3");
                  this.addChargingRequest(request);
                  this.addChargingReply(reply);
                  this.addCDR(cdr);
                  break;
               default:
                  System.out.println("O tarifário nao existe!");
            }
         } else {
            System.out.println("O serviço que inseriu nao é válido Insira A ou B!!");
         }
      }
   }

   public int getRequestIdMax() {
      return this.chargingRequests.size() + 1;
   }

   public BillingAccount findBillingAccount(String msisdn) {
      for (BillingAccount account : billingAccounts) {
         if (account.getMsisdn().equals(msisdn)) {
            return account;
         }
      }
      return null;
   }

   public ArrayList<CDR> findBillingAccountCDRs(String msisdn) {
      ArrayList<CDR> result = new ArrayList<CDR>();
      for (CDR cdr : this.cdrs) {
         if (cdr.getMsisdn().equals(msisdn)) {
            result.add(cdr);
         }
      }
      return result;
   }

   public void addBillingAccount(BillingAccount account) {
      this.billingAccounts.add(account);
   }

   public void addCDR(CDR cdr) {
      this.cdrs.add(cdr);
   }

   public void addChargingRequest(ChargingRequest request) {
      this.chargingRequests.add(request);
   }

   public void addChargingReply(ChargingReply reply) {
      this.chargingReplies.add(reply);
   }

   public ArrayList<BillingAccount> getBillingAccounts() {
      return this.billingAccounts;
   }

   public ArrayList<CDR> getCDRs() {
      return this.cdrs;
   }

   public ArrayList<ChargingRequest> getChargingRequests() {
      return this.chargingRequests;
   }

   public ArrayList<ChargingReply> getChargingReplies() {
      return this.chargingReplies;
   }

   public void setBillingAccounts(ArrayList<BillingAccount> accounts) {
      this.billingAccounts = accounts;
   }

   public void setCDRs(ArrayList<CDR> cdrs) {
      this.cdrs = cdrs;
   }

   public void setChargingRequests(ArrayList<ChargingRequest> requests) {
      this.chargingRequests = requests;
   }

   public void setChargingReplies(ArrayList<ChargingReply> replies) {
      this.chargingReplies = replies;
   }

   public void printBillingAccounts() {
      for (BillingAccount account : billingAccounts) {
         System.out.println(account.toString());
      }
   }

   public void printCDRs() {
      for (CDR cdr : cdrs) {
         System.out.println(cdr.toString());
      }
   }

   public void printChargingRequests() {
      for (ChargingRequest request : chargingRequests) {
         System.out.println(request.toString());
      }
   }

   public void printChargingReplies() {
      for (ChargingReply reply : chargingReplies) {
         System.out.println(reply.toString());
      }
   }
}
