package Repository;

import Entity.Customer;
import Entity.Order;

import java.util.ArrayList;
import java.util.List;

public class CustomerRepository implements IRepository<Customer> {

    List<Customer> customers = new ArrayList<>();

    @Override
    public List<Customer> getAll() {
        return this.customers;
    }

    @Override
    public void add(Customer entity) {
        boolean result = this.customers.add(entity);
        if (result){
            System.out.println("Müşteri eklendi.");
        }else{
            System.out.println("Müşteri eklenirken hata oluştu!");
            System.out.println("Result: "+result);
        }


    }

    @Override
    public void delete(Customer entity) {
        boolean result = this.customers.remove(entity);
        if (result){
            System.out.println("Müşteri silindi.");
        }else{
            System.out.println("Müşteri silinirken hata oluştu!");
            System.out.println("Result: "+result);

        }


    }

    @Override
    public void update(int id, Customer newEntity) {
        for (int i = 0; i < customers.size(); i++) {
            if (customers.get(i).getId() == id) {
                customers.set(i, newEntity);
                return;
            }
        }
        System.out.println("Bu id ye sahip entity bulunamadu");
    }

    @Override
    public Customer getById(int id) {
        return customers.stream()
                .filter(customer -> customer.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public void addOrderToOrderList(int customerId,Order order){

        getById(customerId).addOrder(order);
    }

    public void infoCustomer(int customerId){
        System.out.println("----------------Customer----------------------");
        System.out.println(
                "CustomerId: "+customerId+"\n" +
                        "Name: "    +getById(customerId).getName()+"\n"+
                        "Email: " +getById(customerId).getEmail()+"\n"+
                        "Phone: "  +getById(customerId).getPhone()+"\n"

        );
        for (Order order:getById(customerId).getOrders()){
            System.out.println(
                    "----------CustomerOrders-------------------\n"+
                    "OrderId: "+order.getId()+"\n" +
                    "TotalPrice: "    +order.getTotalPrice()+"\n"+
                    "CustomerId: " +order.getCustomerId()+"\n"+
                    "------------------------------------------");
        }
        System.out.println("--------------------------------------");
    }


}
