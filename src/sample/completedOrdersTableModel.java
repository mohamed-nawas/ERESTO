package sample;

public class completedOrdersTableModel {
    String orderRefNo, employee;

    public completedOrdersTableModel(String orderRefNo, String employee) {
        this.orderRefNo = orderRefNo;
        this.employee = employee;
    }

    public String getOrderRefNo() {
        return orderRefNo;
    }

    public void setOrderRefNo(String orderRefNo) {
        this.orderRefNo = orderRefNo;
    }

    public String getEmployee() {
        return employee;
    }

    public void setEmployee(String employee) {
        this.employee = employee;
    }
}
