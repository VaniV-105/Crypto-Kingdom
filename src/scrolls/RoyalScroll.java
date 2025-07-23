package scrolls;

import java.io.Serializable;

public class RoyalScroll implements Serializable {
    private ScrollType operation;
    private CipherType algorithm;
    private String data;
    private String key;

    public RoyalScroll(ScrollType operation, CipherType algorithm, String data, String key) {
        this.operation = operation;
        this.algorithm = algorithm;
        this.data = data;
        this.key = key;
    }

    public ScrollType getOperation() {
        return operation;
    }

    public CipherType getAlgorithm() {
        return algorithm;
    }

    public String getData() {
        return data;
    }

    public String getKey() {
        return key;
    }
}
