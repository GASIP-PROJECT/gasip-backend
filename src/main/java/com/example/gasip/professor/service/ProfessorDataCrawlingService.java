package com.example.gasip.professor.service;

import com.example.gasip.category.model.Category;
import com.example.gasip.category.repository.CategoryRepository;
import com.example.gasip.professor.dto.ProfessorCrawlRequest;
import com.example.gasip.professor.model.Professor;
import com.example.gasip.professor.repository.ProfessorRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.*;
import java.util.regex.Pattern;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProfessorDataCrawlingService {
    private final CategoryRepository categoryRepository;

    private final ProfessorRepository professorRepository;

    private static boolean containsKorean(String str) {
        // 한글의 Unicode 범위: \uAC00-\uD7A3
        return str.matches(".*[\\uAC00-\\uD7A3]+.*");
    }
    @Transactional
    public int CrawlingProfessorInfo(ProfessorCrawlRequest professorCrawlRequest) throws IOException {
        Integer majorCount = 0;
        for (String majorUrl : professorCrawlRequest.getMajorUrl()) {
            Document doc = Jsoup.connect(String.valueOf(majorUrl)).get();
            Elements professorName = doc.select(".name strong");
            Map<String, List> professorInfoMap = new LinkedHashMap<>();
            professorInfoMap.put("sample", new ArrayList<>());
            for (Element element : professorName) {
                professorInfoMap.put(element.text().replace(" 교수",""), new ArrayList<>());
            }

            Elements professorData = doc.select(".person .info dd");
            Integer count = 0;
            Set<String> professorKeySet = professorInfoMap.keySet();
            List<String> professorKeyList = new ArrayList<>(professorKeySet);

            List<String> professorinfoList = new ArrayList<>();
            for (Element professorDatum : professorData) {
                if (professorDatum.text().isEmpty()) {
                    continue;
                }
                if (professorDatum.text().contains("교수")) {
                    // list에 있던 정보 채워넣고 비우기
                    professorInfoMap.replace(professorKeyList.get(count),new ArrayList<>(professorinfoList));
                    professorinfoList.clear();
                    // list에 정보 담기
                    professorinfoList.add(professorDatum.text());
                    count += 1;
                    continue;
                }
                professorinfoList.add(professorDatum.text());
            }
            professorInfoMap.remove("가");
            log.info("professorMap 최종 = {}", professorInfoMap);

            for (String key : professorInfoMap.keySet()) {
                List prfessorinfoList = professorInfoMap.get(key);
                Category category = categoryRepository.findByMajorName(professorCrawlRequest.getMajorName().get(majorCount));
                Professor professor = professorRepository.findByProfNameAndCategory(key,category);
                if (professor == null) {
                    System.err.println("Warning: Professor not found for name: " + key + " and category: " + category);
                    // 교수 정보가 없으면 해당 반복을 건너뜁니다.
                    continue;
                }
                for (Object info : prfessorinfoList) {
                    String information = String.valueOf(info).trim();
                    if (information.contains("교수")) {
                        professor.updatePostion(information);
                        continue;
                    } else if (Pattern.matches("\\d{3}-\\d{3,4}-\\d{4}", information)) {
                        professor.updateTel(information);
                        continue;
                    } else if (isSpecialLocation(information)) {
                        professor.updateLocation(information);
                        continue;
                    } else if (information.length() <= 15 && containsKorean(information)) {
                        professor.updateDegreeMajor(information);
                    }
                }
            }
            majorCount += 1;

        }
        return 1;
    }

    private static boolean isSpecialLocation(String information) {
        return information.endsWith("호") || information.contains("가천관") || information.contains("아름관") || information.contains("새롬관")
            || information.contains("비전타워") || information.contains("공과대학") || information.contains("공학관") || information.contains("중앙도서관")
            || information.contains("산학협력관") || information.contains("IT대학") || information.contains("반도체대학") || information.contains("교육대학원")
            || information.contains("미래관") || information.contains("미래1관") || information.contains("미래 1관") || information.contains("바이오나노연구")
            || information.contains("진리관") || information.contains("암당뇨연구원") || information.contains("AI공학관") || information.contains("AI관")
            || information.contains("IT building") || information.contains("IT융합대학") || information.contains("컨벤션센터") || information.contains("Artificial Intelligence")
            || information.contains("창의관") || information.contains("공학2관") || information.contains("한의대")|| information.contains("의과대학")
            || information.contains("벤처타워")|| information.contains("한의과대학")|| information.contains("미래2관") || information.contains("암당뇨 연구원")
            || information.contains("창조관") || information.contains("예술대학")|| information.contains("예술체육대학") || information.contains("교육대학원")
            || information.contains("예음관");
    }

}
