package lk.ijse.layardproject.bo.custom.impl;

import lk.ijse.layardproject.bo.custom.ItemBO;
import lk.ijse.layardproject.dao.DAOFactory;
import lk.ijse.layardproject.dao.custom.ItemDAO;
import lk.ijse.layardproject.dto.ItemDTO;
import lk.ijse.layardproject.entity.Item;

import java.sql.SQLException;
import java.util.ArrayList;

public class ItemBOImpl implements ItemBO {

    ItemDAO itemDAO = (ItemDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ITEM);

    @Override
    public boolean deleteItem(String searchIdText) throws SQLException {
        return itemDAO.delete(searchIdText);
    }

    @Override
    public boolean saveItem(ItemDTO itemDTO) throws SQLException {
        return itemDAO.save(new Item(itemDTO.getItemId(),itemDTO.getDescription(),itemDTO.getQty(), itemDTO.getPrice()));
    }

    @Override
    public boolean updateItem(ItemDTO itemDTO) throws SQLException {
        return itemDAO.update(new Item(itemDTO.getItemId(),itemDTO.getDescription(),itemDTO.getQty(), itemDTO.getPrice()));
    }

    @Override
    public ItemDTO searchItem(String searchIdText) throws SQLException {
        Item item = itemDAO.search(searchIdText);
        return new ItemDTO(item.getItemId(),item.getDescription(),item.getQty(), item.getPrice());
    }

    @Override
    public ArrayList<ItemDTO> getAll() throws SQLException {
        ArrayList<ItemDTO> itemDTOS = new ArrayList<>();
        ArrayList<Item> itemArrayList = itemDAO.getAll();
        for (Item item : itemArrayList){
            itemDTOS.add(new ItemDTO(item.getItemId(),item.getDescription(),item.getQty(), item.getPrice()));
        }
        return itemDTOS;
    }

    @Override
    public String generateItemId() throws SQLException {
        return itemDAO.generateId();
    }

    @Override
    public ArrayList<String> getItemId() throws SQLException {
        ArrayList<String> stringArrayList = itemDAO.getId();
        return stringArrayList;
    }
}
