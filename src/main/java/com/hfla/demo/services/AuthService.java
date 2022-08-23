package com.hfla.demo.services;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.nylas.HostedAuthentication;
import com.nylas.NylasApplication;
import com.nylas.NylasClient;
import com.nylas.RequestFailedException;
import com.nylas.Scope;

@Component
public class AuthService {
    @Value("${nylas.client.id}")
    private String clientId;  

    @Value("${nylas.client.secret}")
    private String clientSecret;

    public String hostedAuthUrlRedirect() {
      NylasClient client = new NylasClient();
      NylasApplication application = client.application(clientId, clientSecret);
      HostedAuthentication authentication = application.hostedAuthentication();
      String url = authentication.urlBuilder()
          .redirectUri("http://localhost:8081/auth/callback")
          .responseType("code")
          .scopes(Scope.CALENDAR, Scope.CALENDAR_READ_ONLY)
          .state("example_csrf_token")
          .buildUrl();

      return "redirect:" + url;
    }

    public String getAccessToken(String code) throws IOException, RequestFailedException {
      NylasClient client = new NylasClient();
      NylasApplication application = client.application(clientId, clientSecret);
      String accessToken = application.hostedAuthentication().fetchToken(code).getAccessToken();
      return accessToken;
    }
}
