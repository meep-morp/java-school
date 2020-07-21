package com.lambdaschool.schools.services;

import com.lambdaschool.schools.exceptions.ResourceNotFoundException;
import com.lambdaschool.schools.models.Instructor;
import com.lambdaschool.schools.models.Slip;
import com.lambdaschool.schools.models.SlipElement;
import com.lambdaschool.schools.repositories.InstructorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service(value = "instructorService")
public class InstructorServiceImpl implements InstructorService {

    @Autowired
    InstructorRepository instructorrepo;

    public RestTemplate restTemplate() {
        RestTemplate restTemplate = new RestTemplate();
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        converter.setSupportedMediaTypes(Collections.singletonList(MediaType.ALL));
        restTemplate.getMessageConverters().add(converter);
        return restTemplate;
    }

    @Override
    public Instructor addAdvice(long id) {
        RestTemplate restTemplate = restTemplate();
        Instructor instructor = instructorrepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(String.format("Instructor %s not found", id)));
        ParameterizedTypeReference<Slip> responseType = new ParameterizedTypeReference<>(){};

       ResponseEntity<Slip> newAdvice = restTemplate.exchange("http://api.adviceslip.com/advice", HttpMethod.GET, null, responseType);
        System.out.println(newAdvice);

        SlipElement slipsElement = newAdvice.getBody().getSlips().get(0);

       instructor.setAdvice(slipElement.getAdvice());
        return instructor;
    }
}

 //@Override
//    public Instructor addAdvice(
//        long id,
//        String searchTerm)
//    {
//        Instructor updatedInstructor = instructorrepos.findById(id)
//            .orElseThrow(() -> new ResourceNotFoundException("Instructor id " + id + " not found!"));
//        /**
//         * Creates the object that is needed to do a client side Rest API call.
//         * We are the client getting data from a remote API.
//         */
//        RestTemplate restTemplate = new RestTemplate();
//        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
//        converter.setSupportedMediaTypes(Collections.singletonList(MediaType.ALL));
//        restTemplate.getMessageConverters().add(converter);
//        // create the url to access the country data
//        String requestURL = "http://api.adviceslip.com/advice/search/" + searchTerm;
//        // create the responseType expected. Notice the slip is the data type we are expecting back from the API!
//        ParameterizedTypeReference<Slips> responseType = new ParameterizedTypeReference<>()
//        {
//        };
//        // create the response entity. do the get and get back information
//        ResponseEntity<Slips> responseEntity = restTemplate.exchange(requestURL,
//            HttpMethod.GET,
//            null,
//            responseType);
//        System.out.println(responseEntity);
//        // now that we have our data, put it into our object!
//        SlipsElement slipsElement = responseEntity.getBody().getSlips().get(0);
//        updatedInstructor.setAdvice(slipsElement.getAdvice());
//        return updatedInstructor;
//    }