package com.bitcoin.history.repository;

import java.util.List;

import com.bitcoin.history.entities.BitcoinPrice;


/**
 * Repository interface for fetching Bitcoin prices, enabling different storage implementations.
 */

public interface PriceRepository {
    List<BitcoinPrice> getPricesBetween(String start, String end);
}
