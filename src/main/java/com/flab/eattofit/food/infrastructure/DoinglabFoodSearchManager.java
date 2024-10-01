package com.flab.eattofit.food.infrastructure;

import com.flab.eattofit.food.domain.FoodSearchManager;
import com.flab.eattofit.food.infrastructure.dto.FoodSearchResponse;
import com.flab.eattofit.food.infrastructure.dto.PredictFoodSearchResponse;
import com.flab.eattofit.food.infrastructure.dto.doinglab.DoinglabSearchBodyResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RequiredArgsConstructor
@Component
public class DoinglabFoodSearchManager implements FoodSearchManager {

    private static final String FILE_URL = "fileUrl";
    private static final String API_VERSION = "ApiVersion";
    private static final String APP_DEVICE_ID = "AppDeviceId";
    private static final String APP_DEVICE = "AppDevice";
    private static final String APP_PACKAGE = "AppPackage";
    private static final String APP_TOKEN = "AppToken";
    private static final String COMPANY_TOKEN = "CompanyToken";
    private static final String APP_VERSION = "AppVersion";
    private static final String USER_AGENT = "User-Agent";

    private final RestTemplate restTemplate;

    @Value("${doinglab.api-endpoint}")
    private String endpoint;

    @Value("${doinglab.authorization}")
    private String authorization;

    @Value("${doinglab.api-version}")
    private String apiVersion;

    @Value("${doinglab.app-device-id}")
    private String appDeviceId;

    @Value("${doinglab.app-device}")
    private String appDevice;

    @Value("${doinglab.app-package}")
    private String appPackage;

    @Value("${doinglab.app-token}")
    private String appToken;

    @Value("${doinglab.company-token}")
    private String companyToken;

    @Value("${doinglab.app-version}")
    private String appVersion;

    @Value("${doinglab.user-agent}")
    private String userAgent;

    @Override
    public PredictFoodSearchResponse search(final String url) {
        MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
        map.add(FILE_URL, url);
        HttpHeaders headers = generateDoingLabHeaders();
        HttpEntity<MultiValueMap<String, Object>> entity = new HttpEntity<>(map, headers);

        ResponseEntity<DoinglabSearchBodyResponse> response = restTemplate.exchange(
                endpoint,
                HttpMethod.POST,
                entity,
                DoinglabSearchBodyResponse.class
        );

        return convertPredictFoodSearchResponse(response.getBody());
    }

    private HttpHeaders generateDoingLabHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);
        headers.setBasicAuth(authorization);
        headers.set(API_VERSION, apiVersion);
        headers.set(APP_DEVICE_ID, appDeviceId);
        headers.set(APP_DEVICE, appDevice);
        headers.set(APP_PACKAGE, appPackage);
        headers.set(APP_TOKEN, appToken);
        headers.set(COMPANY_TOKEN, companyToken);
        headers.set(APP_VERSION, appVersion);
        headers.set(USER_AGENT, userAgent);

        return headers;
    }

    private PredictFoodSearchResponse convertPredictFoodSearchResponse(final DoinglabSearchBodyResponse response) {
        if (response.body().isEmpty()) {
            return new PredictFoodSearchResponse(List.of());
        }

        List<FoodSearchResponse> predicts = response.body().getFirst()
                .candidates()
                .stream()
                .map(candidate -> new FoodSearchResponse(
                                candidate.fullFoodName(),
                                candidate.totalServingSize(),
                                candidate.unit(),
                                candidate.energy(),
                                candidate.carbohydrate(),
                                candidate.protein(),
                                candidate.fat(),
                                candidate.sodium()
                        )
                )
                .toList();
        return new PredictFoodSearchResponse(predicts);
    }
}
