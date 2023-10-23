
import java.util.Calendar;


public class Alpha2Tariff extends Tariff {
    @Override
    public String verifyEligibility(ChargingRequest request, BillingAccount account) {
        if (request.getRoaming() || account.getBucket2() > 10) {
            
            return "INVALID: Este serviço nao é possivel com roaming ou você possui crédito a mais no Bucket 2";
        } else {
            return "OK";
        }

    }
    @Override
    public float calculateRating(ChargingRequest request, BillingAccount account) {
        float rating = 0.0f;
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(request.getTimeStamp());
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        if (hour > 19 || hour < 6) {
            rating = 0.25f;
        } else {
            rating = 0.5f;
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
        // BUCKET2
        float gsu = (float) Math.floor((float) account.getBucket2() / cost);
        if (request.getRsu() <= gsu) {
            
            account.setBucket2(account.getBucket2() - (request.getRsu() * cost));
            
            return request.getRsu();
        } else {
            
            account.setBucket2(account.getBucket2() - (gsu * cost));
            
            return gsu;
        }

    }
}