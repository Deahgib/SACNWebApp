package webapp.backend;


import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import webapp.backend.exceptions.InvalidUniverseException;
import webapp.server.*;

import java.io.IOException;
import java.net.UnknownHostException;

public class Controller {
    private Patch patch;
    private  OutputStreamer os;
    private Fixture light;
    public Controller() {
        try {
            patch = new Patch(1, "Lustr Default");
            light = new Fixture(0, 9, true);
            light.loadDefaultIdentity();
            patch.addFixture(light);
            os = new OutputStreamer(patch.getLiveOutput());

            light.setValue(PatchIdentity.RED, DMXUtils.FULL);
            light.setValue(PatchIdentity.WHITE, DMXUtils.OFF);
            light.setValue(PatchIdentity.YELLOW, DMXUtils.FULL);
            light.setValue(PatchIdentity.CYAN, DMXUtils.OFF);
            light.setValue(PatchIdentity.GREEN, DMXUtils.OFF);
            light.setValue(PatchIdentity.BLUE, DMXUtils.OFF);
            light.setValue(PatchIdentity.PURPLE, DMXUtils.OFF);
            light.setValue(PatchIdentity.INTENSITY, DMXUtils.FULL);
            light.setValue(PatchIdentity.STROBE, DMXUtils.OFF);


            //UIWindow window = new UIWindow(light);
            //new ACNUpdater(os).start();
            //window.updateFixture();
        }catch (InvalidUniverseException e){
            e.printStackTrace();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        os.startOutputStream(patch);
        startServer();
    }

    private void startServer(){
        // Create a basic Jetty server object that will listen on port 8080.  Note that if you set this to port 0
        // then a randomly available port will be assigned that you can either look in the logs for the port,
        // or programmatically obtain it for use in webapp cases.
        Server server = new Server(8080);


        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.setContextPath("/api");
        //server.setHandler(context);
        context.addServlet(new ServletHolder(new DirectChannelServlet(light, PatchIdentity.BLUE)), "/blue");
        context.addServlet(new ServletHolder(new DirectChannelServlet(light, PatchIdentity.RED)), "/red");
        context.addServlet(new ServletHolder(new DirectChannelServlet(light, PatchIdentity.YELLOW)), "/yellow");

        context.addServlet(new ServletHolder(new ReturnPatchServlet(patch) ), "/patch" );

        context.addServlet(new ServletHolder(new PatchGetFixturesServlet(patch) ), "/patch/fixtures" );

        context.addServlet(new ServletHolder(new FixtureValueChangeServlet(patch) ), "/patch/fixture/value" );

        context.addServlet(new ServletHolder(new IntensityHandleServlet(light) ), "/intensity" );


        // Create the ResourceHandler. It is the object that will actually handle the request for a given file. It is
        // a Jetty Handler object so it is suitable for chaining with other handlers as you will see in other examples.
        ResourceHandler resource_handler = new ResourceHandler();

        // Configure the ResourceHandler. Setting the resource base indicates where the files should be served out of.
        // In this example it is the current directory but it can be configured to anything that the jvm has access to.
        resource_handler.setDirectoriesListed(true);
        resource_handler.setWelcomeFiles(new String[]{ "index.html" });
        resource_handler.setResourceBase("front-end/dist/front-end");

        // Add the ResourceHandler to the server.
        HandlerList handlers = new HandlerList();
        handlers.addHandler(resource_handler);
        handlers.addHandler(context);
        server.setHandler(handlers);

        // Start things up! By using the server.join() the server thread will join with the current thread.
        // See "http://docs.oracle.com/javase/1.5.0/docs/api/java/lang/Thread.html#join()" for more details.
        try {
            server.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
        //server.join();
    }
}
