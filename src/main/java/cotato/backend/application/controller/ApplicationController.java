package cotato.backend.application.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cotato.backend.application.dto.request.ApplicationCreateRequest;
import cotato.backend.application.dto.request.ApplicationListRequest;
import cotato.backend.application.dto.response.ApplicationCreateResponse;
import cotato.backend.application.dto.response.ApplicationDetailResponse;
import cotato.backend.application.dto.response.ApplicationListResponse;
import cotato.backend.application.service.ApplicationService;
import cotato.backend.common.dto.DataResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

@Validated
@RestController
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
@RequestMapping("/api/applications")
public class ApplicationController {

	private final ApplicationService applicationService;

	@PostMapping
	public ResponseEntity<DataResponse<ApplicationCreateResponse>> create(@Valid @RequestBody ApplicationCreateRequest request) {
		return ResponseEntity.status(HttpStatus.CREATED).body(DataResponse.created(applicationService.create(request)));
	}

	@GetMapping("/{id}")
	public ResponseEntity<DataResponse<ApplicationDetailResponse>> findById(@Parameter(description = "지원서 ID", example = "1")
                                                                                @PathVariable Long id) {
		return ResponseEntity.ok(DataResponse.from(applicationService.findById(id)));
	}

	@GetMapping
	public ResponseEntity<DataResponse<ApplicationListResponse>> findAll(@Valid @ModelAttribute ApplicationListRequest request) {
		return ResponseEntity.ok(DataResponse.from(applicationService.findAll(request)));
	}
}
