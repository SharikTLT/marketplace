package ru.shariktlt.marketplace.services.mappers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.shariktlt.marketplace.controller.dto.CartResponse;
import ru.shariktlt.marketplace.controller.dto.ClientCartItemAdapter;
import ru.shariktlt.marketplace.model.CartItem;
import ru.shariktlt.marketplace.services.PriceFormatterService;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;


/**
 * Сервис для преобразования внутренних моделей в представление для передачи на фронт.
 *
 * Добавляет форматирование и суммирование элементов в корзине.
 */
@Service
public class ClientCartMapper {

    @Autowired
    private PriceFormatterService priceFormatter;

    public CartResponse prepareResponse(Iterable<CartItem> items) {

        List<ClientCartItemAdapter> clientCartItems = StreamSupport.stream(items.spliterator(), false)
                .map(this::mapItem)
                .collect(Collectors.toList());

        BigDecimal sum = clientCartItems.stream().map(ClientCartItemAdapter::getSum).reduce(BigDecimal.ZERO, BigDecimal::add);

        return new CartResponse(clientCartItems, sum, priceFormatter.format(sum));

    }

    private ClientCartItemAdapter mapItem(CartItem cartItem) {
        BigDecimal sum = BigDecimal.valueOf(cartItem.getCount()).multiply(cartItem.getProduct().getBasePrice());
        return new ClientCartItemAdapter(cartItem, sum, priceFormatter.format(sum));
    }
}
