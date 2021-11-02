package bookofchanges.model;

import lombok.Data;
import lombok.Getter;

@Data
public class Line {
    @Getter
    private boolean isYang;
    @Getter
    private boolean isChangeLine;

    public Line(boolean isYang, boolean isChangeLine) {
        this.isYang = isYang;
        this.isChangeLine = isChangeLine;
    }

    public boolean isChangeLine() {
        return this.isChangeLine;
    }
}
