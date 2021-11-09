package bookofchanges.service;

import bookofchanges.model.HexagramSummary;

import java.util.ArrayList;

public interface HexagramService {
    public Iterable<HexagramSummary> getAllHexagrams();
}
