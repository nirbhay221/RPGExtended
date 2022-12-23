public abstract class Characters {
//handles characters like heroes and villains
    public String CharacterName ;
    public int MaxHP ,HP, XP ;
    public Characters(String Name , int MaxHP , int HP , int XP  )
    {
        this.CharacterName = Name;
        this.MaxHP = MaxHP;
        this.XP = XP;
        this.HP = HP ;
    }
    public Characters( )
    {

    }
    public abstract int Attack ();
    public abstract int Defend();
}
