/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

/**
 *
 * @author Hoang's PC
 */
public class Category {
    int cat_id;
    String cat_name;
    int sort;

    public Category() {
    }

    public Category(int cat_id, String cat_name, int sort) {
        this.cat_id = cat_id;
        this.cat_name = cat_name;
        this.sort = sort;
    }

    public int getCat_id() {
        return cat_id;
    }

    public void setCat_id(int cat_id) {
        this.cat_id = cat_id;
    }

    public String getCat_name() {
        return cat_name;
    }

    public void setCat_name(String cat_name) {
        this.cat_name = cat_name;
    }

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }

    @Override
    public String toString() {
        return this.cat_name;
    }
    
    
    
    
}
