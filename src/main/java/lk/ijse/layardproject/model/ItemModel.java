package lk.ijse.layardproject.model;

import lk.ijse.layardproject.dto.CartDTO;
import lk.ijse.layardproject.dto.ItemDTO;
import lk.ijse.layardproject.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ItemModel {
    public static String generateItemId() throws SQLException {
        String sql = "select max(itemId) as lastItemId from item;";
        try {
            ResultSet resultSet = CrudUtil.execute(sql);
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

    public static boolean saveItem(ItemDTO itemDTO) throws SQLException {
        String sql = "insert into item(itemId,description,qty,price) values(?,?,?,?);";
        return CrudUtil.execute(sql,itemDTO.getItemId(),itemDTO.getDescription(),itemDTO.getQty(),itemDTO.getPrice());
    }

    public static boolean updateItem(ItemDTO itemDTO) throws SQLException {
        String sql = "update item set description = ?,qty = ?,price = ? where itemId = ?;";
        return CrudUtil.execute(sql,itemDTO.getDescription(),itemDTO.getQty(),itemDTO.getPrice(),itemDTO.getItemId());
    }

    public static ItemDTO searchItem(String id) throws SQLException {
        String sql = "select * from item where itemId = ?";
        ResultSet resultSet = CrudUtil.execute(sql, id);
        ItemDTO itemDTO = null;
        if (resultSet.next()){
            String itemId = resultSet.getString("itemId");
            String description = resultSet.getString("description");
            int qty = resultSet.getInt("qty");
            double price = resultSet.getDouble("price");
            itemDTO = new ItemDTO(itemId,description,qty,price);
        }
        return itemDTO;
    }

    public static boolean deleteItem(String id) throws SQLException {
        String sql = "delete from item where itemId = ?;";
        return CrudUtil.execute(sql,id);
    }

    public static ArrayList<ItemDTO> getAll() throws SQLException {
        String sql = "select * from item";
        ArrayList<ItemDTO> itemDTOArrayList = new ArrayList<>();
        ResultSet resultSet = CrudUtil.execute(sql);
        while (resultSet.next()){
            ItemDTO itemDTO =new ItemDTO(
                    resultSet.getString("itemId"),
                    resultSet.getString("description"),
                    resultSet.getInt("qty"),
                    resultSet.getDouble("price")
            );
            itemDTOArrayList.add(itemDTO);
        }
        return itemDTOArrayList;
    }

    public static ArrayList<String> getItemId() throws SQLException {
        String sql = "select itemId from item";
        ResultSet resultSet = CrudUtil.execute(sql);
        ArrayList<String> item = new ArrayList<>();
        while (resultSet.next()){
            String itemId = resultSet.getString("itemId");
            item.add(itemId);
        }
        return item;
    }

    public static boolean updateQty(List<CartDTO> cartDTOList) throws SQLException {
        for (CartDTO cartDTO : cartDTOList){
            if (!updateQty(cartDTO)){
                return false;
            }
        }
        return true;
    }

    public static boolean updateQty(CartDTO cartDTO) throws SQLException {
        String sql = "update item set qty = (qty - ?) where itemId =?;";
        return CrudUtil.execute(sql,cartDTO.getQty(),cartDTO.getItemId());
    }
}
