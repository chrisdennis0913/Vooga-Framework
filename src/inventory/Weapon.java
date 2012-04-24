package inventory;

public interface Weapon {
    /**
     * Class for items that do damage. Has instance variable myDamage to
     * represent base damage value Weapons are in the form of
     * "Name, weapon, boolean forSale, int Price, int Damage" for example:
     * "Dagger, Weapon, true, 100, 55" or "Sword, Weapon, false, 85"
     * 
     * @author Chris Dennis
     */
//    private int myDamage;
//    protected String weaponType;

    public int getDamage ();


    public void setDamage (int damageValue);


    public String getWeaponType ();


    public void setWeaponType (String Type);
}
