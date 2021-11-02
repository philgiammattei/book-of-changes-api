package bookofchanges.controller;

import bookofchanges.model.Line;
import bookofchanges.model.Reading;
import bookofchanges.service.CastingService;
import bookofchanges.service.ReadingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/new-reading")
    public Reading generateReading(Model model) {

        ArrayList<Line> castLines = castingService.castLines();

        // log for debugging purposes
        renderLines(castLines);

        // send castLines to readingService, get back reading

        Reading reading = readingService.getReading(castLines);

        // log reading/return as json when converted to REST
        return reading;
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
