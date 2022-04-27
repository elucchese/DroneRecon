package com.dronecon.ws;
        import javax.servlet.*;
        import javax.servlet.http.*;
        import java.io.*;
        import java.util.*;
        import java.security.SecureRandom;

    /**
     *
     * @author Emily
     */
    public class DroneDataService extends HttpServlet{


        public void doGet(HttpServletRequest request,
                          HttpServletResponse response)
                throws ServletException, IOException {

            response.setContentType("application/json;charset=utf-8");
            response.addHeader("Access-Control-Allow-Origin", "*");

            PrintWriter out = response.getWriter();

            // ##############################
            // 1. Get params passed in.

            // Get the following parameters from the request object and put them into strings:
            // area_id
            // tilex
            // tiley
            // totalcols
            // totalrows
            // ##############################
            String area_id = request.getParameter("area_id");
            String tilex = request.getParameter("tilex");
            String tiley = request.getParameter("tiley");
            String totalcols = request.getParameter("totalcols");
            String totalrows = request.getParameter("totalrows");



            // ##############################
            // 2. Default value of beginning direction.

            // Set a string called sDirection to "right".
            // ##############################
            String sDirection = "right";



            // ##############################
            // 3. Calculate next drone move.

            // Determine next tile to move to.
            // Base this on current x and y.
            // Change sDirection if necessary.
            // Drone must serpentine from top left of grid back and forth down.
            // If rows are done, change sDirection to "stop".
            // ##############################



            // ##############################
            // 4. Format & Return JSON string to caller.

        /* Return via out.println() a JSON string like this:
        {"area_id":"[area id from above]", "nextTileX":"[next tile x]", "nextTileY":"[next tile y]", "direction":"[direction string from above]"}
        */
            // ##############################

        }
    }


