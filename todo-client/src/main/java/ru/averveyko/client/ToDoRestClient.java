package ru.averveyko.client;

import org.apache.http.impl.client.HttpClients;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.averveyko.client.error.ToDoErrorHandler;
import ru.averveyko.domain.ToDo;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;

@Service
public class ToDoRestClient {

    private RestTemplate restTemplate;
    private ToDoRestClientProperties properties;

    public ToDoRestClient(ToDoRestClientProperties properties) {
        this.restTemplate = new RestTemplate();
        this.restTemplate.setErrorHandler(new ToDoErrorHandler());
        // Дефолтный не поддерживает PATCH-запросы (restTemplate.patchForObject)
        this.restTemplate.setRequestFactory(new HttpComponentsClientHttpRequestFactory(HttpClients.createDefault()));
        this.properties = properties;
    }

    public Iterable<ToDo> findAll() throws URISyntaxException {
        RequestEntity<Iterable<ToDo>> requestEntity = new RequestEntity<>(
                HttpMethod.GET, new URI(properties.getUrl() + properties.getBasePath())
        );

        ResponseEntity<Iterable<ToDo>> response = restTemplate.exchange(
                requestEntity, new ParameterizedTypeReference<Iterable<ToDo>>() {
                }
        );

        if (response.getStatusCode() == HttpStatus.OK) {
            return response.getBody();
        }

        return null;
    }

    public ToDo findById(String id) {
        HashMap<String, String> params = new HashMap<>();
        params.put("id", id);
        return restTemplate.getForObject(properties.getUrl() + properties.getBasePath() + "/{id}", ToDo.class, params);
    }

    public ToDo upsert(ToDo toDo) throws URISyntaxException {
        RequestEntity<?> requestEntity = new RequestEntity<>(
                toDo, HttpMethod.POST, new URI(properties.getUrl() + properties.getBasePath())
        );

        ResponseEntity<?> response = restTemplate.exchange(
                requestEntity, new ParameterizedTypeReference<ToDo>() {
                }
        );

        if (response.getStatusCode() == HttpStatus.CREATED) {
            return restTemplate.getForObject(response.getHeaders().getLocation(), ToDo.class);
        }

        return null;
    }

    public ToDo setCompleted(String id) {
        HashMap<String, String> params = new HashMap<>();
        params.put("id", id);

        restTemplate.patchForObject(
                properties.getUrl()
                        + properties.getBasePath()
                        + "/{id}?_method=patch",
                null,
                ResponseEntity.class,
                params);

        return findById(id);
    }

    public void delete(String id) {
        HashMap<String, String> params = new HashMap<>();
        params.put("id", id);
        restTemplate.delete(properties.getUrl() + properties.getBasePath() + "/{id}", params);
    }
}