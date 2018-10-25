package webapp;


import webapp.backend.Controller;
import webapp.backend.DMXUtils;
import webapp.backend.LiveOutput;
import webapp.backend.OutputStreamer;
import webapp.backend.exceptions.InvalidUniverseException;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Test {
    public static void main(String[] args) throws Exception{
        new Controller();

        //TestDMX();



    }


    public static void TestDMX(){

        try {
            // TODO code application logic here
            LiveOutput lo = new LiveOutput(1, "sACN4J testing");
            OutputStreamer os = new OutputStreamer(lo);
            lo.setPriority((byte) 0xAA); // Default 0x65
            //byte val = (byte) 0x0;
            byte zero = DMXUtils.OFF;
            byte full = DMXUtils.FULL;
            // Red 0
            // White 1
            // Yellow 2
            // Green Cyan 3
            // Green 4
            // Blue 5
            // Purple 6
            // Intensity 7
            // Strobe 8
            lo.setDMXVal(0, full);
            lo.setDMXVal(1, full);
            lo.setDMXVal(2, zero);
            lo.setDMXVal(3, zero);
            lo.setDMXVal(4, full);
            lo.setDMXVal(5, full);
            lo.setDMXVal(6, zero);
            lo.setDMXVal(7, zero);
            lo.setDMXVal(8, zero);
            os.sendLiveOutput();
            Thread.sleep(20);

            int index = 7;
            int i = 0;
            while(i < 255){
                lo.setDMXVal(index, (byte) (lo.getDMXVal(index)+0x01));
                i++;
                os.sendLiveOutput();
                Thread.sleep(20);
            }
        } catch (IOException ex) {
            Logger.getLogger(Test.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvalidUniverseException ex) {
            Logger.getLogger(Test.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InterruptedException ex) {
            Logger.getLogger(Test.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
