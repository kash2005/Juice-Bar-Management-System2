package lk.ijse.layardproject.bo.custom;

import lk.ijse.layardproject.bo.SuperBO;
import lk.ijse.layardproject.dto.IngredientDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface IngredientBO extends SuperBO {
    boolean deleteIngredient(String id) throws SQLException;

    boolean saveIngredient(IngredientDTO ingredientDTO) throws SQLException;

    IngredientDTO searchIngredientId(String id) throws SQLException;

    boolean updateIngredient(IngredientDTO ingredientDTO) throws SQLException;

    String generateIngredientId() throws SQLException;

    ArrayList<IngredientDTO> getAll() throws SQLException;
}
