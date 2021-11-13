package bookofchanges.controller;

import bookofchanges.model.*;
import bookofchanges.service.CastingService;
import bookofchanges.service.HexagramService;
import bookofchanges.service.ReadingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import static java.lang.String.valueOf;

@Slf4j
@RestController
@RequestMapping(path="/api", produces="application/json")
@CrossOrigin(origins="*")
public class HexagramController {

    @Autowired
    CastingService castingService;

    @Autowired
    ReadingService readingService;

    @Autowired
    HexagramService hexagramService;

    @GetMapping("/new-reading")
    public Reading generateReading() {

        ArrayList<Line> castLines = castingService.castLines();

        // log for debugging purposes
        renderLines(castLines);

        // send castLines to readingService, get back reading

        Reading reading = readingService.getReading(castLines);

        // log reading/return as json when converted to REST
        return reading;
    }

    @GetMapping("/all-hexagrams")
    public Iterable<HexagramSummary> allHexagrams() {
        Iterable<HexagramSummary> allHexagrams = hexagramService.getAllHexagrams();

        return allHexagrams;
    }

    @GetMapping("/get-hexagram-by-number")
    public HexagramReading getHexagramByNumber(@RequestParam int hexagramNumber) {
        return hexagramService.getHexagramByNumber(hexagramNumber);
    }

    private void renderLines(ArrayList<Line> lines) {
        ArrayList<String> renderedLines = new ArrayList<String>();

        for(int i = 5; i > -1; i--) {
            Line line = lines.get(i);
            if (line.isYang() && line.isChangeLine()) {
                renderedLines.add("----O----");
                log.info("----O----");
            } else if (line.isYang() && !line.isChangeLine()) {
                renderedLines.add("---------");
                log.info("---------");
            } else if (!line.isYang() && line.isChangeLine()) {
                renderedLines.add("----x----");
                log.info("----x----");
            } else if (!line.isYang() && !line.isChangeLine()) {
                renderedLines.add("---- ----");
                log.info("---- ----");
            }

        }
        log.info(" ");
    }
}
