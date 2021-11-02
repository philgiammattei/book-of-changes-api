package bookofchanges.model;

import lombok.Data;
import lombok.Getter;

@Data
public class LineReading {
    private int lineNumber;
    @Getter
    private boolean isYang;
    @Getter
    private boolean isChangeLine;
    private String explanation;

    public LineReading(int lineNumber, boolean isYang, boolean isChangeLine, String explanation) {
        this.lineNumber = lineNumber;
        this.isYang = isYang;
        this.isChangeLine = isChangeLine;
        this.explanation = explanation;
    }
}
