import java.util.ArrayList;

public class Market {
    public ArrayList<Weapons> WeaponMarket;
    public ArrayList<LightningSpells> LightningSpellsMarket;
    public ArrayList<IceSpells> IceSpellsMarket;
    public ArrayList<FireSpells> FireSpellsMarket;
    public ArrayList<Potions> PotionsMarket;
    public ArrayList<Armor> ArmorMarket;

   String Name ;
   int Cost , level , Damage , noHands, MP;
    public Market(String Name , int Cost , int level , int Damage , int noHands,int MP){//Handles the market for shopping for the heroes .
        this.Name = Name ;
        this.Cost = Cost;
        this.level = level;
        this.Damage= Damage;
        this.noHands = noHands;
        this.MP= MP;
        WeaponMarket = new ArrayList<Weapons>();
        LightningSpellsMarket = new ArrayList<LightningSpells>();
        IceSpellsMarket = new ArrayList<IceSpells>();
        FireSpellsMarket = new ArrayList<FireSpells>();
        PotionsMarket = new ArrayList<Potions>();
        ArmorMarket = new ArrayList<Armor>();
    }

    public Market() {

    }
}
