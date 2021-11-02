package bookofchanges.service;

import bookofchanges.model.Line;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class CastingServiceImpl implements CastingService {
    @Override
    public ArrayList<Line> castLines() {
        ArrayList<Line> lines = new ArrayList<Line>();

        for (int lineNumber = 0; lineNumber < 6; lineNumber++) {
            int numberOfStalks = 50;
            numberOfStalks -= 1;

            int firstResult = resolveSeparation(numberOfStalks);
            numberOfStalks -= firstResult;
            int secondResult = resolveSeparation(numberOfStalks);
            numberOfStalks -= secondResult;
            int thirdResult = resolveSeparation(numberOfStalks);

            int finalResult = (firstResult == 9 ? 2 : 3)
                    + (secondResult == 8 ? 2 : 3)
                    + (thirdResult == 8 ? 2 : 3);

            Line newLine = null;

            switch (finalResult) {
                case 6:
                    newLine = new Line(false, true);
                    break;
                case 7:
                    newLine = new Line(true, false);
                    break;
                case 8:
                    newLine = new Line(false, false);
                    break;
                case 9:
                    newLine = new Line(true, true);
                    break;

            }
            lines.add(newLine);

        }
        return lines;
    }

    private int resolveSeparation(int numberOfStalks) {
        int left = (int) (Math.ceil(Math.random() * (numberOfStalks - 2)) + 1);
        int right = numberOfStalks - left;

        int stalksBetweenRingAndLittleFingersOfLeftHand = 1;
        right -= stalksBetweenRingAndLittleFingersOfLeftHand;

        while (left > 4) {
            left -= 4;
        }
        int stalksBetweenMiddleAndRingFingersOfLeftHand = left;

        while(right > 4) {
            right -= 4;
        }
        int stalksBetweenIndexAndMiddleFingersOfLeftHand = right;

        return (
                stalksBetweenIndexAndMiddleFingersOfLeftHand +
                        stalksBetweenMiddleAndRingFingersOfLeftHand +
                        stalksBetweenRingAndLittleFingersOfLeftHand
        );
    }
}
