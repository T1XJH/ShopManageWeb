package com.wbg.DaoInterface;
import com.wbg.entity.OrderInformation;
import java.util.List;
public interface OrderInformationInterface {
    List<OrderInformation> finAll(int ouid);
    List<OrderInformation> finselestatus(int ouid,String ostatus);
    OrderInformation insert(OrderInformation OrderInformation ,int[] sid,String[]spid,int [] count);
    boolean updates(OrderInformation OrderInformation);
    boolean delete(String oid);
}
