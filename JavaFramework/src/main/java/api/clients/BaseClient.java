package api.clients;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.Map;

public abstract class BaseClient {

    protected final String baseUrl;

    protected BaseClient(String baseUrl) {
        this.baseUrl = baseUrl;
        RestAssured.baseURI = baseUrl;
    }

    protected RequestSpecification createRequest(String endpoint, Map<String, String> headers) {
        RequestSpecification request = RestAssured
                .given()
                .contentType("application/json")
                .relaxedHTTPSValidation() //Used for test environments, if you need certificate validation comment this
                .basePath(endpoint);

        if (headers != null) {
            request.headers(headers);
        }
        return request;
    }

    protected <T> RequestSpecification addJsonBody(RequestSpecification request, T body) {
        return request.body(body);
    }

    protected Response executeRequest(RequestSpecification request, Method method) {
        return request.request(method);
    }

    protected void ensureSuccessStatusCode(Response response, int expectedStatusCode) {
        if (response.statusCode() != expectedStatusCode) {
            throw new RuntimeException(
                    String.format("Expected status code %d but got %d. Response: %s",
                            expectedStatusCode, response.statusCode(), response.getBody().asPrettyString())
            );
        }
    }

    protected Response executeRequestWithLogs(RequestSpecification request, Method method, int statusCode) {
        request.then().statusCode(statusCode).log().all();

        Response response = request.request(method);

        System.out.println("Response Status: " + response.statusCode());
        response.then().log().all();

        return response;
    }

    protected RequestSpecification addQueryParams(RequestSpecification request, Map<String, String> queryParams) {
        if (queryParams != null && !queryParams.isEmpty()) {
            request.queryParams(queryParams);
        }
        return request;
    }

    protected <T> T mapResponse(Response response, Class<T> responseType) {
        return response.as(responseType);
    }
}
