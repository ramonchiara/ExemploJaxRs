package br.pro.ramon.dcs.resources;

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

@Path("/testes")
public class TestesResource {

    @GET
    @Produces("text/plain")
    public String getHello() {
        return "Olá, mundo!";
    }

    @GET
    @Path("/ola")
    @Produces(MediaType.TEXT_PLAIN)
    public String getHello(@QueryParam("nome") String nome) {
        return "Olá, " + nome + "!";
    }

    @GET
    @Path("/ola-errado.html")
    @Produces("text/plain")
    public String getHelloHtmlErrado(@QueryParam("nome") String nome) {
        return "<p>Olá, " + nome + "!</p>";
    }

    @GET
    @Path("/ola.html")
    @Produces("text/html")
    public String getHelloHtml(@QueryParam("nome") String nome) {
        return "<p>Olá, " + nome + "!</p>";
    }

    @GET
    @Path("/soma")
    public double getSoma(@QueryParam("n1") double n1, @QueryParam("n2") double n2) {
        return n1 + n2;
    }

    @GET
    @Path("/soma.trata-erro")
    public Response getSoma(@QueryParam("n1") String n1, @QueryParam("n2") String n2) {
        try {
            double a = Double.parseDouble(n1);
            double b = Double.parseDouble(n2);

            double resultado = a + b;

            // return Response.status(Status.OK).entity(resultado).build();
            return Response.ok(resultado).build();
        } catch (NumberFormatException ex) {
            return Response.status(Status.BAD_REQUEST).entity("Entre com números válidos!").build();
        }
    }

    @GET
    @Path("/soma/{a}/{b}")
    public double getSomaViaPath(@PathParam("a") double n1, @PathParam("b") double n2) {
        return n1 + n2;
    }

    @POST
    @Path("/soma.post")
    public double getSomaViaPost(@FormParam("a") double n1, @FormParam("b") double n2) {
        return n1 + n2;
    }

    @GET
    @Path("/ola.xml/{nome}")
    @Produces(MediaType.APPLICATION_XML)
    public String getHelloXml(@PathParam("nome") String nome) {
        return "<ola><nome>" + nome + "</nome><saudacao>Olá</saudacao></ola>";
    }

    @GET
    @Path("/ola.json/{nome}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getHelloJson(@PathParam("nome") String nome) {
        return "{ \"nome\": \"" + nome + "\", \"saudacao\": \"Olá\" }";
    }

    @GET
    @Path("/get-post")
    public String mesmoPathGet() {
        return "foi get...";
    }

    @POST
    @Path("/get-post")
    public String mesmoPathPost() {
        return "foi post...";
    }

}
