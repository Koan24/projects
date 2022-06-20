

public class Player {
    public String Name;
    public Team Team;
    public Conference Conf;
    public Division Div;
    public Positon Pos;
    public int Height_Foot;
    public int Height_Inch;
    public int Age;
    public int Jersey_Nr;
    public String Website;

    public Player(String n, Team t, Conference c,
                  Division d, Positon p, int hf, int hi,
                  int a, int jn) {
        Name = n;
        Team = t;
        Conf = c;
        Div = d;
        Pos = p;
        Height_Foot = hf;
        Height_Inch = hi;
        Age = a;
        Jersey_Nr = jn;
    }
    public Player(){

    }

    @Override
    public String toString() {
        return Name + " " + Team + " " + Conf + " " + Div + " " + Pos + " " + Height_Foot + "' " + Height_Inch + "'' " + Age + " " + Jersey_Nr;
    }
}

enum Team {
    DEN, PHI, PHO, BOS, OKC, SAC, ORL, IND, NYK, CHI, NOR,
    HOU, LAC, BRO, GOL, POR, LAL, MIN, WAS, SAN, TOR, CHA,
    MIA, DAL, MIL, ATL, UTA, MEM, CLE, DET

    }
enum Conference {
    East, West
}
enum Division {
    AT, CEN, SE, NW, PAC, SW
}
enum Positon {
    G, G_F, F, F_C, C, F_G, C_F
}