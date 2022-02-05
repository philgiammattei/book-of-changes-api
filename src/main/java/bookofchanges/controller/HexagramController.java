package bookofchanges.controller;

import bookofchanges.model.*;
import bookofchanges.service.CastingService;
import bookofchanges.service.HexagramService;
import bookofchanges.service.ReadingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@Slf4j
@RestController
@RequestMapping(path="/api/new-reading", produces="application/json")
@CrossOrigin(origins="*")
public class HexagramController {

    @Autowired
    CastingService castingService;

    @Autowired
    ReadingService readingService;

    @Autowired
    HexagramService hexagramService;

    @PostMapping(consumes="application/json")
    public Reading generateReading(@RequestBody int[] randomNumbers) {

       /* int[] mockRandomNumbers = {101,255, 0,
                213,
                136,
                87,
                219,
                32,
                225,
                244,
                237,
                130,
                94,
                239,
                150,
                198,
                184,
                31};

        */

        double[][] formattedRandomNumbers = formatRandomNumbers(randomNumbers);


        ArrayList<Line> castLines = castingService.castLines(formattedRandomNumbers);

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

    // takes single array of 18 random integers and returns array 6 arrays of
    // 3 fractional random numbers
    private double[][] formatRandomNumbers(int[] inputNumbers) {
        double[][] processedNumbers = new double[6][3];

        int inputIndex = 0;

        for (int i = 0; i < 6; i++) {
            for(int j = 0; j < 3; j++) {
                // convert rand(0...<256) to (0...<1)
                double inputNumberDouble = inputNumbers[inputIndex];
                double fractionalRandomNumber = inputNumberDouble / 256.0;
                processedNumbers[i][j] = fractionalRandomNumber;
                inputIndex++;
            }
        }

        return processedNumbers;
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
