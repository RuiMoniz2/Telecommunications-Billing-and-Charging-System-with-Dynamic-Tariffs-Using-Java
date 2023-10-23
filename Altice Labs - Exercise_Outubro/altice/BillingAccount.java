
import java.sql.Timestamp;





public class BillingAccount {
    private String msisdn;
    private float bucket1;
    private float bucket2;
    private float bucket3;
    private int counterA;
    private int counterB;
    private int counterC;
    private Timestamp counterD;
    private String tariffServiceA;
    private String tariffServiceB;

    public BillingAccount(String msisdn, float bucket1, float bucket2, float bucket3, int counterA, int counterB,
            int counterC, Timestamp counterD, String tariffServiceA, String tariffServiceB) {
        this.msisdn = msisdn;
        this.bucket1 = bucket1;
        this.bucket2 = bucket2;
        this.bucket3 = bucket3;
        this.counterA = counterA;
        this.counterB = counterB;
        this.counterC = counterC;
        this.counterD = counterD;
        this.tariffServiceA = tariffServiceA;
        this.tariffServiceB = tariffServiceB;
    }

    public String toString() {
        return "BillingAccount: " +
                "msisdn=" + msisdn +
                ", bucket1=" + bucket1 +
                ", bucket2=" + bucket2 +
                ", bucket3=" + bucket3 +
                ", counterA=" + counterA +
                ", counterB=" + counterB +
                ", counterC=" + counterC +
                ", counterD=" + counterD +
                ", tariffServiceA='" + tariffServiceA +
                ", tariffServiceB='" + tariffServiceB;
    }

    public String getMsisdn() {
        return this.msisdn;
    }

    public void setMsisdn(String msisdn) {
        this.msisdn = msisdn;
    }

    public float getBucket1() {
        return this.bucket1;
    }

    public void setBucket1(float bucket1) {
        this.bucket1 = bucket1;
    }

    public float getBucket2() {
        return this.bucket2;
    }

    public void setBucket2(float bucket2) {
        this.bucket2 = bucket2;
    }

    public float getBucket3() {
        return this.bucket3;
    }

    public void setBucket3(float bucket3) {
        this.bucket3 = bucket3;
    }

    public int getCounterA() {
        return this.counterA;
    }

    public void setCounterA(int counterA) {
        this.counterA = counterA;
    }

    public int getCounterB() {
        return this.counterB;
    }

    public void setCounterB(int counterB) {
        this.counterB = counterB;
    }

    public int getCounterC() {
        return this.counterC;
    }

    public void setCounterC(int counterC) {
        this.counterC = counterC;
    }

    public Timestamp getCounterD() {
        return this.counterD;
    }

    public void setCounterD(Timestamp counterD) {
        this.counterD = counterD;
    }

    public String getTariffServiceA() {
        return this.tariffServiceA;
    }

    public void setTariffServiceA(String tariffServiceA) {
        this.tariffServiceA = tariffServiceA;
    }

    public String getTariffServiceB() {
        return this.tariffServiceB;
    }

    public void setTariffServiceB(String tariffServiceB) {
        this.tariffServiceB = tariffServiceB;
    }
}
