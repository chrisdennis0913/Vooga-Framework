package inventory;

public interface EquipItemInterface {
    /**
     * basic interface for any item that needs switch interfaces or be equipped
     * by a game character whether they be the player, an enemy, or an npc
     */

    void use ();


    void equip ();


    void unequip ();


    void drop ();


    void setDroppable (boolean drop);


    boolean isDroppable ();


    boolean isEquipped ();


    boolean canBeEquipped ();


    void setEquippable (boolean equippable);

}
