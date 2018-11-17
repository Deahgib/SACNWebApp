package webapp.server;

import org.json.JSONArray;
import org.json.JSONObject;
import webapp.backend.Fixture;
import webapp.backend.Patch;
import webapp.backend.PatchIdentity;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

public class PatchGetFixturesServlet extends HttpServlet
{
    private Patch patch;

    public PatchGetFixturesServlet(Patch patch)
    {
        this.patch = patch;
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        System.out.println(request.getRequestURL());

        JSONArray json = new JSONArray();

        patch.updateLiveDMXOutput();

        ArrayList<Fixture> fixtures = patch.getFixtures();
        for (Fixture fixture : fixtures){
            JSONObject fix = new JSONObject();

            fix.put("channel", fixture.channel);
            fix.put("startAddress", fixture.startAddress);
            fix.put("size", fixture.size);

            JSONArray ids = new JSONArray();
            for (PatchIdentity type : fixture.identity.keySet()){
                JSONObject pi = new JSONObject();
                pi.put("index", fixture.identity.get(type));
                pi.put("key", type.toString());
                pi.put("value", (0x00 << 24 | fixture.dmx[fixture.identity.get(type)] & 0xff));
                ids.put(pi);
            }
            fix.put("identities", ids);

            json.put(fix);
        }

        String jsonString = json.toString();
        System.out.println("Response: " + jsonString);

        response.setContentType("application/json");
        response.setStatus(HttpServletResponse.SC_OK);
        response.getWriter().append(jsonString);
    }

}