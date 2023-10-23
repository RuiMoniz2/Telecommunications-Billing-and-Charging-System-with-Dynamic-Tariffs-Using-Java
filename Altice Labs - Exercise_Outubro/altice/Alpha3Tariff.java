
import java.util.Calendar;



public class Alpha3Tariff extends Tariff {
    @Override
    public String verifyEligibility(ChargingRequest request, BillingAccount account) {
        if (!request.getRoaming() || account.getBucket3() > 10) {
            
            return "INVALID: Este serviço nao é possivel com localmente e você possui cédito a mais no Bucket 3";
        } else {
             //System.out.println("TESTE VAVALID");
             //System.out.println(request.getRoaming());
             //System.out.println(account.getBucket3() );
            return "OK";
        }
    }
    @Override
    public float calculateRating(ChargingRequest request, BillingAccount account) {
        float rating = 0.0f;
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(request.getTimeStamp());
        int day = calendar.get(Calendar.DAY_OF_WEEK);
        if (day == 1 || day == 7) {
            rating = 0.25f;
        } else {
            rating = 1.0f;
        }
        if (account.getCounterB() > 10) {
            rating = rating - 0.2f;
        }
        if (account.getBucket2() > 15) {
            rating = rating - 0.05f;
        }
        return rating;
    }
    @Override
    public float doCharging(ChargingRequest request, BillingAccount account, float cost) {
        // BUCKET3
        float gsu = (float) Math.floor((float) account.getBucket3() / cost);
        if (request.getRsu() <= gsu) {
            account.setBucket3(account.getBucket3() - (request.getRsu() * cost));
            return request.getRsu();
        } else {
            account.setBucket3(account.getBucket3() - (gsu * cost));
            return gsu;
        }

    }
}
