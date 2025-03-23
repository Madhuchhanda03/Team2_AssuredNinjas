package utils;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Properties;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

import static io.cucumber.core.internal.com.fasterxml.jackson.databind.type.LogicalType.Map;

public class CommonUtils {
	public static RequestSpecification request;
	public RequestSpecification requestSpecification() throws IOException {

		if (request == null) {
			PrintStream log = new PrintStream(new FileOutputStream("logging.txt"));
			request = new RequestSpecBuilder().setBaseUri("https://lms-hackthon-feb25-803334c87fbe.herokuapp.com/lms")
					.addFilter(RequestLoggingFilter.logRequestTo(log))
					.addFilter(ResponseLoggingFilter.logResponseTo(log))
					.setContentType(ContentType.JSON)
					.build();
			return request;

		}

//		// Add custom headers to the existing request spec
//		public RequestSpecification setHeaders(Map<String, String> headers) throws IOException {
//			return requestSpecification().headers(headers);
//		}

		return request;
	}

}
