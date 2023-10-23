
import java.util.Calendar;




public class Beta2Tariff extends Tariff {
    @Override
    public String verifyEligibility(ChargingRequest request, BillingAccount account) {
        if (request.getRoaming() || account.getBucket2() > 10) {
            return "INVALID: Este serviço nao é possivel com roaming ou você nao possui crédito suficiente no Bucket 2";
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
        if ((hour > 19 || hour < 6) && !request.getRoaming()) {
            rating = 0.025f;
        } else {
            if (!request.getRoaming()) {
                rating = 0.05f;
            }
        }

        if (account.getCounterB() > 10) {
            rating = rating - 0.02f;
        }
        if (account.getBucket2() > 15) {
            rating = rating - 0.005f;
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
