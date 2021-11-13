package bookofchanges.service;

import bookofchanges.data.HexagramRepository;
import bookofchanges.model.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;


@Slf4j
@Service
public class HexagramServiceImpl implements HexagramService {
    private final HexagramRepository hexagramRepo;

    @Autowired
    public HexagramServiceImpl(HexagramRepository hexagramRepo) {
        this.hexagramRepo = hexagramRepo;
    }

    @Override
    public Iterable<HexagramSummary> getAllHexagrams() {
        Iterable<HexagramSummary> hexagrams = hexagramRepo.getAllHexagrams();

        return hexagrams;
    }

    @Override
    public HexagramReading getHexagramByNumber(int hexagramNumber) {
        Hexagram hex =  hexagramRepo.getHexagramByNumber(hexagramNumber);

        return composeHexagramReading(hex);
    }


    private HexagramReading composeHexagramReading(Hexagram hexagram) {
        HexagramReading hexagramReading = new HexagramReading();

        hexagramReading.setHexagramNumber(hexagram.getHexagramNumber());
        hexagramReading.setChineseName(hexagram.getChineseName());
        hexagramReading.setEnglishName(hexagram.getEnglishName());
        hexagramReading.setHexagramExplanation(hexagram.getHexagramExplanation());

        ArrayList<LineReading> lineReadings = new ArrayList<LineReading>();
        for (int i = 0; i < 6; i++) {
            lineReadings.add(new LineReading(i + 1, hexagram.getLineYangValues().get(i), false, hexagram.getLineExplanations().get(i)));
        }

        hexagramReading.setLines(lineReadings);

        return hexagramReading;
    }

}
