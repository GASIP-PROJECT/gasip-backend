package com.example.gasip.board.service;

import com.example.gasip.board.dto.BoardPushRequest;
import com.example.gasip.board.dto.FcmMessageRequest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.gson.JsonParseException;
import lombok.RequiredArgsConstructor;
import okhttp3.*;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.List;

@RequiredArgsConstructor
@Service
public class FirebasePushService {
    private final String API_URL = "https://fcm.googleapis.com/v1/projects/gasip-4eb42/messages:send";  // 요청을 보낼 엔드포인트
    private final ObjectMapper objectMapper;

    public int sendMessageTo(BoardPushRequest boardPushRequest) throws IOException {
        String message = makeMessage(boardPushRequest.getTargetToken(), boardPushRequest.getTitle(), boardPushRequest.getBody());

        OkHttpClient client = new OkHttpClient();
        RequestBody requestBody = RequestBody.create(message,
            MediaType.get("application/json; charset=utf-8"));
        Request request = new Request.Builder()
            .url(API_URL)
            .post(requestBody)
            .addHeader(HttpHeaders.AUTHORIZATION, "Bearer " + getAccessToken())
            .addHeader(HttpHeaders.CONTENT_TYPE, "application/json; UTF-8")
            .build();

        Response response = client.newCall(request).execute();

        System.out.println(response.body().string());
        return response.code() == HttpURLConnection.HTTP_OK ? 1 : 0;
    }

    private String makeMessage(String targetToken, String title, String body) throws JsonParseException, JsonProcessingException {
        FcmMessageRequest fcmMessageRequest = FcmMessageRequest.builder()
            .message(FcmMessageRequest.Message.builder()
                .token(targetToken)
                .notification(FcmMessageRequest.Notification.builder()
                    .title(title)
                    .body(body)
                    .image(null)
                    .build()
                ).build()).validateOnly(false).build();

        return objectMapper.writeValueAsString(fcmMessageRequest);
    }

    private String getAccessToken() throws IOException {
        String firebaseConfigPath = "firebase/gasip-4eb42-firebase-adminsdk-3v4pj-6851019caa.json";

        GoogleCredentials googleCredentials = GoogleCredentials
            .fromStream(new ClassPathResource(firebaseConfigPath).getInputStream())
            .createScoped(List.of("https://www.googleapis.com/auth/cloud-platform"));           //엑세스 토큰 받아오기

        googleCredentials.refreshIfExpired();
        return googleCredentials.getAccessToken().getTokenValue();
    }
}
