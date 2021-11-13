package bookofchanges.service;

import bookofchanges.model.HexagramReading;
import bookofchanges.model.HexagramSummary;


public interface HexagramService {
    public Iterable<HexagramSummary> getAllHexagrams();
    public HexagramReading getHexagramByNumber(int hexagramNumber);
}
