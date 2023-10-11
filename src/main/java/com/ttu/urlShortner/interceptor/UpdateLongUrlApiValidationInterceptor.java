package com.ttu.urlShortner.interceptor;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ttu.urlShortner.Dto.UpdateLongUrlDto;
import com.ttu.urlShortner.Dto.UpdateNewExpiryDto;
import com.ttu.urlShortner.Exception.JsonParsingException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.io.BufferedReader;
import java.io.IOException;

@Component
public class UpdateLongUrlApiValidationInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String requestBody = getRequestBody(request);
        ObjectMapper objectMapper = new ObjectMapper();
        UpdateLongUrlDto longUrlDto = objectMapper.readValue(requestBody, UpdateLongUrlDto.class);
        if (requestBody != null && !isValidRequestBody(requestBody)) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write("Bad Request: Invalid request body");
            return false;
        }
        request.setAttribute("body",longUrlDto);
        request.setAttribute("Valid",true);
        return true;
    }

    private boolean isValidRequestBody(String requestBody) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            UpdateLongUrlDto longUrlDto = objectMapper.readValue(requestBody, UpdateLongUrlDto.class);

            if (longUrlDto.getShortUrl() != null && longUrlDto.getNewDestination() != null) {
                return true;
            }
        } catch (Exception e) {
            throw new JsonParsingException("Unable to parse request body!!!");
        }
        return false;
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
