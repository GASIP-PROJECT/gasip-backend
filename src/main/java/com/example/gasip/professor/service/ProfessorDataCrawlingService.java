package com.example.gasip.professor.service;

import com.example.gasip.professor.dto.ProfessorCrawlRequest;
import com.example.gasip.professor.repository.ProfessorRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProfessorDataCrawlingService {

    private final ProfessorRepository professorRepository;

    public int CrawlingProfessorInfo(ProfessorCrawlRequest professorCrawlRequest) throws IOException {
        for (Short majorNumber : professorCrawlRequest.getMajorNumber()) {
            Document doc = Jsoup.connect(String.format("https://www.gachon.ac.kr/biz/%s/subview.do",majorNumber)).get();
            log.info(doc.title());
        }
        return 1;
    }

}
