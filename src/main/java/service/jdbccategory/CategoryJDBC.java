package service.jdbccategory;

import model.Category;
import service.connectdatabase.ConnectJDBC;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoryJDBC implements CategoryJDBCInterface{

    public static final String SELECT_ALL_CATEGORY = "select * from category";
    public static final String ADD_NEW_CATEGORY = "insert category (name) value (?)";
    public static final String DELETE_CATEGORY = "delete from category where id = ?";
    public static final String UPDATE_CATEGORY = "update from category set name = ? where id = ?";
    Connection connection = ConnectJDBC.getConnect();


    @Override
    public List<Category> selectAll() {
        List<Category> categoryList = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement(SELECT_ALL_CATEGORY);
            ResultSet set = statement.executeQuery();
            while (set.next()){
                int id = set.getInt("id");
                String name = set.getString("name");
                Category category = new Category(id,name);
                categoryList.add(category);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return categoryList;
    }

    @Override
    public Category selectById(int id) {
        Category category = null;
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement("select * from category where  id = ?");
            statement.setInt(1,id);
            ResultSet set = statement.executeQuery();
            while (set.next()){
                String name = set.getString("name");
                category = new Category(id,name);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return category;
    }

    @Override
    public void create(Category category) {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(ADD_NEW_CATEGORY);
            statement.setString(1,category.getName());
            statement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }



    }

    @Override
    public void update(int id, Category category) {
        try {
            PreparedStatement statement = connection.prepareStatement(UPDATE_CATEGORY);
            statement.setInt(2,id);
            statement.setString(1,category.getName());
            statement.executeUpdate();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


    }

    @Override
    public void delete(int id) {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(DELETE_CATEGORY);
            statement.setInt(1,id);
            statement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }
}
