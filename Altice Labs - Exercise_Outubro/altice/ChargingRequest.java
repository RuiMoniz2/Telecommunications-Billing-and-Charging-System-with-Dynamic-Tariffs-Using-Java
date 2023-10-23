import java.sql.Timestamp;




public class ChargingRequest {
    private int requestId;
    private Timestamp timeStamp;
    private char service;
    private boolean roaming;
    private String msisdn;
    private int rsu;

    public ChargingRequest(int requestId, Timestamp timeStamp, char service, boolean roaming, String msisdn, int rsu) {
        this.requestId = requestId;
        this.timeStamp = timeStamp;
        this.service = service;
        this.roaming = roaming;
        this.msisdn = msisdn;
        this.rsu = rsu;
    }

    public String toString() {
        return "ChargingRequest: " +
                "requestId=" + requestId +
                ", timeStamp=" + timeStamp +
                ", service=" + service +
                ", roaming=" + roaming +
                ", msisdn=" + msisdn +
                ", rsu=" + rsu;
    }

    public int getRequestId() {
        return this.requestId;
    }

    public void setRequestId(int requestId) {
        this.requestId = requestId;
    }

    public Timestamp getTimeStamp() {
        return this.timeStamp;
    }

    public void setTimeStamp(Timestamp timeStamp) {
        this.timeStamp = timeStamp;
    }

    public char getService() {
        return this.service;
    }

    public void setService(char service) {
        this.service = service;
    }

    public boolean getRoaming() {
        return this.roaming;
    }

    public void setRoaming(boolean roaming) {
        this.roaming = roaming;
    }

    public String getMsisdn() {
        return this.msisdn;
    }

    public void setMsisdn(String msisdn) {
        this.msisdn = msisdn;
    }

    public int getRsu() {
        return this.rsu;
    }

    public void setRsu(int rsu) {
        this.rsu = rsu;
    }
}