package service.jdbcstaff;

import model.Staff;

import java.util.List;

public interface StaffJDBCInterface {
    List<Staff> selectAll();

    Staff selectById(int id);

    void create(Staff staff, int[] category);

    void update (int id,Staff staff, int [] category);

    void delete(int id);


}
