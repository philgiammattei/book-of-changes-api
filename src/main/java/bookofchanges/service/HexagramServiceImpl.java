package bookofchanges.service;

import bookofchanges.data.HexagramRepository;
import bookofchanges.model.HexagramSummary;
import bookofchanges.model.Line;
import bookofchanges.model.Reading;
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

}
