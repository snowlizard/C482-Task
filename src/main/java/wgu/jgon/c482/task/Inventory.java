package wgu.jgon.c482.task;

/**
 *
 * @author Julian Gonzalez
 */

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Inventory {
    private static ObservableList<Part> allParts = FXCollections.observableArrayList();
    private static ObservableList<Product> allProducts = FXCollections.observableArrayList();
    
    /**
     * @param newPart - part
     * to add to parts list
     */
    public static void addPart(Part newPart){
        allParts.add(newPart);
    }
    
    /**
     * @param newProduct - product
     * to add to products list
     */
    public static void addProduct(Product newProduct){
        allProducts.add(newProduct);
    }
    
    /**
     * @param partId - integer
     * @return part if found by part id
     * returns null if not found
     */
    public static Part lookupPart(int partId){
        Part p = null;
        for(Part part: allParts){
            if(part.getId() == partId){
                p = part;
            }
        }
        
        return p;
    }
    
    /**
     * @param productId - integer
     * @return product if found
     * returns null if not found
     */
    public static Product lookupProduct(int productId){
        Product p = null;
        for(Product product : allProducts){
            if(product.getId() == productId){
                p = product;
            }
        }

        return p;
    }
    
    /**
     * @param partName - string
     * @return list of parts containing
     * the part name
     */
    public static ObservableList<Part> lookupPart(String partName){
        ObservableList<Part> parts = FXCollections.observableArrayList();
        for(Part part: allParts){
            if(part.getName().contains(partName)){
                parts.add(part);
            }
        }
        return parts;
    }
    
    /**
     * @param productName - String
     * @return list of products containing
     * product name
     */
    public static ObservableList<Product> lookupProduct(String productName){
       ObservableList<Product> products = FXCollections.observableArrayList();
       for(Product product : allProducts){
           if(product.getName().contains(productName)){
               products.add(product);
           }
       }
       return products;
    }
    
    /**
     * @param index - integer
     * @param selectPart - Part
     * Updates the part at the given index
     * with the given part
     */
    public static void updatePart(int index, Part selectPart){
        allParts.set(index, selectPart);
    }
    
    /**
     * @param index - integer
     * @param selectedProduct - Product
     * Updates the product at the given index
     * with the given product
     */
    public static void updateProduct(int index, Product selectedProduct){
        allProducts.set(index, selectedProduct);
    }
    
    /**
     * @param selectedPart - Part
     * @return true if part was removed
     */
    public static boolean deletePart(Part selectedPart){
        return allParts.remove(selectedPart);
    }
    
    /**
     * @param selectedProduct - Product
     * @return true if product was removed
     */
    public static boolean deleteProduct(Product selectedProduct){
        return allProducts.remove(selectedProduct);
    }
    
    /**
     * @return list of all parts 
     */
    public static ObservableList<Part> getAllParts(){
        return allParts;
    }
    
    /**
     * @return list of all products 
     */
    public static ObservableList<Product> getAllProducts(){
        return allProducts;
    }
}
