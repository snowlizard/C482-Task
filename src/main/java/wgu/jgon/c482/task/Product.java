package wgu.jgon.c482.task;

import javafx.collections.ObservableList;
import javafx.collections.FXCollections;
import wgu.jgon.c482.task.Part;

/**
 *
 * @author Julian Gonzalez
 */
public abstract class Product {
    private ObservableList<Part> associatedParts = FXCollections.observableArrayList();
    private int id;
    private String name;
    private double price;
    private int stock;
    private int min;
    private int max;
    
    public Product(int id, String name, double price, int stock, int min, int max){
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.min = min;
        this.max = max;
    }
    
    /**
     * @param id the id to set
     */
    public void setId(int id){
        this.id = id;
    }
    
    /**
     * 
     * @param name set the name 
     */
    public void setName(String name){
        this.name = name;
    }
    
    /**
     * 
     * @param price set the price 
     */
    public void setPrice(double price){
        this.price = price;
    }
    
    /**
     * 
     * @param stock set the stock
     */
    public void setStock(int stock){
        this.stock = stock;
    }
    
    /**
     * 
     * @param min set the min
     */
    public void setMin(int min){
        this.min = min;
    }
    
    /**
     * 
     * @param max set the max 
     */
    public void setMax(int max){
        this.max = max;
    }
    
    /**
     * 
     * @return the id 
     */
    public int getId(){
        return id;
    }
    
    /**
     * 
     * @return the name 
     */
    public String getName(){
        return name;
    }
    
    /**
     * 
     * @return the price 
     */
    public double getPrice(){
        return price;
    }
    
    /**
     * 
     * @return the stock 
     */
    public int getStock(){
        return stock;
    }
    
    /**
     * 
     * @return the min 
     */
    public int getMin(){
        return min;
    }
    
    /**
     * @return the max
     */
    public int getMax(){
        return max;
    }
    
    /**
     * 
     * @param part add part to product 
     */
    public void addAssociatedPart(Part part){
        associatedParts.add(part);
    }
    
    /**
     * 
     * @param selectedPart the part to delete
     * @return returns true if removed from product
     */
    public boolean deleteAssociatedPart(Part selectedPart){
        associatedParts.remove(selectedPart);
        
        if(associatedParts.contains(selectedPart)){
            return false;
        }
        return true;
    }
    
    /**
     * 
     * @return product list 
     */
    public ObservableList<Part> getAllAssociatedParts(){
        return associatedParts;
    }
}
