package com.kotanapp.kotanappapi.utils.fileParser;

import com.kotanapp.kotanappapi.modules.club.models.Club;
import com.kotanapp.kotanappapi.modules.club.services.ClubBuilders;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;


@Service
@Slf4j
@RequiredArgsConstructor
public class FileParseService {
    public List<Club> extractClubs(MultipartFile file) throws IOException {
        String filename = file.getOriginalFilename();
        if (filename == null || !filename.toLowerCase().endsWith(".csv")) {
            throw new FileParserUnsupportedFileExtensionException();
        }

        List<Club> clubs = new ArrayList<>();
        boolean firstLine = true;

        try (BufferedReader br = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
            String line;
            while ((line = br.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty()) continue;

                if (firstLine || line.toLowerCase().contains("name")) {
                    firstLine = false;
                    continue;
                }
                firstLine = false;

                String[] parts = line.split(",");

                if (parts.length != 5) {
                    continue;
                }

                Club club = ClubBuilders.buildFromCSV(parts);
                clubs.add(club);
            }
        }

        return clubs;
    }
}
