package daily;

/**
 * @author Li-Xiaoxu
 * @version 1.0
 * @date 2020/6/3 15:29
 */
public class StringReplace {
    public static void main(String[] args) {
        String string = "SubNetwork=210709,MEID=85598,Equipment=8237,Rack=82,SubRack=1,Slot=1";
        String newstring = string.replace("SubNetwork=","")
                .replace(",MEID=","_")
                .replaceAll(",Equipment=(.*),Rack=","_")
                .replace(",SubRack=","_")
                .replace(",Slot=","_");
        System.out.println(newstring);
        String SubNetwork = string.replaceAll("SubNetwork=","").replaceAll(",MEID=.*","");
        String MEID = string.replaceAll(".*,MEID=","").replaceAll(",Equipment.*","");
        String Rack = string.replaceAll(".*,Rack=","").replaceAll(",SubRack.*","");
        String SubRack = string.replaceAll(".*,SubRack=","").replaceAll(",Slot.*","");
        String Slot = string.replaceAll(".*,Slot=","");
        System.out.println(Rack);
        System.out.println(SubNetwork + " " + MEID + " " + Rack + " " + SubRack + " " + Slot + " ");
        /////
        System.out.println(string);
        System.out.println(string.replaceAll(".*,Rack=",""));

    }
}
