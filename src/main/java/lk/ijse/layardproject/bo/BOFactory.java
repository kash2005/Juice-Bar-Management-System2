package lk.ijse.layardproject.bo;

import lk.ijse.layardproject.bo.custom.CustomerBO;
import lk.ijse.layardproject.bo.custom.impl.*;
import lk.ijse.layardproject.dao.custom.impl.*;

public class BOFactory {
    private static BOFactory boFactory;

    private BOFactory(){}

    public static BOFactory getBoFactory(){
        return (boFactory==null) ? boFactory = new BOFactory() : boFactory;
    }

    public enum BOTypes{
        CUSTOMER,SUPPLIER,INGREDIENT,ITEM,ORDER,PLACEORDER,EMPLOYEE,ATTENDANCE,USER,DELIVERY,ORDERDETAILS
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
            case PLACEORDER:
                return new PlaceOrderBOImpl();
            case EMPLOYEE:
                return new EmployeeBOImpl();
            case ATTENDANCE:
                return new AttendanceBOImpl();
            case USER:
                return new UserBOImpl();
            case DELIVERY:
                return new DeliveryBOImpl();
            case ORDERDETAILS:
                return new OrderDetailsBOImpl();
            default:
                return null;
        }
    }
}
