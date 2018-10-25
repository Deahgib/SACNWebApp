package webapp.backend;

import java.nio.ByteBuffer;
import java.util.ArrayList;

public class Patch {
    ByteBuffer universe_dmx;

    public ArrayList<Fixture> getFixtures() {
        return  fixtures;
    }

    public void setFixtures(ArrayList<Fixture> fixtures) {
        this.fixtures = fixtures;
    }

    ArrayList<Fixture> fixtures;
    private LiveOutput lo;
    public LiveOutput getLiveOutput(){
        return lo;
    }

    public Patch(int universe, String patchName) {
        fixtures = new ArrayList<Fixture>();
        lo = new LiveOutput(universe, patchName);
        lo.setPriority((byte) 0xAA); // Default 0x65
        universe_dmx = ByteBuffer.wrap(new byte[DMXUtils.DMX_Universe_Size]);
    }

    public void addFixture(Fixture fixture) {
        fixture.setLiveOutput(lo);
        fixtures.add(fixture);
    }

    public byte[] getDMXPatch(){
        universe_dmx.clear();
        for (Fixture fixture : fixtures){
            universe_dmx.put(fixture.dmx, fixture.startAddress, fixture.size);
        }
        return universe_dmx.array();
    }

    public void updateLiveDMXOutput(){
        lo.setDMXvals(getDMXPatch());
    }
}
