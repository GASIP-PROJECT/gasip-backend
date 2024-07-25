package com.example.gasip.professor.service;

import com.example.gasip.professor.dto.ProfessorCrawlRequest;
import com.example.gasip.professor.repository.ProfessorRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProfessorDataCrawlingService {

    private final ProfessorRepository professorRepository;

    public int CrawlingProfessorInfo(ProfessorCrawlRequest professorCrawlRequest) throws IOException {
        for (Short majorNumber : professorCrawlRequest.getMajorNumber()) {
            Document doc = Jsoup.connect(String.format("https://www.gachon.ac.kr/biz/%s/subview.do",majorNumber)).get();
            Elements professorName = doc.select(".name strong");
            Map<String, List> professorInfoMap = new HashMap<>();
            for (Element element : professorName) {
                professorInfoMap.put(element.text().replace(" 교수",""), new ArrayList<>());
            }
            professorInfoMap.put("가", new ArrayList<>());
            System.out.println(professorInfoMap);
            Elements professorData = doc.select(".person .info dd");
            Integer count = 0;
            Set<String> professorKeySet = professorInfoMap.keySet();
            List<String> professorKeyList = new ArrayList<>(professorKeySet);

            for (Element professorDatum : professorData) {
                List<Object> professorinfoList = new ArrayList<>();
                System.out.println(professorDatum.text());
                if (professorDatum.text().contains("교수")) {
                    // list에 있던 정보 채워넣고 비우기
                    professorInfoMap.put(String.valueOf(professorKeyList.indexOf(count)),professorinfoList);
                    System.out.println(professorinfoList);
                    professorinfoList.clear();
                    // list에 정보 담기
                    professorinfoList.add(professorDatum.text());
                    count += 1;
                    continue;
                }
                professorinfoList.add(professorDatum.text());
            }
            count = 0;
            System.out.println(professorInfoMap);


            Elements professorEduArea = doc.select(".person .info .eduArea");

        }
        return 1;
    }

}
