package org.leanpoker.player;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParser;

@WebServlet("/")
public class PlayerServlet extends HttpServlet {

	private final static Gson gson = new GsonBuilder().create();
    private static final String VERSION = "1";

	@Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.getWriter().print("Java player is running");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getParameter("action").equals("bet_request")) {
            String gameState = req.getParameter("game_state");
            
            System.out.println("PlayerServlet.doPost(): " + gameState);
            try {
            	resp.getWriter().print(PlayerBot.betRequest(gameState));
        	} catch (Exception e) {
        		System.out.println("Experimental Playerbot failed:");        		
        		e.printStackTrace(System.out);
            	resp.getWriter().print(PlayerBotSafety.betRequest(gameState));
            }
        }
        if (req.getParameter("action").equals("showdown")) {
            String gameState = req.getParameter("game_state");

            PlayerBot.showdown(new JsonParser().parse(gameState));
        }
        if (req.getParameter("action").equals("version")) {
            resp.getWriter().print(VERSION);
        }
    }
}
