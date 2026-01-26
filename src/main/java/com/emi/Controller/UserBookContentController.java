package com.emi.Controller;

import java.security.Principal;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.emi.dto.requestDto.PurchaseMultipleContentRequest;
import com.emi.dto.responseDto.ResponseUserBookContentDto;
import com.emi.service.UserBookContentService;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/userBookContent")
@RequiredArgsConstructor
public class UserBookContentController {

	private final UserBookContentService contentService;
	
	@PostMapping("/buy/{id}")
	@Operation(
			summary="Buy single content",
			description="bookContents end point"
			)
	public List<ResponseUserBookContentDto> purchaseSingle(Principal principal , @PathVariable Long id) {
		return contentService.purchaseSingleContent(principal.getName(), id );
	}
	
	
	@GetMapping("/getinfo")
	@Operation(
			summary="get all purchased content",
			description="bookContents end point"
			)
	public List<ResponseUserBookContentDto> getAllContent(Principal principal){
		return contentService.getPurchasedContentsByUser(principal.getName());
	}
	
	@GetMapping("/getinfo/{contentId}")
	@Operation(
			summary="get single purchased content",
			description="bookContents end point"
			)
	public ResponseUserBookContentDto getSingle(Principal principal ,@PathVariable Long contentId) {
		return contentService.getUserBookContent(principal.getName(), contentId);
	}
	
	@PostMapping("/buy")
	@Operation(
			summary="Buy multiple content",
			description="bookContents end point"
			)
	public List<ResponseUserBookContentDto> buyMultiple(Principal principal ,@RequestBody @Valid PurchaseMultipleContentRequest request){
		return contentService.purchaseMultipleContent(principal.getName(), request.getContentIds());
	}
	
}
