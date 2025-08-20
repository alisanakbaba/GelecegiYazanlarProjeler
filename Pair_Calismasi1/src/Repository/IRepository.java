package Repository;

import java.util.List;

public interface IRepository<T> {
    List<T> getAll();
    void add(T entity);
    void delete(T entity);
    void update(int id,T newEntity);
    T getById(int id);
}
