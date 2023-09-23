package com.ltdd14.FarmMarket.handlers;

import javax.servlet.ServletException;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

@Component
public class MyLogoutSuccessHandler implements LogoutSuccessHandler {

    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication a) throws IOException {
        request.getSession().removeAttribute("currentUser");
        try {
            response.sendRedirect(String.format("%s/user/login", request.getContextPath()));
        } catch (IOException ex) {
            Logger.getLogger(MyLogoutSuccessHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
