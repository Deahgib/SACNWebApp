package webapp.server;

import org.json.JSONObject;
import webapp.backend.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;

public class FixtureValueChangeServlet extends HttpServlet {
    protected Patch patch;
    protected PatchIdentity pi;

    public FixtureValueChangeServlet(Patch patch) {
        this.patch = patch;
        this.pi = pi;
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println(request.getRequestURL());

        BufferedReader reader = request.getReader();
        String data = reader.readLine();
        JSONObject jObject = new JSONObject(data);

        System.out.println("Received:" + data);

        for (Fixture fixture : patch.getFixtures()) {
            if (fixture.channel == jObject.getInt("channel")) {
                fixture.setValue(PatchUtils.getIdentityForString(jObject.getString("key")), (byte) jObject.getInt("value"));
            }
        }
    }
}