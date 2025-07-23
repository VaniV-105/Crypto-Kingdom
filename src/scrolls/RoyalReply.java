package scrolls;

import java.io.Serializable;

public class RoyalReply implements Serializable {
    private String result;
    private boolean success;
    private String errorMessage;

    public RoyalReply(String result, boolean success, String errorMessage) {
        this.result = result;
        this.success = success;
        this.errorMessage = errorMessage;
    }

    public String getResult() {
        return result;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
