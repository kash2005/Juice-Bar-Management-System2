package lk.ijse.layardproject.dao.custom.impl;

import lk.ijse.layardproject.dao.SQLUtil;
import lk.ijse.layardproject.dao.SuperDAO;
import lk.ijse.layardproject.dao.custom.IngredientDAO;
import lk.ijse.layardproject.entity.Ingredient;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class IngredientDAOImpl implements IngredientDAO {
    @Override
    public boolean save(Ingredient ingredient) throws SQLException {
        String sql ="insert into ingredient(ingredientId,description,price,weight) values(?,?,?,?);";
        return SQLUtil.execute(sql,ingredient.getIngredientId(),ingredient.getDescription(),ingredient.getPrice(),ingredient.getWeight());
    }

    @Override
    public Ingredient search(String searchIdText) throws SQLException {
        String sql = "select * from ingredient where ingredientId = ?;";
        ResultSet resultSet = SQLUtil.execute(sql, searchIdText);
        Ingredient ingredient = null;
        while (resultSet.next()){
            String ingredientId = resultSet.getString("ingredientId");
            String description = resultSet.getString("description");
            double price = resultSet.getDouble("price");
            String weight = resultSet.getString("weight");
            ingredient = new Ingredient(ingredientId,description,price,weight);
        }
        return ingredient;
    }

    @Override
    public boolean update(Ingredient ingredient) throws SQLException {
        String sql = "update ingredient set description = ?,price = ?,weight =? where ingredientId = ?;";
        return SQLUtil.execute(sql,ingredient.getDescription(),ingredient.getPrice(),ingredient.getWeight(),ingredient.getIngredientId());
    }

    @Override
    public boolean delete(String id) throws SQLException {
        String sql = "delete from ingredient where ingredientId = ?;";
        return SQLUtil.execute(sql,id);
    }

    @Override
    public String generateId() throws SQLException {
        String sql = "select max(ingredientId) as lastIngredientId from ingredient;";
        ResultSet resultSet = SQLUtil.execute(sql);
        try {
            if (resultSet.next()){
                String lastIngredientId = resultSet.getString("lastIngredientId");
                if (lastIngredientId == null) {
                    return "IN001";
                }else {
                    int nextId = Integer.parseInt(lastIngredientId.substring(2)) + 1;
                    return "IN" + String.format("%03d",nextId);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public ArrayList<Ingredient> getAll() throws SQLException {
        String sql = "select * from ingredient";
        ResultSet resultSet = SQLUtil.execute(sql);
        ArrayList<Ingredient> ingredients = new ArrayList<>();
        while (resultSet.next()){
            Ingredient ingredient = new Ingredient(
                    resultSet.getString("ingredientId"),
                    resultSet.getString("description"),
                    resultSet.getDouble("price"),
                    resultSet.getString("weight")
            );
            ingredients.add(ingredient);
        }
        return ingredients;
    }
}
