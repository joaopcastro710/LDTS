package feup.ldts.trex.model.leaderboard;

import com.googlecode.lanterna.screen.Screen;
import feup.ldts.trex.controller.leaderboard.LeaderboardController;
import feup.ldts.trex.view.leaderboard.LeaderboardViewer;

import java.io.*;
import java.util.*;

public class Leaderboard {

    String file ="src/main/resources/Leaderboard/scoreLeaders.txt";

    LeaderboardViewer viewer = new LeaderboardViewer();
    LeaderboardController controller = new LeaderboardController();

    public void showTop6(Screen screen) throws Exception {

        List<String> allLines = new ArrayList<>(Collections.emptyList());

        BufferedReader reader = new BufferedReader(new FileReader(file));
        int i=0;
        String line=reader.readLine();
        while (line!=null){
            allLines.add(i, line);
            i++;
            line=reader.readLine();
        }
        reader.close();

        allLines.sort((line1, line2) -> {
            int score1 = Integer.parseInt(line1.split(" ")[line1.split(" ").length - 1]);
            int score2 = Integer.parseInt(line2.split(" ")[line2.split(" ").length - 1]);
            return Integer.compare(score2, score1);
        });

        int numLines = Math.min(6, allLines.size());
        List<String> top6 = allLines.subList(0, numLines);

        viewer.displayLeaderboard(screen, top6);
        boolean next = false;
        while(!next){
            viewer.displayLeaderboard(screen,top6);
            next = controller.processKey(controller.nextAction(screen));
        }
    }

    public void addNewLeader(String player, int score) {
        try {
            String newLeader = player + " " + score;

            List<String> allLines = new ArrayList<>(Collections.emptyList());

            BufferedReader reader = new BufferedReader(new FileReader(file));
            int i=0;
            String line=reader.readLine();
            while (line!=null){
                allLines.add(i, line);
                i++;
                line = reader.readLine();
            }
            reader.close();

            writeNewPosition(allLines, score, newLeader);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeNewPosition(List<String> allLines, int score, String newLeader) throws IOException {
        int insertIndex = 0;
        for (String line2 : allLines) {
            String[] tokens = line2.split(" ");
            if (Integer.parseInt(tokens[tokens.length - 1]) < score) {
                break;
            }
            insertIndex++;
        }
        allLines.add(insertIndex, newLeader);

        BufferedWriter writer = new BufferedWriter(new FileWriter(file));
        for (int i=0;i<allLines.size();i++){
            writer.write(allLines.get(i)+"\n");
        }
        writer.close();
    }
}
