package lk.ijse.layardproject.bo.custom.impl;

import lk.ijse.layardproject.bo.SuperBO;
import lk.ijse.layardproject.bo.custom.IngredientBO;
import lk.ijse.layardproject.dao.DAOFactory;
import lk.ijse.layardproject.dao.custom.IngredientDAO;
import lk.ijse.layardproject.dto.IngredientDTO;
import lk.ijse.layardproject.entity.Ingredient;

import java.sql.SQLException;
import java.util.ArrayList;

public class IngredientBOImpl implements IngredientBO {

    IngredientDAO ingredientDAO = (IngredientDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.INGREDIENT);

    @Override
    public boolean deleteIngredient(String id) throws SQLException {
        return ingredientDAO.delete(id);
    }

    @Override
    public boolean saveIngredient(IngredientDTO ingredientDTO) throws SQLException {
        return ingredientDAO.save(new Ingredient(ingredientDTO.getIngredientId(),ingredientDTO.getDescription(),
                ingredientDTO.getPrice(),ingredientDTO.getWeight()));
    }

    @Override
    public IngredientDTO searchIngredientId(String id) throws SQLException {
        Ingredient ingredient = ingredientDAO.search(id);
        return new IngredientDTO(ingredient.getIngredientId(),ingredient.getDescription(), ingredient.getPrice(),
                ingredient.getWeight());
    }

    @Override
    public boolean updateIngredient(IngredientDTO ingredientDTO) throws SQLException {
        return ingredientDAO.update(new Ingredient(ingredientDTO.getIngredientId(),ingredientDTO.getDescription(),
                ingredientDTO.getPrice(),ingredientDTO.getWeight()));
    }

    @Override
    public String generateIngredientId() throws SQLException {
        return ingredientDAO.generateId();
    }

    @Override
    public ArrayList<IngredientDTO> getAll() throws SQLException {
        ArrayList<IngredientDTO> ingredientDTOS = new ArrayList<>();
        ArrayList<Ingredient> ingredients = ingredientDAO.getAll();
        for (Ingredient ingredient : ingredients){
            ingredientDTOS.add(new IngredientDTO(ingredient.getIngredientId(),ingredient.getDescription(),
                    ingredient.getPrice(),ingredient.getWeight()));
        }
        return ingredientDTOS;
    }
}
