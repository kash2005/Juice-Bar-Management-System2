package lk.ijse.layardproject.dao;

import lk.ijse.layardproject.bo.custom.impl.UserBOImpl;
import lk.ijse.layardproject.dao.custom.impl.*;
import lk.ijse.layardproject.entity.Attendance;

public class DAOFactory {
    public static DAOFactory daoFactory;

    public DAOFactory(){}

    public static DAOFactory getDaoFactory(){
        return (daoFactory == null) ? new DAOFactory() : daoFactory;
    }

    public enum DAOTypes{
        CUSTOMER,SUPPLIER,INGREDIENT,ITEM,ORDER,PLACEORDER,ORDERDETAILS,EMPLOYEE,ATTENDANCE,USER
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
            case PLACEORDER:
                return new PlaceOrderDAOImpl();
            case ORDERDETAILS:
                return new OrderDetailsDAOImpl();
            case EMPLOYEE:
                return new EmployeeDAOImpl();
            case ATTENDANCE:
                return new AttendanceDAOImpl();
            case USER:
                return new UserDAOImpl();
            default:
                return null;
        }
    }
}
