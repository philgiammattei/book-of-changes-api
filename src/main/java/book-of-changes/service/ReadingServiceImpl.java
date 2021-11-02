package bookofchanges.service;

import bookofchanges.data.HexagramRepository;
import bookofchanges.model.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Slf4j
@Service
public class ReadingServiceImpl implements ReadingService {

    private final HexagramRepository hexagramRepo;

    @Autowired
    public ReadingServiceImpl(HexagramRepository hexagramRepo) {
        this.hexagramRepo = hexagramRepo;
    }

    @Override
    public Reading getReading(ArrayList<Line> lines) {
        //query for first hexagram
        Hexagram primaryHexagram = hexagramRepo.findByLines(lines);


        //compose HexagramReading with Hexagram and change lines
        HexagramReading primaryHexagramReading = composeHexagramReading(primaryHexagram, lines);

        //detect change lines
        boolean isChangeLines = false;
        for(int i = 0; i < 6; i++) {
            if (lines.get(i).isChangeLine()) isChangeLines = true;
        }

        // when there's no change lines, just return the primary hexagram
        if(!isChangeLines) {
            return new Reading(primaryHexagramReading);
        }

        //resolve changeLines
        ArrayList<Line> changedLines = new ArrayList<>();

       for (int i = 0; i < lines.size(); i++){
           Line line = lines.get(i);

            changedLines.add(new Line(line.isChangeLine() ? !line.isYang() : line.isYang(), false));
        }

        // otherwise, get the change and return both
        Hexagram changedHexagram = hexagramRepo.findByLines(changedLines);

       // compose reading object with queried object and new lines
        HexagramReading changedHexagramReading = composeHexagramReading(changedHexagram, changedLines);
        return new Reading(primaryHexagramReading, changedHexagramReading);
    }

    private HexagramReading composeHexagramReading(Hexagram hexagram, ArrayList<Line> lines) {
        HexagramReading hexagramReading = new HexagramReading();

        hexagramReading.setHexagramNumber(hexagram.getHexagramNumber());
        hexagramReading.setChineseName(hexagram.getChineseName());
        hexagramReading.setEnglishName(hexagram.getEnglishName());
        hexagramReading.setHexagramExplanation(hexagram.getHexagramExplanation());

        ArrayList<LineReading> lineReadings = new ArrayList<LineReading>();
        for (int i = 0; i < 6; i++) {
            lineReadings.add(new LineReading(i + 1, lines.get(i).isYang(), lines.get(i).isChangeLine(), hexagram.getLineExplanations().get(i)));
        }

        hexagramReading.setLines(lineReadings);

        return hexagramReading;
    }
}
