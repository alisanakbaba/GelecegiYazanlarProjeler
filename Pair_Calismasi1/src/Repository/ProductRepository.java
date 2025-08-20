package Repository;

import Entity.Product;

import java.util.ArrayList;
import java.util.List;

public class ProductRepository implements IRepository<Product>{

    List<Product> products = new ArrayList<>();

    @Override
    public List<Product> getAll() {
        return this.products;
    }

    @Override
    public void add(Product entity) {
        boolean result = this.products.add(entity);
        if (result){
            System.out.println("Ürün eklendi.");
        }else{
            System.out.println("Ürün eklenirken hata oluştu!");
            System.out.println("Result: "+result);

        }

    }

    @Override
    public void delete(Product product) {
        boolean result = this.products.remove(product);
        if (result){
            System.out.println("Ürün silindi.");
        }else{
            System.out.println("Ürün silinirken hata oluştu!");
            System.out.println("Result: "+result);
        };


    }

    @Override
    public void update(int id, Product newEntity) {
        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).getId() == id) {
                products.set(i, newEntity);
                return;
            }
        }
        System.out.println("Bu id ye sahip entity bulunamadu");

    }

    @Override
    public Product getById(int id) {
        return products.stream()
                .filter(product -> product.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public boolean stockControl(int productId,int quantity){
        return getById(productId).getStock()>quantity;
    }

    public void  reduceStock(int productId,int quantity){
        int newStock = getById(productId).getStock()-quantity;
        getById(productId).setStock(newStock);
    }

    public void infoProduct(int productId){
        System.out.println(
                "--------------Product-------------------\n"+
                "ProductId: "+productId+"\n"+
                        "Name: "+getById(productId).getName()+"\n"+
                "Price: "+getById(productId).getPrice()+"\n"+
                "Stock: "+getById(productId).getStock()+
                "-----------------------------------------");

    }
}
