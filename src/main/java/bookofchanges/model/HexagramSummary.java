package bookofchanges.model;

import lombok.Data;
import lombok.Getter;

import java.util.ArrayList;

@Data
public class HexagramSummary {
    private int hexagramNumber;
    private String chineseName;
    private String englishName;
    private ArrayList<Boolean> lineYangs;
}
