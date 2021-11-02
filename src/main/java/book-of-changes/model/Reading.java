package bookofchanges.model;

import lombok.Data;

@Data
public class Reading {
    private HexagramReading primaryHexagram;
    private HexagramReading changedHexagram;

    public Reading(HexagramReading primaryHexagram) {
        this.primaryHexagram = primaryHexagram;
    }

    public Reading(HexagramReading primaryHexagram, HexagramReading changedHexagram) {
        this.primaryHexagram = primaryHexagram;
        this.changedHexagram = changedHexagram;
    }
}
