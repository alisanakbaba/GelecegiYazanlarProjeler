package Service;

import Entity.Customer;
import Entity.Product;
import Repository.ProductRepository;

import java.util.List;

public class ProductService implements IService<Product>{

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public void add(Product entity) {
        productRepository.add(entity);
    }

    @Override
    public void delete(Product entity) {
        productRepository.delete(entity);
    }

    @Override
    public void update(int id, Product entity) {
        productRepository.update(id,entity);
    }

    @Override
    public List<Product> getAll() {
        return productRepository.getAll();
    }

    @Override
    public Product getById(int id) {
        return productRepository.getById(id);
    }

    public void infoProduct(int productId){
        productRepository.infoProduct(productId);
    }




}
