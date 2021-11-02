package bookofchanges.data;

import bookofchanges.model.Hexagram;
import bookofchanges.model.Line;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Repository
public class JdbcHexagramRepository implements HexagramRepository {
    private JdbcTemplate jdbc;

    @Autowired
    public JdbcHexagramRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    @Override
    public Hexagram findByLines(List<Line> lines) {
        boolean lineOneYang = lines.get(0).isYang();
        log.info("line 1: " + (lineOneYang ? "yang" : "yin"));
        boolean lineTwoYang = lines.get(1).isYang();
        log.info("line 2: " + (lineTwoYang ? "yang" : "yin"));
        boolean lineThreeYang = lines.get(2).isYang();
        log.info("line 3: " + (lineThreeYang ? "yang" : "yin"));
        boolean lineFourYang = lines.get(3).isYang();
        log.info("line 4: " + (lineFourYang ? "yang" : "yin"));
        boolean lineFiveYang = lines.get(4).isYang();
        log.info("line 5: " + (lineFiveYang ? "yang" : "yin"));
        boolean lineSixYang = lines.get(5).isYang();
        log.info("line 6: " + (lineSixYang ? "yang" : "yin"));

        return jdbc.queryForObject("select HEXAGRAMNUMBER, HEXAGRAMCHINESENAME, HEXAGRAMENGLISHNAME, HEXAGRAMEXPLANATION, LINEONEYANG, LINETWOYANG, LINETHREEYANG, LINEFOURYANG, LINEFIVEYANG, LINESIXYANG, LINEONEEXPLANATION, LINETWOEXPLANATION, LINETHREEEXPLANATION, LINEFOUREXPLANATION, LINEFIVEEXPLANATION, LINESIXEXPLANATION from HEXAGRAMS where LINEONEYANG=? and LINETWOYANG=? and LINETHREEYANG=? and LINEFOURYANG=? and LINEFIVEYANG=? and LINESIXYANG=?",
                this::mapRowToHexagram, lineOneYang, lineTwoYang, lineThreeYang, lineFourYang, lineFiveYang, lineSixYang);
    }

    private Hexagram mapRowToHexagram(ResultSet rs, int rowNum) throws SQLException {

        Hexagram hex = new Hexagram();

        hex.setHexagramNumber(rs.getInt("HEXAGRAMNUMBER"));
        hex.setEnglishName(rs.getString("HEXAGRAMENGLISHNAME"));
        hex.setChineseName(rs.getString("HEXAGRAMCHINESENAME"));
        hex.setHexagramExplanation(rs.getString("HEXAGRAMEXPLANATION"));

        ArrayList<Boolean> yangs = new ArrayList<Boolean>();
        yangs.add(rs.getBoolean("LINEONEYANG"));
        yangs.add(rs.getBoolean("LINETWOYANG"));
        yangs.add(rs.getBoolean("LINETHREEYANG"));
        yangs.add(rs.getBoolean("LINEFOURYANG"));
        yangs.add(rs.getBoolean("LINEFIVEYANG"));
        yangs.add(rs.getBoolean("LINESIXYANG"));
        hex.setLineYangValues(yangs);

        ArrayList<String> lineExplanations = new ArrayList<String>();
        lineExplanations.add(rs.getString("LINEONEEXPLANATION"));
        lineExplanations.add(rs.getString("LINETWOEXPLANATION"));
        lineExplanations.add(rs.getString("LINETHREEEXPLANATION"));
        lineExplanations.add(rs.getString("LINEFOUREXPLANATION"));
        lineExplanations.add(rs.getString("LINEFIVEEXPLANATION"));
        lineExplanations.add(rs.getString("LINESIXEXPLANATION"));
        hex.setLineExplanations(lineExplanations);

        return hex;
    }
}
