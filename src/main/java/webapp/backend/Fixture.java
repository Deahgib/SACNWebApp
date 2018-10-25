package webapp.backend;


import java.util.HashMap;
import java.util.Map;

public class Fixture {
    public int startAddress;
    public int size;
    public int channel;
    public byte[] dmx;

    public Map<PatchIdentity, Integer> identity;

    private LiveOutput liveOutput;
    public void setLiveOutput(LiveOutput liveOutput) {
        this.liveOutput = liveOutput;
    }
    //    private byte[] patch;
//    public byte[] getPatch() {
//        return patch;
//    }

    public int getPatchIndex(PatchIdentity channel){
        return startAddress + identity.get(channel);
    }

    public void loadDefaultIdentity(){
        identity = new HashMap<PatchIdentity, Integer>();
        identity.put(PatchIdentity.RED, 0);
        identity.put(PatchIdentity.WHITE, 1);
        identity.put(PatchIdentity.YELLOW, 2);
        identity.put(PatchIdentity.CYAN, 3);
        identity.put(PatchIdentity.GREEN, 4);
        identity.put(PatchIdentity.BLUE, 5);
        identity.put(PatchIdentity.PURPLE, 6);
        identity.put(PatchIdentity.INTENSITY, 7);
        identity.put(PatchIdentity.STROBE, 8);
    }

    public Fixture(int startAddress, int size) {
        this(startAddress, size, false);
    }

    public Fixture(int startAddress, int size, boolean loadAsBlank) {
        this.startAddress = startAddress;
        this.size = size;
        this.dmx = new byte[this.size];
        if(loadAsBlank)
            for(int i = 0; i < this.size; i++){
                dmx[i] = DMXUtils.OFF;
            }
    }

    public void setValue(PatchIdentity channel, byte value){
        this.dmx[getPatchIndex(channel)] = value;
    }

    public void goHome(){
        // GO through dmx make all OFF get index for intensity make FULL.
        for(int i = 0; i < size; i++){
            dmx[i] = DMXUtils.OFF;
        }
        dmx[getPatchIndex(PatchIdentity.WHITE)] = DMXUtils.HALF;
        dmx[getPatchIndex(PatchIdentity.INTENSITY)] = DMXUtils.FULL;
    }

}
