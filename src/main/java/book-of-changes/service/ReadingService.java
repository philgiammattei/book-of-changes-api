package bookofchanges.service;

import bookofchanges.model.Line;
import bookofchanges.model.Reading;

import java.util.ArrayList;

public interface ReadingService {
    public Reading getReading(ArrayList<Line> lines);
}
