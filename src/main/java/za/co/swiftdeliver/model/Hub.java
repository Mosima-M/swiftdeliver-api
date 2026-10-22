package za.co.swiftdeliver.model;

public class Hub {
    private final String hubId;
    private final String province;
    private final String sortingCenter;
    private final boolean active;

   public Hub(String hubId, String province, String sortingCenter,boolean active){
       this.hubId = hubId;
       this.province = province;
       this.sortingCenter = sortingCenter;
       this.active = active;
   }

    public String getHubId() {
        return hubId;
    }

    public String getProvince() {
        return province;
    }

    public String getSortingCenter() {
        return sortingCenter;
    }

    public boolean isActive() {
        return active;
    }
}