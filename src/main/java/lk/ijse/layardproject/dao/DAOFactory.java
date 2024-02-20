package lk.ijse.layardproject.dao;

import lk.ijse.layardproject.bo.custom.impl.ItemBOImpl;
import lk.ijse.layardproject.dao.custom.impl.*;

public class DAOFactory {
    public static DAOFactory daoFactory;

    public DAOFactory(){}

    public static DAOFactory getDaoFactory(){
        return (daoFactory == null) ? new DAOFactory() : daoFactory;
    }

    public enum DAOTypes{
        CUSTOMER,SUPPLIER,INGREDIENT,ITEM,ORDER
    }

    public SuperDAO getDAO(DAOTypes daoTypes){
        switch (daoTypes){
            case CUSTOMER :
                return new CustomerDAOImpl();
            case SUPPLIER:
                return new SupplierDAOImpl();
            case INGREDIENT:
                return new IngredientDAOImpl();
            case ITEM:
                return new ItemDAOImpl();
            case ORDER:
                return new OrderDAOImpl();
            default:
                return null;
        }
    }
}
