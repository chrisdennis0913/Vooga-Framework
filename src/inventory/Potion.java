package inventory;


public interface Potion {
    /**
     * Interface for items that affect the hero's counters
     * 
     * @author Chris Dennis
     */
//    private int healthChange;
    
    public void setPotionValue(int value);
    public int getPotionValue();


//    public void use () {
//        wrapper.getCharacter().getCounters().get("health").decrease(1); // might break
//        wrapper.getCharacter()
//               .getCounters()
//               .get("health")
//               .increase(healthChange + 1);
//        remove(1);
//    }
}
