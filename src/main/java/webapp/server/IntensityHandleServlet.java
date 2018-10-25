package webapp.server;

import webapp.backend.DMXUtils;
import webapp.backend.Fixture;
import webapp.backend.PatchIdentity;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;

public class IntensityHandleServlet extends DirectChannelServlet
{
    public IntensityHandleServlet(Fixture fixture)
    {
        super(fixture, PatchIdentity.INTENSITY);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        System.out.println(request.getRequestURL());

        StringBuilder sb = new StringBuilder();
        BufferedReader reader = request.getReader();

        String data = reader.readLine();
        int val = Integer.parseInt(data);

        System.out.println("Received:" + data + " - " + val);


        fixture.setValue(pi, (byte) val);


    }

}