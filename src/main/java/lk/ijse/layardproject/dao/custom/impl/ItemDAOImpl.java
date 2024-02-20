package lk.ijse.layardproject.dao.custom.impl;

import lk.ijse.layardproject.dao.SQLUtil;
import lk.ijse.layardproject.dao.SuperDAO;
import lk.ijse.layardproject.dao.custom.ItemDAO;
import lk.ijse.layardproject.entity.Item;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ItemDAOImpl implements ItemDAO {
    @Override
    public boolean save(Item item) throws SQLException {
        String sql = "insert into item(itemId,description,qty,price) values(?,?,?,?);";
        return SQLUtil.execute(sql,item.getItemId(),item.getDescription(),item.getQty(),item.getPrice());
    }

    @Override
    public Item search(String searchIdText) throws SQLException {
        String sql = "select * from item where itemId = ?";
        ResultSet resultSet = SQLUtil.execute(sql, searchIdText);
        Item item = null;
        if (resultSet.next()){
            String itemId = resultSet.getString("itemId");
            String description = resultSet.getString("description");
            int qty = resultSet.getInt("qty");
            double price = resultSet.getDouble("price");
            item = new Item(itemId,description,qty,price);
        }
        return item;
    }

    @Override
    public boolean update(Item item) throws SQLException {
        String sql = "update item set description = ?,qty = ?,price = ? where itemId = ?;";
        return SQLUtil.execute(sql,item.getDescription(),item.getQty(),item.getPrice(),item.getItemId());
    }

    @Override
    public boolean delete(String id) throws SQLException {
        String sql = "delete from item where itemId = ?;";
        return SQLUtil.execute(sql,id);
    }

    @Override
    public String generateId() throws SQLException {
        String sql = "select max(itemId) as lastItemId from item;";
        try {
            ResultSet resultSet = SQLUtil.execute(sql);
            if (resultSet.next()){
                String lastItemId = resultSet.getString("lastItemId");
                if (lastItemId == null){
                    return "I001";
                }else {
                    int nextId = Integer.parseInt(lastItemId.substring(1))+1;
                    return "I" + String.format("%03d",nextId);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public ArrayList<Item> getAll() throws SQLException {
        String sql = "select * from item";
        ArrayList<Item> itemArrayList = new ArrayList<>();
        ResultSet resultSet = SQLUtil.execute(sql);
        while (resultSet.next()){
            Item item =new Item(
                    resultSet.getString("itemId"),
                    resultSet.getString("description"),
                    resultSet.getInt("qty"),
                    resultSet.getDouble("price")
            );
            itemArrayList.add(item);
        }
        return itemArrayList;
    }

    @Override
    public ArrayList<String> getId() throws SQLException {
        String sql = "select itemId from item";
        ResultSet resultSet = SQLUtil.execute(sql);
        ArrayList<String> item = new ArrayList<>();
        while (resultSet.next()){
            String itemId = resultSet.getString("itemId");
            item.add(itemId);
        }
        return item;
    }
}
