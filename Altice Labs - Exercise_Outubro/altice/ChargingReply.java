

public class ChargingReply {
    private int requestId;
    private String result;
    private float gsu;

    public ChargingReply(int requestId, String result, float gsu) {
        this.requestId = requestId;
        this.result = result;
        this.gsu = gsu;
    }

    public String toString() {
        return "ChargingReply: " +
                "requestId=" + requestId +
                ", result=" + result +
                ", gsu=" + gsu;
    }

    public int getRequestId() {
        return this.requestId;
    }

    public void setRequestId(int requestId) {
        this.requestId = requestId;
    }

    public String getResult() {
        return this.result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public float getGsu() {
        return this.gsu;
    }

    public void setGsu(float gsu) {
        this.gsu = gsu;
    }
}
