package bookofchanges.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class HexagramReading {
    private int hexagramNumber;
    private String chineseName;
    private String englishName;
    private String hexagramExplanation;
    private ArrayList<LineReading> lines;


}
