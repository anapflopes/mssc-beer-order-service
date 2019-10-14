package guru.sfg.beer.order.service.services.beer;

import guru.sfg.beer.order.service.services.beer.model.BeerDto;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;
import java.util.UUID;

@ConfigurationProperties(prefix = "sfg.beer.order", ignoreUnknownFields = false)
@Component
public class BeerServiceRestTemplateImpl implements BeerService {

    private final String BEER_BY_UPC_PATH = "/api/v1/beerUpc/{upc}";
    private final String BEER_BY_ID_PATH = "/api/v1/beer/{beerId}";
    private final RestTemplate restTemplate;
    private String beerServiceHost;

    public BeerServiceRestTemplateImpl(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    public void setBeerServiceHost(String beerServiceHost) {
        this.beerServiceHost = beerServiceHost;
    }

    @Override
    public Optional<BeerDto> getBeerByUpc(String upc) {
        return Optional.of(restTemplate.getForObject(beerServiceHost + BEER_BY_UPC_PATH, BeerDto.class, upc));
    }

    @Override
    public Optional<BeerDto> getBeerById(UUID id) {
        return Optional.of(restTemplate.getForObject(beerServiceHost + BEER_BY_UPC_PATH, BeerDto.class, id.toString()));
    }
}
