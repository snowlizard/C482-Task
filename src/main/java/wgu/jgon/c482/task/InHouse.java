package wgu.jgon.c482.task;

/**
 *
 * @author Julian Gonzalez
 */

public class InHouse extends Part {
    private int machineId;
    
    public InHouse(int id, String name, double price, int stock, int min, int max, int machineId){
        super(id, name, price, stock, min, max);
        this.machineId = machineId;
    }
    
    /**
     * 
     * @param machineId set the machine id 
     */
    public void setMachineId(int machineId){
        this.machineId = machineId;
    }
    
    /**
     * 
     * @return get the machine id 
     */
    public int getMachineId (){
        return machineId;
    }
}