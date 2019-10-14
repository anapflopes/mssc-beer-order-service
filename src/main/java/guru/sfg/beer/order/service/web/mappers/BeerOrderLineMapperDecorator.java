package guru.sfg.beer.order.service.web.mappers;

import guru.sfg.beer.order.service.domain.BeerOrderLine;
import guru.sfg.beer.order.service.services.beer.BeerService;
import guru.sfg.beer.order.service.services.beer.model.BeerDto;
import guru.sfg.beer.order.service.web.model.BeerOrderLineDto;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;


public abstract class BeerOrderLineMapperDecorator implements BeerOrderLineMapper {

    private BeerService beerService;
    private BeerOrderLineMapper mapper;

    @Autowired
    public void setBeerService(BeerService beerService) {
        this.beerService = beerService;
    }

    @Autowired
    public void setMapper(BeerOrderLineMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public BeerOrderLineDto beerOrderLineToDto(BeerOrderLine line) {
        BeerOrderLineDto beerOrderLineDto = mapper.beerOrderLineToDto(line);
        Optional<BeerDto> beerDtoOptional = beerService.getBeerByUpc(line.getUpc());
        beerDtoOptional.ifPresent(b -> {
            beerOrderLineDto.setBeerId(b.getId());
            beerOrderLineDto.setBeerName(b.getBeerName());
            beerOrderLineDto.setBeerStyle(b.getBeerStyle());
            beerOrderLineDto.setPrice(b.getPrice());
        });

        return beerOrderLineDto;
    }

}


