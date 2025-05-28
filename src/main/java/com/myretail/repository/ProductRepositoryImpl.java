package com.myretail.repository;

import com.myretail.exception.SPARQLQueryException;
import com.myretail.mapper.ProductMapper;
import com.myretail.model.Product;
import org.eclipse.rdf4j.query.QueryLanguage;
import org.eclipse.rdf4j.query.TupleQuery;
import org.eclipse.rdf4j.query.TupleQueryResult;
import org.eclipse.rdf4j.repository.Repository;
import org.eclipse.rdf4j.repository.RepositoryConnection;
import org.eclipse.rdf4j.repository.sparql.SPARQLRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductRepositoryImpl implements ProductRepository {

    private final Repository repository;
    private static final String NS = "http://bestbuy.com/ontology#";


    private final ProductMapper productMapper;
    private static final String ENDPOINT = "http://localhost:3030/myretail/sparql";

    private static final Logger logger = LoggerFactory.getLogger(ProductRepositoryImpl.class);

    public ProductRepositoryImpl(ProductMapper productMapper) {
        this.productMapper = productMapper;

        this.repository = new SPARQLRepository(ENDPOINT);
        this.repository.init();

    }



    @Override
    public List<Product> findAll() {

        String queryStr = String.format("""
            PREFIX : <%s>
            SELECT ?p ?id ?name ?brand ?category ?imageURL
            WHERE {
              ?p a :Product ;
                 :productId ?id ;
                 :productName ?name .
              OPTIONAL { ?p :hasBrand ?brand . }
              OPTIONAL { ?p :hasCategory ?category . }
              OPTIONAL {
                ?p :hasImage ?img .
                ?img :imageURL ?imageURL .
              }
            }
        """, NS);

        List<Product> products = new ArrayList<>();

        try (RepositoryConnection conn = repository.getConnection()) {
            TupleQuery query = conn.prepareTupleQuery(QueryLanguage.SPARQL, queryStr);
            try (TupleQueryResult result = query.evaluate()) {
                while (result.hasNext()) {
                    products.add(productMapper.toProduct(result.next()));
                }
            }
        } catch (Exception e) {
            logger.error("SPARQL query failed");

            throw new SPARQLQueryException("SPARQL query failed");
        }

        return products;

    }

    @Override
    public Optional<Product> findById(String id) {
        String query = String.format("""
            PREFIX : <http://bestbuy.com/ontology#>
            SELECT ?id ?name ?brand ?category ?imageURL WHERE {
              ?p a :Product ; 
              :productId \"%s\" ; 
              :productName ?name .
              BIND(\"%s\" as ?id)
              OPTIONAL { ?p :hasBrand ?brand }
              OPTIONAL { ?p :hasCategory ?category }
              OPTIONAL { ?p :hasImage ?img . ?img :imageURL ?imageURL }
            }
        """, id, id);

        try (RepositoryConnection conn = repository.getConnection()) {
            TupleQuery q = conn.prepareTupleQuery(QueryLanguage.SPARQL, query);
            try (TupleQueryResult result = q.evaluate()) {
                return result.hasNext() ? Optional.of(productMapper.toProduct(result.next())) : Optional.empty();
            }
        }catch (Exception e) {
            logger.error("SPARQL query failed");
            throw new SPARQLQueryException("SPARQL query failed");
        }
    }
}
