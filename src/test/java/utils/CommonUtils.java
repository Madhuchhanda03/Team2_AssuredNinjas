package utils;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static io.cucumber.core.internal.com.fasterxml.jackson.databind.type.LogicalType.Map;

public class CommonUtils {
	public static RequestSpecification request;
	public static ResponseSpecification resSpec;

	public RequestSpecification requestSpecification() throws IOException {

		if (request == null) {
			PrintStream log = new PrintStream(new FileOutputStream("logging.txt"));
			request = new RequestSpecBuilder().setBaseUri(ConfigReader.readDataFromConfig("baseUrl"))
					.addFilter(RequestLoggingFilter.logRequestTo(log))
					.addFilter(ResponseLoggingFilter.logResponseTo(log))
					.setContentType(ContentType.JSON)
					.setAccept(ContentType.JSON)
					.build();
			return request;

		}


		return request;
	}

	public ResponseSpecification resSpecBuilder() {
		resSpec = new ResponseSpecBuilder()
				.expectContentType(ContentType.JSON)
				.build();
		return resSpec;
	}
}
