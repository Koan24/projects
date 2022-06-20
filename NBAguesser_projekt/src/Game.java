import org.apache.commons.math3.util.Pair;
import java.util.List;
import java.util.Random;

public class Game {
    private Random _rand;
    public List<Player> players;
    public Player guessedPlayer;
    public int numberoftakes;
    public Game(List<Player> p)
    {
        players = p;
        _rand = new Random();
        numberoftakes = 0;
    }

    public void SelectRandomPlayer()
    {
        guessedPlayer = players.get(_rand.nextInt(players.size()));
        numberoftakes = 0;
        System.out.println(guessedPlayer);
    }

    public Pair<Boolean, String> Guess(String name)
    {
        numberoftakes++;
        if(name.equals(guessedPlayer.Name.toLowerCase()))
        {
            return new Pair<>(true, guessedPlayer.toString());
        }
        else
        {
            Player givenPlayer = Getplayer(name);
            if(givenPlayer == null)
            {
                System.out.println("This player does not exist or has already retired");
                numberoftakes--;
                return new Pair<>(false, "");
            }
            String res = givenPlayer.Name + " ";
            res += CompareTeam(givenPlayer)+ " ";
            res += CompareConference(givenPlayer)+ " ";
            res += CompareDivision(givenPlayer)+ " ";
            res += ComparePosition(givenPlayer)+ " ";
            res += CompareHeight(givenPlayer)+ " ";
            res += CompareAge(givenPlayer)+ " ";
            res += CompareJerseyNr(givenPlayer)+ "<br>";
            return new Pair<>(false, res);
        }

    }
    public String CompareTeam(Player player)
    {
        String res = "";
        if(player.Team == guessedPlayer.Team)
        {
            return "<font color='green'>" + player.Team + "</font>";
        }
        else
        {
            return "<font color='red'>" + player.Team + "</font>";
        }
    }
    public String CompareConference(Player player)
    {
        if(player.Conf == guessedPlayer.Conf)
        {
            return "<font color='green'>" + player.Conf + "</font>";
        }
        else
        {
            return "<font color='red'>" + player.Conf + "</font>";
        }
    }
    public String CompareDivision(Player player)
    {
        if(player.Div == guessedPlayer.Div)
        {
            return "<font color='green'>" + player.Div + "</font>";
        }
        else
        {
            return "<font color='red'>" + player.Div + "</font>";
        }
    }
    public String ComparePosition(Player player)
    {
        if(player.Pos == guessedPlayer.Pos)
        {
            return "<font color='green'>" + player.Pos + "</font>";
        }
        else
        {
            return "<font color='red'>" + player.Pos + "</font>";
        }
    }
    public String CompareJerseyNr(Player player)
    {
        if(player.Jersey_Nr == guessedPlayer.Jersey_Nr)
        {
            return "<font color='green'>" + player.Jersey_Nr + "</font>";
        }
        else if(player.Jersey_Nr > guessedPlayer.Jersey_Nr)
        {
           int difference = player.Jersey_Nr - guessedPlayer.Jersey_Nr;
           if(difference <= 2)
           {
               return "<font color='orange'>" + player.Jersey_Nr + "\\/" + "</font>";
           }
           else
           {
               return "<font color='red'>" + player.Jersey_Nr + "\\/" + "</font>";
           }
        }
        else
        {
            int difference = guessedPlayer.Jersey_Nr - player.Jersey_Nr;
            if(difference <= 2)
            {
                return "<font color='orange'>" + player.Jersey_Nr + "/|" + "</font>";
            }
            else
            {
                return "<font color='red'>" + player.Jersey_Nr + "/|" + "</font>";
            }
        }
    }
    public String CompareAge(Player player)
    {
        if(player.Age == guessedPlayer.Age)
        {
            return "<font color='green'>" + player.Age + "</font>";
        }
        else if(player.Age > guessedPlayer.Age)
        {
            int difference = player.Age - guessedPlayer.Age;
            if(difference <= 2)
            {
                return "<font color='orange'>" + player.Age + "\\/" + "</font>";
            }
            else
            {
                return "<font color='red'>" + player.Age + "\\/" + "</font>";
            }
        }
        else
        {
            int difference = guessedPlayer.Age - player.Age;
            if(difference <= 2)
            {
                return "<font color='orange'>" + player.Age + "/|" + "</font>";
            }
            else
            {
                return "<font color='red'>" + player.Age + "/|" + "</font>";
            }
        }
    }
    public String CompareHeight(Player player)
    {
        if(player.Height_Foot == guessedPlayer.Height_Foot && player.Height_Inch == guessedPlayer.Height_Inch)
        {
            return "<font color='green'>" + player.Height_Foot + "'" + player.Height_Inch + "''" + "</font>";
        }
        else if(player.Height_Foot > guessedPlayer.Height_Foot || (player.Height_Foot == guessedPlayer.Height_Foot && player.Height_Inch > guessedPlayer.Height_Inch))
        {
            int difference = ((player.Height_Foot * 12)+ player.Height_Inch) - ((guessedPlayer.Height_Foot * 12)+ guessedPlayer.Height_Inch);
            if(difference <= 2)
            {
                return "<font color='orange'>" + player.Height_Foot + "'" + player.Height_Inch + "''" + "\\/" + "</font>";
            }
            else
            {
                return "<font color='red'>" + player.Height_Foot + "'" + player.Height_Inch + "''" + "\\/" + "</font>";
            }
        }
        else
        {
            int difference = ((guessedPlayer.Height_Foot * 12)+ guessedPlayer.Height_Inch) - ((player.Height_Foot * 12)+ player.Height_Inch);
            if(difference <= 2)
            {
                return "<font color='orange'>" + player.Height_Foot + "'" + player.Height_Inch + "''" + "/|" + "</font>";
            }
            else
            {
                return "<font color='red'>" + player.Height_Foot + "'" + player.Height_Inch + "''" + "/|" + "</font>";
            }
        }
    }
    private Player Getplayer(String name)
    {
        for (int i = 0; i<players.size(); i++)
        {
            if(name.trim().equals(players.get(i).Name.trim().toLowerCase()))
            {
                return players.get(i);
            }
        }
        return null;
    }

}
