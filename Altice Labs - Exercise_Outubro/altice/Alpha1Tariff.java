
import java.util.Calendar;


public class Alpha1Tariff extends Tariff {
    @Override
    public String verifyEligibility(ChargingRequest request, BillingAccount account) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(request.getTimeStamp());
        int day = calendar.get(Calendar.DAY_OF_WEEK);
        // System.out.println(day);
        if (!(day == 1 || day == 7) && account.getCounterA() < 100) {
            return "OK";
        } else {
            if ((day == 1 || day == 7) && account.getCounterA() >= 100) {
                return "INVALID: Este serviço nao é permitido durante o fim de semana e você já atingiu o máximo de SU's no Serviço A";
            } else {
                if (day == 1 || day == 7) {
                    return "INVALID: Este serviço nao é permitido durante o fim de semana";
                }
                if (account.getCounterA() >= 100) {
                    return "INVALID: Você já atingiu o máximo de SU's no serviço A";
                }
            }
        }

        return "";
    }
    @Override
    public float calculateRating(ChargingRequest request, BillingAccount account) {
        float rating = 0.0f;
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(request.getTimeStamp());
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        if ((hour > 19 || hour < 6) && !request.getRoaming()) {
            rating = 0.5f;
        } else {
            if (request.getRoaming()) {
                rating = 2.0f;
            } else {
                rating = 1.0f;
            }
        }

        if (account.getCounterA() > 10) {
            rating = rating - 0.25f;
        }
        if (account.getBucket3() > 50) {
            rating = rating - 0.1f;
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
        return -1;
    }
}