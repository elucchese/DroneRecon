package com.dronerecon.ws;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.net.URL;
import java.util.*;
import java.security.SecureRandom;

/**
 * @author Emily
 */
public class DroneDataService extends HttpServlet{ //servlet in apache


    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("application/json;charset=utf-8");
        response.addHeader("Access-Control-Allow-Origin", "*");

        PrintWriter out = response.getWriter();

        // ##############################
        // 1. Get params passed in.
        // ##############################
        String sArea_id = request.getParameter("area_id");
        int iTilex = Integer.parseInt(request.getParameter("tilex"));
        int iTiley = Integer.parseInt(request.getParameter("tiley"));
        int totalcols = Integer.parseInt(request.getParameter("totalcols"));
        int totalrows = Integer.parseInt(request.getParameter("totalrows"));

        //###############################
        String sRed = request.getParameter("r");
        String sGreen = request.getParameter("g");

        //################################
        //Send data for this tile to the cloud (PortalDBService.java)
        //call PortalDBService api and send this tile's data
        try {
            URL url = new URL("http://127.0.0.1:8080/dronereconportal/PortalDBService?area_id="
                    + sArea_id +"&tilex=" + iTilex + "&tiley="+ iTiley + "&r="+ sRed + "+&g=" + sGreen);
            BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()));

        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("An error happened when calling PortalDBService api: " + ex.toString());
        }

        // ##############################
        // 2. Default value of beginning direction.
        // ##############################
        String sDirection = "right";



        // ##############################
        // 3. Calculate next drone move.
        // ##############################

        //see if on even row
        if(iTiley % 2 == 0){
            //check if on last tile of row
            if(iTilex == totalcols - 1){
                iTiley++;
                sDirection = "left";
            }// else x needs to be increased
            else {
                iTilex++;
                sDirection = "right";
            }

        }//else it is an odd row
        else {
            //check if on far left tile
            if (iTilex == 0) {
                iTiley++;
                sDirection = "right";
            }
            //else x need to be drecreased
            else {
                iTilex--;
                sDirection = "left";
            }

        } //check if off grid because then time to stop
        if(iTiley == totalrows){
            sDirection = "stop";
        }

        // ##############################
        // 4. Format & Return JSON string to caller.
        // ##############################

        String sReturnJson = "{\"area_id\":\"" + sArea_id + "\", \"nextTileX\":\" "+ iTilex +" \", \"nextTileY\":\" "+ iTiley +" \", \"direction\":\" "+sDirection+" \"}";
        out.println(sReturnJson);
    }
}

