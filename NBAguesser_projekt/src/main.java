import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.apache.commons.math3.util.Pair;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import javax.swing.*;

public class main {
    static boolean isTraining = false;
    public static String historyString = "";
    public static void main(String[] args){
        List <Player> players = new ArrayList<>();
        try{
            players = readPlayers("src/NBA Stats 202122.xlsx");
        }
        catch (
                IOException ex){
            ex.printStackTrace();
            return;
        }
        Game game = new Game(players);
        JFrame frame = new JFrame("NBA Guesser");
        frame.setIconImage(new ImageIcon("src/pobrane.png").getImage());
        frame.setPreferredSize(new Dimension(768, 768));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JLabel entry_text = new JLabel("<html>Welcome to NBAGuesser!</html>", SwingConstants.CENTER);
        entry_text.setFont(new Font("Kdam Thmor Pro", Font.BOLD, 54));
        entry_text.setBounds(50, 50, 668, 150);
        frame.getContentPane().add(entry_text, BorderLayout.CENTER);

        JButton start_button = new JButton("START");
        start_button.setFont(new Font("Kdam Thmor Pro", Font.PLAIN, 38));
        start_button.setBounds(50, 250, 668, 100);
        frame.getContentPane().add(start_button);

        JButton instruction_button = new JButton("INSTRUCTIONS");
        instruction_button.setFont(new Font("Kdam Thmor Pro", Font.PLAIN, 38));
        instruction_button.setBounds(50, 550, 668, 100);
        frame.getContentPane().add(instruction_button);

        JLabel instructions = new JLabel("<html> You get eight guesses, try any current NBA player!<br><br>" +
                "<font color='green'>Green text</font> in any column indicates a match!<br><br>" +
                "<font color='orange'>Yellow text</font> in the team column indicates the mystery player at one point played for this team, but does not currently<br><br>" +
                "<font color='orange'>Yellow text</font> in the position column indicates a partial match to the mystery player's position<br><br>" +
                "<font color='orange'>Yellow text</font> in any other column indicates this attribute is within 2 (inches, years, numbers) of the mystery player</html>");
        instructions.setFont(new Font("Kdam Thmor Pro", Font.PLAIN, 25));
        instructions.setBounds(50, 50, 668, 500);
        frame.getContentPane().add(instructions);
        instructions.setVisible(false);

        JButton instructions_back = new JButton("BACK");
        instructions_back.setFont(new Font("Kdam Thmor Pro", Font.PLAIN, 38));
        instructions_back.setBounds(50, 550, 668, 100);
        frame.getContentPane().add(instructions_back);
        instructions_back.setVisible(false);


        JLabel game_text = new JLabel("<html>Type in the name of the mystery player.<br>" + "Remember, you only have eight guesses :D</html>", SwingConstants.CENTER);
        game_text.setFont(new Font("Kdam Thmor Pro", Font.PLAIN, 28));
        game_text.setBounds(50, 50, 668, 100);
        frame.getContentPane().add(game_text);
        game_text.setVisible(false);

        JTextField inputbox = new JTextField();
        inputbox.setFont(new Font("Kdam Thmor Pro", Font.PLAIN, 40));
        inputbox.setBounds(50, 150, 668, 100);
        frame.getContentPane().add(inputbox);
        inputbox.setVisible(false);

        JLabel history = new JLabel("");
        history.setFont(new Font("Kdam Thmor Pro", Font.PLAIN, 15));
        history.setBounds(50, 250, 668, 300);
        frame.getContentPane().add(history);
        history.setVisible(false);

        JButton game_back =  new JButton("BACK");
        game_back.setFont(new Font("Kdam Thmor Pro", Font.PLAIN, 38));
        game_back.setBounds(50, 550, 300, 100);
        frame.getContentPane().add(game_back);
        game_back.setVisible(false);

        JButton play_again = new JButton("PLAY AGAIN");
        play_again.setFont(new Font("Kdam Thmor Pro", Font.PLAIN, 38));
        play_again.setBounds(418, 550, 300, 100);
        frame.getContentPane().add(play_again);
        play_again.setVisible(false);
        play_again.setEnabled(false);

        JFrame popup = new JFrame("Confirm");
        popup.setIconImage(new ImageIcon("src/pobrane.png").getImage());
        popup.setBounds(100, 100, 300, 300);
        popup.setLayout(null);

        JLabel confirm_text = new JLabel("<html><p style='text-align:center;'>Do you really want to give up now?</p></html>", SwingConstants.CENTER);
        confirm_text.setFont(new Font("Kdam Thmor Pro", Font.BOLD, 18));
        confirm_text.setBounds(20, 10, 260, 50);
        popup.getContentPane().add(confirm_text, BorderLayout.CENTER);

        JButton yes_button = new JButton("Yes");
        yes_button.setBounds(50, 100, 80, 80);
        popup.getContentPane().add(yes_button);

        JButton no_button = new JButton("No");
        no_button.setBounds(170, 100, 80, 80);
        popup.getContentPane().add(no_button);

        JButton training_button = new JButton("TRAINING MODE");
        training_button.setBounds(50, 400, 668, 100);
        training_button.setFont(new Font("Kdam Thmor Pro", Font.PLAIN, 38));
        frame.getContentPane().add(training_button);

        training_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                isTraining = true;
                entry_text.setVisible(false);
                start_button.setVisible(false);
                instruction_button.setVisible(false);
                game_text.setVisible(true);
                inputbox.setVisible(true);
                history.setVisible(true);
                game_back.setVisible(true);
                play_again.setVisible(true);
                play_again.setEnabled(true);
                game.SelectRandomPlayer();
                inputbox.setEditable(true);
                training_button.setVisible(false);
                game_text.setText("<html>Type in the name of the mystery player.</html>");
            }
        });

        yes_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                entry_text.setVisible(true);
                start_button.setVisible(true);
                instruction_button.setVisible(true);
                game_text.setVisible(false);
                inputbox.setVisible(false);
                history.setVisible(false);
                history.setText("");
                game_back.setVisible(false);
                play_again.setVisible(false);
                historyString = "";
                inputbox.setText("");
                popup.setVisible(false);
                training_button.setVisible(true);
            }
        });
        no_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                popup.setVisible(false);
            }
        });

        game_back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                popup.setVisible(true);
            }
        });

        instruction_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                entry_text.setVisible(false);
                start_button.setVisible(false);
                instruction_button.setVisible(false);
                instructions.setVisible(true);
                instructions_back.setVisible(true);
                training_button.setVisible(false);
            }
        });
        instructions_back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                entry_text.setVisible(true);
                start_button.setVisible(true);
                instruction_button.setVisible(true);
                instructions.setVisible(false);
                instructions_back.setVisible(false);
                training_button.setVisible(true);
            }
        });
        start_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                isTraining = false;
                entry_text.setVisible(false);
                start_button.setVisible(false);
                instruction_button.setVisible(false);
                game_text.setVisible(true);
                inputbox.setVisible(true);
                history.setVisible(true);
                game_back.setVisible(true);
                play_again.setVisible(true);
                game.SelectRandomPlayer();
                inputbox.setEditable(true);
                training_button.setVisible(false);
                game_text.setText("<html>Type in the name of the mystery player.<br>" + "Remember, you only have eight guesses :D</html>");
            }
        });
        play_again.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                history.setText("");
                historyString = "";
                play_again.setEnabled(isTraining);
                inputbox.setEditable(true);
                game.SelectRandomPlayer();
            }
        });

        String input = "";
        boolean isGame_active = false;
        inputbox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String text = inputbox.getText();
                Pair<Boolean, String> pair = game.Guess(text.toLowerCase());
                if(pair.getFirst() == false)
                {
                    if(pair.getSecond() != "")
                    {
                        history.setText("<html>" + historyString + pair.getSecond() + "</html>");
                        historyString += pair.getSecond();
                        inputbox.setText("");
                    }
                    else
                    {
                        game_text.setText("This player does not exist or has already retired");
                    }
                }
                else
                {
                    game_text.setText("<html>Congratulations! You guessed the player. It took you " + game.numberoftakes + " takes." + "\nYour player was:" + "<font color='green'>" +
                    game.guessedPlayer + "</font></html>");
                    play_again.setEnabled(true);
                }
                if(game.numberoftakes >= 8 && isTraining == false)
                {
                    game_text.setText("<html> You used all 8 possible tries. Select 'PLAY AGAIN' button to try again. Good luck! </html>");
                    inputbox.setEditable(false);
                    play_again.setEnabled(true);
                }
            }
        });
        frame.setLayout(null);
        frame.pack();
        frame.setVisible(true);


    }
    private static Object getCellValue(Cell cell) {
        switch (cell.getCellType()) {
            case STRING:
                return cell.getStringCellValue();

            case BOOLEAN:
                return cell.getBooleanCellValue();

            case NUMERIC:
                return cell.getNumericCellValue();
        }

        return null;
    }
    public static List<Player> readPlayers(String excelFilePath) throws IOException {
        List<Player> listPlayers = new ArrayList<>();
        FileInputStream inputStream = new FileInputStream(new File(excelFilePath));

        Workbook workbook = new XSSFWorkbook(inputStream);
        Sheet firstSheet = workbook.getSheetAt(0);
        Iterator<Row> iterator = firstSheet.iterator();

        while (iterator.hasNext()) {
            Row nextRow = iterator.next();
            if(nextRow.getRowNum()==0 ){
                continue;
            }
            Iterator<Cell> cellIterator = nextRow.cellIterator();
            Player aPlayer = new Player();

            while (cellIterator.hasNext()) {
                Cell nextCell = cellIterator.next();
                int columnIndex = nextCell.getColumnIndex();

                switch (columnIndex) {
                    case 0:
                        aPlayer.Name = ((String) getCellValue(nextCell));
                        break;
                    case 1:
                        aPlayer.Team = (Team.valueOf((String)getCellValue(nextCell)));
                        break;
                    case 2:
                        aPlayer.Conf = (Conference.valueOf((String)getCellValue(nextCell)));
                        break;
                    case 3:
                        aPlayer.Div = (Division.valueOf((String)getCellValue(nextCell)));
                        break;
                    case 4:
                        aPlayer.Pos = (Positon.valueOf((String)getCellValue(nextCell)));
                        break;
                    case 5:
                        String height = ((String) getCellValue(nextCell));
                        aPlayer.Height_Foot = (Integer.parseInt(height.split("-")[0]));
                        aPlayer.Height_Inch = (Integer.parseInt(height.split("-")[1]));
                        break;
                    case 6:
                        aPlayer.Age = ((Double) getCellValue(nextCell)).intValue();
                        break;
                    case 7:
                        aPlayer.Jersey_Nr = ((Double) getCellValue(nextCell)).intValue();
                        break;
                }


            }
            listPlayers.add(aPlayer);
        }

        workbook.close();
        inputStream.close();

        return listPlayers;
    }
}
