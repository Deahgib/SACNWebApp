package webapp.backend;


import java.io.IOException;
import java.nio.file.Path;

public class ACNUpdater extends Thread {
    private OutputStreamer acnStreamer;
    private Patch patch;
    public ACNUpdater(OutputStreamer os, Patch patch){
        acnStreamer = os;
        this.patch = patch;
        setDaemon(true);
    }

    public void run() {
        int count = 0;

        while (true) {

            try {
                this.patch.updateLiveDMXOutput();
                //System.out.println("Hello from Worker "+count++);
                acnStreamer.sendLiveOutput();
                sleep(20);
            } catch (InterruptedException e) {
                System.out.println(e.getLocalizedMessage());
            } catch (IOException e){
                System.out.println(e.getLocalizedMessage());
            }
        }
    }
}