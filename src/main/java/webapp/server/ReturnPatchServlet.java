package webapp.server;

import org.json.JSONArray;
import org.json.JSONObject;
import webapp.backend.DMXUtils;
import webapp.backend.Fixture;
import webapp.backend.Patch;
import webapp.backend.PatchIdentity;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ReturnPatchServlet extends HttpServlet
{
    private Patch patch;

    public ReturnPatchServlet(Patch patch)
    {
        this.patch = patch;
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        System.out.println(request.getRequestURL());

        JSONArray json = new JSONArray();

        byte[] dmx = patch.getDMXPatch();
        for (int i = 0; i < dmx.length; i++){
            JSONObject cell = new JSONObject();
            cell.put("id", i);
            cell.put("value", (0x00 << 24 | dmx[i] & 0xff));

            json.put(cell);
        }

        String jsonString = json.toString();
        System.out.println("Response: " + jsonString);

        response.setContentType("application/json");
        response.setStatus(HttpServletResponse.SC_OK);
        response.getWriter().append(jsonString);
//
//                response.setContentType("text/html");
//        response.setStatus(HttpServletResponse.SC_OK);
        //response.getWriter().println("<h1>"+greeting+"</h1>");
        //response.getWriter().println("session=" + request.getSession(true).getId());
        //response.getWriter().append("MY WEBSITE ANSWER <p>WItH</p>\nFOR\n<span>MAT</span>\n\n\n<h1>ing</h1>");


//        File file = new File("Index.html");
//
//        BufferedReader br = new BufferedReader(new FileReader(file));

//        String st;
//        while ((st = br.readLine()) != null)
//            response.getWriter().println(st);
    }

}