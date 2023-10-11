package com.ttu.urlShortner.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.io.BufferedReader;
import java.io.IOException;

@Component
public class CreateApiValidationInterceptor implements HandlerInterceptor {
        @Override
        public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
            String requestBody = getRequestBody(request);
            if (requestBody != null && !isValidRequestBody(requestBody)) {
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                response.getWriter().write("Bad Request: Invalid request body");
                return false;
            }
            request.setAttribute("body",requestBody);
            request.setAttribute("Valid",true);
            return true;
        }

        private boolean isValidRequestBody(String requestBody) {
            if(requestBody.startsWith("http://"))
            {
                return true;
            }
            else
            {
                return false;
            }
        }

        private String getRequestBody(HttpServletRequest request) throws IOException {
            StringBuilder buffer = new StringBuilder();
            BufferedReader reader = request.getReader();
            String line;
            while ((line = reader.readLine()) != null) {
                buffer.append(line);
            }
            return buffer.toString();
        }
}
