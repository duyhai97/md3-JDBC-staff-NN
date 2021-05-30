package service.jdbccategory;

import model.Category;
import model.Staff;

import java.util.List;

public interface CategoryJDBCInterface {

    List<Category> selectAll();

    Category selectById(int id);

    void create(Category category);

    void update (int id,Category category);

    void delete(int id);

}
