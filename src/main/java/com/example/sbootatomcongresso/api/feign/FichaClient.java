package com.example.sbootatomcongresso.api.feign;

import com.example.sbootatomcongresso.domain.model.Ficha;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@FeignClient("atom-ficha")
public interface FichaClient {
    @RequestMapping(method = RequestMethod.POST, value = "/api/fichas", consumes = "application/json")
    ResponseEntity<List<Ficha>> credenciar(@RequestBody Ficha fc);
}
