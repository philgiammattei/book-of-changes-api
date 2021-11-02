package bookofchanges.data;

import bookofchanges.model.Hexagram;
import bookofchanges.model.Line;

import java.util.List;

public interface HexagramRepository {
    Hexagram findByLines(List<Line> lines);
}