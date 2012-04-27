package inventory;

public interface EquipItemInterface {
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
