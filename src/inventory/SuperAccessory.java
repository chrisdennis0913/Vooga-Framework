package inventory;

import app.RPGame;
import utils.JsonUtil.JSONItem;
import evented.EventedWrapper;

public class SuperAccessory extends Item implements Accessory, Weapon, Potion {

    public SuperAccessory (EventedWrapper<Item> wrapper, JSONItem item) {
        super(wrapper, item);
        // TODO Auto-generated constructor stub
    }

    @Override
    public void setPrice (int price) {
        // TODO Auto-generated method stub
    }

    @Override
    public int getPrice () {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public boolean isSellable () {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public void setSellable (boolean sell) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void use () {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void equip () {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void unequip () {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void drop () {
        // TODO Auto-generated method stub
        
    }

    @Override
    public boolean isEquipped () {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean canBeEquipped () {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public void setPotionValue (int value) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public int getPotionValue () {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public int getDamage () {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public void setDamage (int damageValue) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public String getWeaponType () {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void setWeaponType (String Type) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void setStatCategory (String statistic) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void setStatChange (int value) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public String getStatCategory () {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public int getStatChange () {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public void setRelPosition (double x, double y) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public int[] getRelPosition () {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void removeWhenUsed (int quantity) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public boolean isThisKindOfItem (String toParse) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public Item parseItem (RPGame game2, String toParse) {
        // TODO Auto-generated method stub
        return null;
    }

}
