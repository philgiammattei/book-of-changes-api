package bookofchanges.data;

import bookofchanges.model.Hexagram;
import bookofchanges.model.HexagramSummary;
import bookofchanges.model.Line;

import java.util.ArrayList;
import java.util.List;

public interface HexagramRepository {
    Hexagram findByLines(List<Line> lines);
    Iterable<HexagramSummary> getAllHexagrams();
}