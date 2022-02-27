package sample;

public class orderTableModel {
    String orderRefNo, orderStatus;

    public orderTableModel(String orderRefNo, String orderStatus) {
        this.orderRefNo = orderRefNo;
        this.orderStatus = orderStatus;
    }

    public String getOrderRefNo() {
        return orderRefNo;
    }

    public void setOrderRefNo(String orderRefNo) {
        this.orderRefNo = orderRefNo;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }
}
