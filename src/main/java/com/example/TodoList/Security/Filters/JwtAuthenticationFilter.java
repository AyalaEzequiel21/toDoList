//package com.example.TodoList.Security.Filters;
//
//import com.example.TodoList.Model.User;
//import com.example.TodoList.Security.Jwt.JwtUtils;
//import com.fasterxml.jackson.core.exc.StreamReadException;
//import com.fasterxml.jackson.databind.DatabindException;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import jakarta.servlet.FilterChain;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.MediaType;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//
//import java.io.IOException;
//import java.util.HashMap;
//import java.util.Map;
//
//public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
//
//    @Autowired
//    private JwtUtils jwtUtils;
//    @Override
//    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
//        User user = null;
//        String email = "";
//        String password = "";
//
//        try{
//            user = new ObjectMapper().readValue(request.getInputStream(), User.class);
//            email = user.getEmail();
//            password = user.getPassword();
//        } catch (StreamReadException e) {
//            throw new RuntimeException(e);
//        } catch (DatabindException e) {
//            throw new RuntimeException(e);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//
//        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(email, password);
//
//        return getAuthenticationManager().authenticate(authToken);
//    }
//
//    @Override
//    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
//        org.springframework.security.core.userdetails.User userDetail = (org.springframework.security.core.userdetails.User) authResult.getPrincipal();   // CLASE USER DE SPRING SERCURITY
//        String token = jwtUtils.generateAccesToken(userDetail.getUsername());
//
//        response.addHeader("Authorization", token);
//
//        Map<String, Object> httpResponse = new HashMap<>();
//        httpResponse.put("token", token);
//        httpResponse.put("message", "authentication sucessful");
//        httpResponse.put("email:", userDetail.getUsername());
//
//        response.getWriter().write(new ObjectMapper().writeValueAsString(httpResponse));
//        response.setStatus(HttpStatus.OK.value());
//        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
//        response.getWriter().flush();
//
//        super.successfulAuthentication(request, response, chain, authResult);
//    }
//}
