
import java.sql.Timestamp;







public class CDR {
    private Timestamp timeStamp;
    private String msisdn;
    private char serviceId;
    private ChargingRequest chargingRequest;
    private ChargingReply chargingReply;
    private float bucket1Value;
    private float bucket2Value;
    private float bucket3Value;
    private float counterAValue;
    private float counterBValue;
    private float counterCValue;
    private Timestamp counterDValue;
    private String appliedTariffId;

    public CDR(Timestamp timeStamp, String msisdn, char serviceId, ChargingRequest chargingRequest,
            ChargingReply chargingReply, float bucket1Value, float bucket2Value, float bucket3Value,
            float counterAValue, float counterBValue, float counterCValue, Timestamp counterDValue,
            String appliedTariffId) {
        this.timeStamp = timeStamp;
        this.msisdn = msisdn;
        this.serviceId = serviceId;
        this.chargingRequest = chargingRequest;
        this.chargingReply = chargingReply;
        this.bucket1Value = bucket1Value;
        this.bucket2Value = bucket2Value;
        this.bucket3Value = bucket3Value;
        this.counterAValue = counterAValue;
        this.counterBValue = counterBValue;
        this.counterCValue = counterCValue;
        this.counterDValue = counterDValue;
        this.appliedTariffId = appliedTariffId;
    }

    public String toString() {
        return "CDR: " +
                "timeStamp=" + timeStamp +
                ", msisdn=" + msisdn +
                ", serviceId=" + serviceId +
                ", bucket1Value=" + bucket1Value +
                ", bucket2Value=" + bucket2Value +
                ", bucket3Value=" + bucket3Value +
                ", counterAValue=" + counterAValue +
                ", counterBValue=" + counterBValue +
                ", counterCValue=" + counterCValue +
                ", counterDValue=" + counterDValue +
                ", appliedTariffId='" + appliedTariffId + "\n"
                + chargingRequest + "\n"
                + chargingReply + "\n";
    }

    public Timestamp getTimeStamp() {
        return this.timeStamp;
    }

    public void setTimeStamp(Timestamp timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getMsisdn() {
        return this.msisdn;
    }

    public void setMsisdn(String msisdn) {
        this.msisdn = msisdn;
    }

    public char getServiceId() {
        return this.serviceId;
    }

    public void setServiceId(char serviceId) {
        this.serviceId = serviceId;
    }

    public ChargingRequest getChargingRequest() {
        return this.chargingRequest;
    }

    public void setChargingRequest(ChargingRequest chargingRequest) {
        this.chargingRequest = chargingRequest;
    }

    public ChargingReply getChargingReply() {
        return this.chargingReply;
    }

    public void setChargingReply(ChargingReply chargingReply) {
        this.chargingReply = chargingReply;
    }

    public float getBucket1Value() {
        return this.bucket1Value;
    }

    public void setBucket1Value(float bucket1Value) {
        this.bucket1Value = bucket1Value;
    }

    public float getBucket2Value() {
        return this.bucket2Value;
    }

    public void setBucket2Value(float bucket2Value) {
        this.bucket2Value = bucket2Value;
    }

    public float getBucket3Value() {
        return this.bucket3Value;
    }

    public void setBucket3Value(float bucket3Value) {
        this.bucket3Value = bucket3Value;
    }

    public float getCounterAValue() {
        return this.counterAValue;
    }

    public void setCounterAValue(float counterAValue) {
        this.counterAValue = counterAValue;
    }

    public float getCounterBValue() {
        return this.counterBValue;
    }

    public void setCounterBValue(float counterBValue) {
        this.counterBValue = counterBValue;
    }

    public float getCounterCValue() {
        return this.counterCValue;
    }

    public void setCounterCValue(float counterCValue) {
        this.counterCValue = counterCValue;
    }

    public Timestamp getCounterDValue() {
        return this.counterDValue;
    }

    public void setCounterDValue(Timestamp counterDValue) {
        this.counterDValue = counterDValue;
    }

    public String getAppliedTariffId() {
        return this.appliedTariffId;
    }

    public void setAppliedTariffId(String appliedTariffId) {
        this.appliedTariffId = appliedTariffId;
    }
}
