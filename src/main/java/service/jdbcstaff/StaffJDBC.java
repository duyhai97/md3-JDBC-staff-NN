package service.jdbcstaff;

import model.Category;
import model.Staff;
import service.connectdatabase.ConnectJDBC;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StaffJDBC implements StaffJDBCInterface{

    public static final String SELECT_ALL = "select s.id, s.name, address, c.id as id_category,c.name as name_category from connect\n" +
            "join category c on c.id = connect.id_category\n" +
            "join staff s on s.id = connect.id_staff; ";
    public static final String SELECT_STAFF_BY_ID = "select *  from staff where id = ?";
    public static final String INSERT_NEW_STAFF = "insert staff (name, address) value " +
            "(?,?)";
    public static final String INSERT_NEW_STAFF_CATEGORY = "insert connect (id_staff,id_category)" +
            "value (?,?)";
    public static final String UPDATE_STAFF = "update  staff set name = ?, address = ? " +
            "where id = ?";
    public static final String UPDATE_CONNECT = "update  connect set id_staff = ?, id_category = ? where id_staff = ?";
    public static final String DELETE_CONNECT = "delete from connect where id_staff = ?";
    public static final String DELETE_STAFF = "delete from staff where  id = ?";
    Connection connection = ConnectJDBC.getConnect();

    @Override
    public List<Staff> selectAll() {
        List<Staff> staffList = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement(SELECT_ALL);
            ResultSet set = statement.executeQuery();
            while (set.next()){
                int id = set.getInt("id");
                String name = set.getString("name");
                String address = set.getString("address");

                int id_category = set.getInt("id_category");
                String name_category = set.getString("name_category");
                Category category = new Category(id_category,name_category);
                Staff staff = new Staff(id,name,address,category);
                staffList.add(staff);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return staffList;
    }

    @Override
    public Staff selectById(int id) {
        Staff staff = null;
        try {
            PreparedStatement statement = connection.prepareStatement(SELECT_STAFF_BY_ID);
            statement.setInt(1,id);
            ResultSet set = statement.executeQuery();
            while (set.next()){
                String name = set.getString("name");
                String address = set.getString("address");
                staff = new Staff(id,name,address);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return staff;
    }

    @Override
    public void create(Staff staff, int[] category) {
        int id_staff = 0;
        try {
            PreparedStatement statement = connection.prepareStatement(INSERT_NEW_STAFF, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1,staff.getName());
            statement.setString(2,staff.getAddress());
            statement.executeUpdate();

            ResultSet set = statement.getGeneratedKeys();
            while (set.next()){
                id_staff = set.getInt(1);
            }

            PreparedStatement statement1 = connection.prepareStatement(INSERT_NEW_STAFF_CATEGORY);
            for (int id_category : category
                 ) {
                statement1.setInt(1,id_staff);
                statement1.setInt(2,id_category);
                statement1.executeUpdate();
            }
            


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


    }

    @Override
    public void update(int id, Staff staff, int [] category) {
        System.out.println(id);
        try {
            PreparedStatement statement = connection.prepareStatement(UPDATE_STAFF );
            statement.setInt(3,id);
            statement.setString(1,staff.getName());
            statement.setString(2,staff.getAddress());
            final int i = statement.executeUpdate();
            System.out.println(i);

            PreparedStatement statement1 = connection.prepareStatement(UPDATE_CONNECT);
            statement1.setInt(3,id);
            for (int id_category: category
                 ) {
                statement1.setInt(1,id);
                statement1.setInt(2,id_category);
                statement1.executeUpdate();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void delete(int id) {
        try {
            PreparedStatement statement = connection.prepareStatement(DELETE_CONNECT);
            statement.setInt(1,id);
            statement.executeUpdate();
            PreparedStatement statement1 = connection.prepareStatement(DELETE_STAFF);
            statement1.setInt(1,id);
            statement1.executeUpdate();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }
}
