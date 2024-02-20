package lk.ijse.layardproject.bo;

import lk.ijse.layardproject.bo.custom.CustomerBO;
import lk.ijse.layardproject.bo.custom.impl.CustomerBOImpl;
import lk.ijse.layardproject.bo.custom.impl.IngredientBOImpl;
import lk.ijse.layardproject.bo.custom.impl.ItemBOImpl;
import lk.ijse.layardproject.bo.custom.impl.SupplierBOImpl;
import lk.ijse.layardproject.dao.custom.impl.IngredientDAOImpl;

public class BOFactory {
    private static BOFactory boFactory;

    private BOFactory(){}

    public static BOFactory getBoFactory(){
        return (boFactory==null) ? boFactory = new BOFactory() : boFactory;
    }

    public enum BOTypes{
        CUSTOMER,SUPPLIER,INGREDIENT,ITEM
    }

    public SuperBO getBO(BOTypes boTypes){
        switch (boTypes){
            case CUSTOMER :
                return new CustomerBOImpl();
            case SUPPLIER:
                return new SupplierBOImpl();
            case INGREDIENT:
                return new IngredientBOImpl();
            case ITEM:
                return new ItemBOImpl();
            default:
                return null;
        }
    }
}
