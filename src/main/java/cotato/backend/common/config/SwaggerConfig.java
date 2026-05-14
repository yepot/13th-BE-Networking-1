package cotato.backend.common.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class SwaggerConfig {

	@Value("${swagger.title:지원자 서류 API}")
	private String swaggerTitle;

	@Value("${swagger.description:지원자 서류 등록, 조회, 목록 필터링 API 문서}")
	private String swaggerDescription;

	@Bean
	public OpenAPI openAPI() {
		return new OpenAPI()
			.info(new Info()
				.title(swaggerTitle)
				.description(swaggerDescription));
	}
}
