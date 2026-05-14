package cotato.backend.executive.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cotato.backend.common.dto.DataResponse;
import cotato.backend.executive.dto.request.ExecutiveUpdateRequest;
import cotato.backend.executive.dto.response.ExecutiveResponse;
import cotato.backend.executive.service.ExecutiveService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
@RequestMapping("/api/executives")
public class ExecutiveController {

	private final ExecutiveService executiveService;

	@GetMapping("/{id}")
	public ResponseEntity<DataResponse<ExecutiveResponse>> findById(@PathVariable Long id) {
		return ResponseEntity.ok(DataResponse.from(executiveService.findById(id)));
	}

	@PutMapping("/{id}")
	public ResponseEntity<DataResponse<ExecutiveResponse>> update(@PathVariable Long id,
                                                                  @Valid @RequestBody ExecutiveUpdateRequest request) {
		return ResponseEntity.ok(DataResponse.from(executiveService.update(id, request)));
	}
}
