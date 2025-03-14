package com.pdfSplitter.pdfSplitter.service;

import org.apache.pdfbox.multipdf.Splitter;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
@Service
public class PdfSplitterService {
    public byte[] splitPdf(MultipartFile file, int startPage, int endPage) throws IOException {
        try (PDDocument document = PDDocument.load(file.getInputStream());
             ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {

            Splitter splitter = new Splitter();
            List<PDDocument> pages = splitter.split(document);

            if (startPage < 1 || endPage > pages.size() || startPage > endPage) {
                throw new IllegalArgumentException("Invalid page range.");
            }

            // Create new PDF with the selected pages
            try (PDDocument newPdf = new PDDocument()) {
                for (int i = startPage - 1; i < endPage; i++) {
                    newPdf.addPage(pages.get(i).getPage(0));
                }
                newPdf.save(outputStream);
            }

            return outputStream.toByteArray();
        }
    }
}
