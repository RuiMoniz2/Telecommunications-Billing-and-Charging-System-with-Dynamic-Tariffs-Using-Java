
import java.util.Calendar;





class Beta1Tariff extends Tariff {
    @Override
    public String verifyEligibility(ChargingRequest request, BillingAccount account) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(request.getTimeStamp());
        int day = calendar.get(Calendar.DAY_OF_WEEK);
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        // System.out.println(day);
        System.out.println(hour);
        if (!(day == 1 || day == 7) || (hour > 19 || hour < 6)) {
            return "OK";
        } else {
            return "INVALID: Este serviço nao está disponível nesta data";
        }
    }
    @Override
    public float calculateRating(ChargingRequest request, BillingAccount account) {
        float rating = 0.0f;
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(request.getTimeStamp());
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        if ((hour > 19 || hour < 6) && !request.getRoaming()) {
            rating = 0.05f;
        } else {
            if (request.getRoaming()) {
                rating = 0.2f;
            } else {
                rating = 0.1f;
            }
        }

        if (account.getCounterA() > 10) {
            rating = rating - 0.025f;
        }
        if (account.getBucket3() > 50) {
            rating = rating - 0.01f;
        }
        return rating;
    }
    @Override
    public float doCharging(ChargingRequest request, BillingAccount account, float cost) {
        if (!request.getRoaming()) {
            // BUCKET1
            float gsu = (float) Math.floor((float) account.getBucket1() / cost);
            if (request.getRsu() <= gsu) {
                account.setBucket1(account.getBucket1() - (request.getRsu() * cost));
                return request.getRsu();
            } else {
                account.setBucket1(account.getBucket1() - (gsu * cost));
                return gsu;
            }

        }
        if (request.getRoaming() && account.getCounterB() > 5) {
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
        if (request.getRoaming()) {
            // BUCKET3
            float gsu = (float) Math.floor((float) account.getBucket3() / cost);
            account.setBucket3(account.getBucket3() - (gsu * cost));
            return gsu;
        }
        return 0;
    }
}
