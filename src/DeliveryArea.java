public class DeliveryArea {

    private String areaId;         
    private String areaName;    
    private String deliveryPersonId;
    private int totalCustomers;      


    public DeliveryArea() {
        this.areaId = null;
        this.areaName = null;
        this.deliveryPersonId = null;
        this.totalCustomers = 0;
    }


    public DeliveryArea(String areaId, String areaName, String deliveryPersonId, int totalCustomers) {
        this.areaId = areaId;
        this.areaName = areaName;
        this.deliveryPersonId = deliveryPersonId;
        this.totalCustomers = totalCustomers;
    }

    public String getAreaId() {
        return areaId;
    }

    public void setAreaId(String areaId) {
        this.areaId = areaId;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public String getDeliveryPersonId() {
        return deliveryPersonId;
    }

    public void setDeliveryPersonId(String deliveryPersonId) {
        this.deliveryPersonId = deliveryPersonId;
    }

    public int getTotalCustomers() {
        return totalCustomers;
    }

    public void setTotalCustomers(int totalCustomers) {
        this.totalCustomers = totalCustomers;
    }

    @Override
    public String toString() {
        return "DeliveryArea{" +
                "areaId='" + areaId + '\'' +
                ", areaName='" + areaName + '\'' +
                ", deliveryPersonId='" + deliveryPersonId + '\'' +
                ", totalCustomers=" + totalCustomers +
                '}';
    }
}