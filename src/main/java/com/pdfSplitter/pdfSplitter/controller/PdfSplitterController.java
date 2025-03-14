package com.pdfSplitter.pdfSplitter.controller;

import com.pdfSplitter.pdfSplitter.service.PdfSplitterService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("api/pdf")
public class PdfSplitterController {

    private final PdfSplitterService pdfSplitterService;

    public PdfSplitterController(PdfSplitterService pdfSplitterService) {
        this.pdfSplitterService = pdfSplitterService;
    }

    @PostMapping("/split")
    public ResponseEntity<byte[]> splitPdf(@RequestParam("file") MultipartFile file,
                                           @RequestParam("startPage") int startPage,
                                           @RequestParam("endPage") int endPage) {
        try {
            byte[] pdfBytes = pdfSplitterService.splitPdf(file, startPage, endPage);

            HttpHeaders headers = new HttpHeaders();
            headers.add("Content-Disposition", "attachment; filename=split.pdf");

            return ResponseEntity.ok()
                    .headers(headers)
                    .body(pdfBytes);

        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest()
                    .body(("Invalid page range: " + e.getMessage()).getBytes());
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(("Error processing PDF: " + e.getMessage()).getBytes());
        }
    }
}
