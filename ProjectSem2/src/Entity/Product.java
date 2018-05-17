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
public class Product {

    private int pro_id, cat_id;
    private String pro_name;
    private float pro_price;
    private int pro_status;
    private String pro_desc;
    private int sort;

    public Product() {
    }

    public Product(int pro_id, int cat_id, String pro_name, float pro_price, int pro_status, String pro_desc, int sort) {
        this.pro_id = pro_id;
        this.cat_id = cat_id;
        this.pro_name = pro_name;
        this.pro_price = pro_price;
        this.pro_status = pro_status;
        this.pro_desc = pro_desc;
        this.sort = sort;
    }

    public Product(int cat_id, String pro_name, float pro_price, int pro_status, String pro_desc, int sort) {
        this.cat_id = cat_id;
        this.pro_name = pro_name;
        this.pro_price = pro_price;
        this.pro_status = pro_status;
        this.pro_desc = pro_desc;
        this.sort = sort;
    }

    public int getPro_id() {
        return pro_id;
    }

    public void setPro_id(int pro_id) {
        this.pro_id = pro_id;
    }

    public int getCat_id() {
        return cat_id;
    }

    public void setCat_id(int cat_id) {
        this.cat_id = cat_id;
    }

    public String getPro_name() {
        return pro_name;
    }

    public void setPro_name(String pro_name) {
        this.pro_name = pro_name;
    }

    public float getPro_price() {
        return pro_price;
    }

    public void setPro_price(float pro_price) {
        this.pro_price = pro_price;
    }

    public int getPro_status() {
        return pro_status;
    }

    public void setPro_status(int pro_status) {
        this.pro_status = pro_status;
    }

    public String getPro_desc() {
        return pro_desc;
    }

    public void setPro_desc(String pro_desc) {
        this.pro_desc = pro_desc;
    }

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }

    public String[] toArray() {
        return new String[]{
            String.valueOf(pro_id),
            this.pro_name,
        };
    }

    @Override
    public String toString() {
        return "Product{" + "pro_id=" + pro_id + ", cat_id=" + cat_id + ", pro_name=" + pro_name + ", pro_price=" + pro_price + ", pro_status=" + pro_status + ", pro_desc=" + pro_desc + ", sort=" + sort + '}';
    }
    
}
