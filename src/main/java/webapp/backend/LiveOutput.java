package webapp.backend;


import java.nio.ByteBuffer;

/**
 *
 * @author lbennette
 */
public class LiveOutput {

    private int universe;
    private String sourceName;
    protected byte[] ACNHeader;
    protected byte[] DMXvals;
    public volatile byte[] output;

    public LiveOutput(int universe, String sourceName) {
        this.universe = universe;
        this.sourceName = sourceName;
        if(!ACNProtocol.isUniverseValid(universe)){
            System.out.println("InvalidUniverseException: All universes must be between 1-63999.");
            //throw new InvalidUniverseException("InvalidUniverseException: All universes must be between 1-63999.");
        }
        ACNHeader = ACNProtocol.generateACNHeaderLayers(universe, sourceName);
        DMXvals = new byte[ACNProtocol.DMX_UNIVERSE_HEADER_SIZE];
        blackout();
    }

    public void blackout(){
        for (int i = 0; i < ACNProtocol.DMX_UNIVERSE_HEADER_SIZE; i++){
            DMXvals[i] = DMXUtils.OFF;
        }
        //updateOutput();
    }

    private void updateOutput(){
        ByteBuffer b = ByteBuffer.wrap(new byte[ ACNProtocol.ACN_HEADER_SIZE+ACNProtocol.DMX_UNIVERSE_HEADER_SIZE]);
        b.put(ACNHeader,0, ACNProtocol.ACN_HEADER_SIZE);
        b.put(DMXvals,0, ACNProtocol.DMX_UNIVERSE_HEADER_SIZE);
        output = b.array();
    }

    public void setDMXvals(byte[] DMXvals) {
        for(int i = 0; i < ACNProtocol.DMX_UNIVERSE_HEADER_SIZE-1; i++) {
            this.DMXvals[i+1] = DMXvals[i];
        }
    }

    public byte[] getOutput(){
        ACNHeader[111]++;
        //updateOutput();
        return output;
    }

    public byte[] buildLiveOutputPacket()
    {
        ACNHeader[111]++;
        ByteBuffer b = ByteBuffer.wrap(new byte[ ACNProtocol.ACN_HEADER_SIZE+ACNProtocol.DMX_UNIVERSE_HEADER_SIZE]);
        b.put(ACNHeader,0, ACNProtocol.ACN_HEADER_SIZE);
        b.put(DMXvals,0, ACNProtocol.DMX_UNIVERSE_HEADER_SIZE);
        output = b.array();
        return output;
    }

    /**
     * @param index 0-511 DMX address
     * @param val
     */
    public void setDMXVal(int index, byte val){
        DMXvals[index+1] = val;
        //updateOutput();
    }

    public byte getDMXVal(int index){
        return DMXvals[index+1];
    }

    public int getUniverse() {
        return universe;
    }

    public void setPriority(byte prio){
        ACNHeader[108] = prio;
        //updateOutput();
    }

}