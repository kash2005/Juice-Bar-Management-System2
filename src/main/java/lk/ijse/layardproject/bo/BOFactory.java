package lk.ijse.layardproject.bo;

import lk.ijse.layardproject.bo.custom.CustomerBO;
import lk.ijse.layardproject.bo.custom.impl.*;
import lk.ijse.layardproject.dao.custom.impl.IngredientDAOImpl;
import lk.ijse.layardproject.dao.custom.impl.OrderDAOImpl;

public class BOFactory {
    private static BOFactory boFactory;

    private BOFactory(){}

    public static BOFactory getBoFactory(){
        return (boFactory==null) ? boFactory = new BOFactory() : boFactory;
    }

    public enum BOTypes{
        CUSTOMER,SUPPLIER,INGREDIENT,ITEM,ORDER
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
            case ORDER:
                return new OrderBOImpl();
            default:
                return null;
        }
    }
}
