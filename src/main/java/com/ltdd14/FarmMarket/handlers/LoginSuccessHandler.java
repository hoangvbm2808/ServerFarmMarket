package com.ltdd14.FarmMarket.handlers;

import com.ltdd14.FarmMarket.model.user.User;
import com.ltdd14.FarmMarket.model.user.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@Component
public class LoginSuccessHandler implements AuthenticationSuccessHandler {
    @Autowired
    private UserDao userDao;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication a) throws IOException {
        User u= this.userDao.getUser(a.getName());
        request.getSession().setAttribute("currentUser", u);
        if (u.getRole().equals("ADMIN")) {
            response.sendRedirect(String.format("%s/admin/index", request.getContextPath()));
        }
        else {
            response.sendRedirect(request.getContextPath());
        }
    }

}