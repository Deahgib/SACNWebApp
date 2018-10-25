package webapp.backend;


import webapp.backend.exceptions.InvalidUniverseException;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.UnknownHostException;


/**
 *
 * @author lbennette
 */
public class OutputStreamer { //implements ActionListener
    private final InetAddress group;
    private final MulticastSocket s;
    private final LiveOutput liveOut;
    //private final Timer timer;

    public OutputStreamer(LiveOutput liveOut) throws UnknownHostException, IOException, InvalidUniverseException {
        this.liveOut = liveOut;
        group = InetAddress.getByName(ipFromUniverseNumber(liveOut.getUniverse()));
        s = new MulticastSocket(5568);
        s.joinGroup(group);

        //timer = new Timer(500, this);
        //timer.start();
    }

    public void sendLiveOutput() throws IOException{
        send(liveOut.buildLiveOutputPacket());
    }

    private void send(byte[] output) throws IOException{
        DatagramPacket hi = new DatagramPacket(output, output.length, group, 5568);

        // Debug first 9 address:

//        System.out.println("_______________________ ");
//        for (int x = 0; x < 9; x++){
//            System.out.println("| " + Byte.toString(output[ACNProtocol.ACN_HEADER_SIZE + x + 1]));
//        }

        s.send(hi);
    }

    public void destroy() throws IOException{
        s.leaveGroup(group);
        //timer.stop();
    }

    private static String ipFromUniverseNumber(int universeNumber) throws InvalidUniverseException{
        String ip = "239.255.";
        if(universeNumber <= 0 || universeNumber >= 64000){
            throw new InvalidUniverseException("InvalidUniverseException: All universes must be between 1-63999.");
        }
        else if(universeNumber <= 255){
            ip += "0." + universeNumber;
            return ip;
        }else{
            int fieldFour = universeNumber % 255;
            int fieldThree = universeNumber / 255;
            ip += fieldThree + "." + fieldFour;
            return ip;
        }
    }

    public void startOutputStream(Patch patch){
        new ACNUpdater(this, patch).start();
    }

//    @Override
//    public void actionPerformed(ActionEvent ae) {
//        try {
//            sendLiveOutput();
//        } catch (IOException ex) {
//            Logger.getLogger(OutputStreamer.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
}