package Service;

import Entity.Customer;
import Repository.CustomerRepository;
import Repository.IRepository;

import java.util.List;

public class CustomerService implements IService<Customer>{

    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public void add(Customer entity) {
        customerRepository.add(entity);
    }

    @Override
    public void delete(Customer entity) {
        customerRepository.delete(entity);
    }

    @Override
    public void update(int id, Customer entity) {
        customerRepository.update(id,entity);
    }

    @Override
    public List<Customer> getAll() {
        return customerRepository.getAll();
    }

    @Override
    public Customer getById(int id) {
        return customerRepository.getById(id);
    }

    public void infoCustomer(int id){
        customerRepository.infoCustomer(id);
    }
}
