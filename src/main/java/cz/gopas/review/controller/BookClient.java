package cz.gopas.review.controller;

import cz.gopas.review.bean.BookDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "book-client", url = "${custom.book-service-base-url}")
public interface BookClient {

    @GetMapping("/books/{id}")
    public BookDTO getBook(@PathVariable("id") Long id);

}
