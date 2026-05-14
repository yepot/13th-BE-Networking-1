package cotato.backend.applicant.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cotato.backend.applicant.dto.request.ApplicantUpdateRequest;
import cotato.backend.applicant.dto.response.ApplicantResponse;
import cotato.backend.applicant.service.ApplicantService;
import cotato.backend.common.dto.DataResponse;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
@RequestMapping("/api/applicants")
public class ApplicantController {

	private final ApplicantService applicantService;

	@GetMapping("/{id}")
	public ResponseEntity<DataResponse<ApplicantResponse>> findById(@PathVariable Long id) {
		return ResponseEntity.ok(DataResponse.from(applicantService.findById(id)));
	}

	@PutMapping("/{id}")
	public ResponseEntity<DataResponse<ApplicantResponse>> update(@PathVariable Long id,
                                                                  @Valid @RequestBody ApplicantUpdateRequest request) {
		return ResponseEntity.ok(DataResponse.from(applicantService.update(id, request)));
	}
}
