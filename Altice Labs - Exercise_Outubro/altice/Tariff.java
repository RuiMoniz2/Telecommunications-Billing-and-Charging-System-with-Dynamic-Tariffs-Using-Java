public abstract class Tariff {
    public Tariff() {
    }

    public ChargingReply processRequest(ChargingRequest request, BillingAccount account) {
        //VERIFICA A ELEGIBILIDADE DO PEDIDO
        String eligibility = verifyEligibility(request, account);
        System.out.println("Eligibility: " + eligibility);
        if (!eligibility.equals("OK")) {
            return new ChargingReply(request.getRequestId(), eligibility, 0);

        } else {
            //CASO O PEDIDO SEJA VÁLIDO; CALCULA O CUSTO POR UNIDADE
            float cost = calculateRating(request, account);
            System.out.println("Cost: " + cost);
            float gsu = doCharging(request, account, cost);   //FAZ A COBRANÇA
            System.out.println("GSU: " + gsu);
            //CASO NAO HAJA CREDITO SUFICIENTE PARA TODOS OS RSU DÁ UM AVISO CREDIT LIMIT REACHED
            if (gsu != request.getRsu()) {
                System.out.print("CREDIT LIMIT REACHED || RSU: " + request.getRsu());
                return new ChargingReply(request.getRequestId(), "CreditLimitReached", gsu);
            } else {
                System.out.println("SUCCESS|| RSU: " + request.getRsu());
                return new ChargingReply(request.getRequestId(), "OK", gsu);
            }

        }
    }
    
    public abstract String verifyEligibility(ChargingRequest request, BillingAccount account);

    public abstract float calculateRating(ChargingRequest request, BillingAccount account);

    public abstract float doCharging(ChargingRequest request, BillingAccount account, float cost);

}
