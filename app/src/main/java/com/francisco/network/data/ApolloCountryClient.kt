package com.francisco.network.data

import com.apollographql.apollo3.ApolloClient
import com.francisco.CountriesQuery
import com.francisco.CountryQuery
import com.francisco.network.domain.CountryClient
import com.francisco.network.domain.DetailedCountry
import com.francisco.network.domain.SimpleCountry

class ApolloCountryClient(
    private val apolloClient: ApolloClient
) : CountryClient {
    override suspend fun getCountries(): List<SimpleCountry> {
        return apolloClient
            .query(CountriesQuery())
            .execute()
            .data
            ?.countries
            ?.map { it.toSimpleCountry() }
            ?: emptyList()
    }

    override suspend fun getCountry(code: String): DetailedCountry? {
        return apolloClient
            .query(CountryQuery(code))
            .execute()
            .data
            ?.country
            ?.toDetailedCountry()
    }
}