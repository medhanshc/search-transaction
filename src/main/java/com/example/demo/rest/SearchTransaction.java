package com.example.demo.rest;

import com.example.demo.dto.ApiError;
import com.example.demo.dto.TransactionSearchRequest;
import com.example.demo.dto.TransactionSearchResponse;
import com.example.demo.service.TransactionSearchService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/demo")
@AllArgsConstructor
public class SearchTransaction {

    private final TransactionSearchService transactionSearchService;

    @GetMapping("/transaction/search")
    public List<TransactionSearchResponse> searchTransaction(@Valid @RequestBody TransactionSearchRequest transactionSearchRequest) {
        List<TransactionSearchResponse> response = transactionSearchService.getTransactionDetails(transactionSearchRequest);
        if (response.isEmpty()) {
            return handleError();
        }
        return response;
    }

    private List<TransactionSearchResponse> handleError() {
        ApiError apiError = new ApiError();
        apiError.setErrorCode(400);
        apiError.setErrorMessage("Result not found");
        TransactionSearchResponse transactionSearchResponse = new TransactionSearchResponse();
        transactionSearchResponse.setApiError(apiError);
        return List.of(transactionSearchResponse);

    }


}
