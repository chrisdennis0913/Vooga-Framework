package inventory;

public interface Weapon {
    /**
     * Interface for items that do damage. Often requires instance variable
     * myDamage to represent base damage value. Should be equippable and often
     * distinguishable by type in order to use different attacks
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
