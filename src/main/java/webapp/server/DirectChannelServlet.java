package webapp.server;

import webapp.backend.DMXUtils;
import webapp.backend.Fixture;
import webapp.backend.PatchIdentity;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DirectChannelServlet extends HttpServlet
{
    protected Fixture fixture;
    protected PatchIdentity pi;

    public DirectChannelServlet(Fixture fixture, PatchIdentity pi)
    {
        this.fixture=fixture;
        this.pi=pi;
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        System.out.println(request.getRequestURL());

        fixture.goHome();
        fixture.setValue(this.pi, DMXUtils.FULL);
        fixture.setValue(PatchIdentity.WHITE, DMXUtils.OFF);


    }

}
