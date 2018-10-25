package webapp.backend;


public class PatchUtils{
    public static String getStringForIdentity(PatchIdentity pi){
        String out;
        switch (pi){
            case EMPTY: out = "EMPTY"; break;
            case INTENSITY: out = "INTENSITY"; break;
            case RED: out = "RED"; break;
            case BLUE: out = "BLUE"; break;
            case GREEN: out = "GREEN"; break;
            case CYAN: out = "CYAN"; break;
            case PURPLE: out = "PURPLE"; break;
            case YELLOW: out = "YELLOW"; break;
            case WHITE: out = "WHITE"; break;
            case STROBE: out = "STROBE"; break;
            case PAN: out = "PAN"; break;
            case TITLT: out = "TITLT"; break;
            default: out = "#";
        }
        return out;
    }


    public static PatchIdentity getIdentityForString(String pi){
        PatchIdentity out;
        switch (pi){
            case "INTENSITY": out = PatchIdentity.INTENSITY; break;
            case "RED": out = PatchIdentity.RED; break;
            case "BLUE": out = PatchIdentity.BLUE; break;
            case "GREEN": out = PatchIdentity.GREEN; break;
            case "CYAN": out = PatchIdentity.CYAN; break;
            case "PURPLE": out = PatchIdentity.PURPLE; break;
            case "YELLOW": out = PatchIdentity.YELLOW; break;
            case "WHITE": out = PatchIdentity.WHITE; break;
            case "STROBE": out = PatchIdentity.STROBE; break;
            case "PAN": out = PatchIdentity.PAN; break;
            case "TITLT": out = PatchIdentity.TITLT; break;
            default: out = PatchIdentity.EMPTY;
        }
        return out;
    }
}
