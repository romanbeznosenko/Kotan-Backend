package com.kotanapp.kotanappapi.modules.article.management;

import com.kotanapp.kotanappapi.modules.article.models.ArticleDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ArticleManager {
    private final ArticleRepository articleRepository;

    public ArticleDAO saveToDatabase(ArticleDAO article) {
        return articleRepository.save(article);
    }

    public Page<ArticleDAO> findAll(Specification<ArticleDAO> specification, Pageable pageable) {
        Pageable sortedPageable = PageRequest.of(
                pageable.getPageNumber(),
                pageable.getPageSize(),
                Sort.by(Sort.Direction.DESC, "createdAt")
        );

        return articleRepository.findAll(specification, sortedPageable);
    }

    public Optional<ArticleDAO> findOne(Specification<ArticleDAO> specification) {
        return articleRepository.findOne(specification);
    }
}