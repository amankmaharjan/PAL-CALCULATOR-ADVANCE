
import java.util.List;


/**
 * file name: EnergyData.java
 * @author aman & damodar
 * This file is used to bind the BMR data and corresponding PAL data which is read from the file
 */
// Class to store the BMR data and corresponding pal descriptions
public class EnergyData {
    //BMR field
   private BMRdata bMRdata;
   // Pal data list
   private List<PALdata>palDatalist;
   
//   Constructor
    public EnergyData(BMRdata bMRdata, List<PALdata> palDatalist) {
        this.bMRdata = bMRdata;
        this.palDatalist = palDatalist;
    }
// Getter
    public BMRdata getbMRdata() {
        return bMRdata;
    }
// Setter
    public void setbMRdata(BMRdata bMRdata) {
        this.bMRdata = bMRdata;
    }
// Getter
     public List<PALdata> getPalDatalist() {
        return palDatalist;
    }
// Setter
    public void setPalDatalist(List<PALdata> palDatalist) {
        this.palDatalist = palDatalist;
    }

    @Override
    public String toString() {
        return "EnergyData{" + "bMRdata=" + bMRdata + ", palDatalist=" + palDatalist + '}';
    }
    
}
