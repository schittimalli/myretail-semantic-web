package com.myretail.handler;

import com.myretail.exception.ProductNotFoundException;
import graphql.schema.DataFetchingEnvironment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.graphql.execution.ErrorType;
import org.springframework.graphql.execution.DataFetcherExceptionResolverAdapter;
import org.springframework.stereotype.Component;
import graphql.GraphQLError;
import graphql.GraphqlErrorBuilder;

@Component
public class GlobalGraphQLExceptionHandler extends DataFetcherExceptionResolverAdapter {
    private static final Logger logger = LoggerFactory.getLogger(GlobalGraphQLExceptionHandler.class);

    @Override
    protected GraphQLError resolveToSingleError(Throwable ex, DataFetchingEnvironment env) {
        logger.error("GraphQL error in field [{}]: {}", env.getField().getName(), ex.getMessage(), ex);
        if (ex instanceof ProductNotFoundException) {
            return GraphqlErrorBuilder.newError(env)
                    .message(ex.getMessage())
                    .errorType(ErrorType.NOT_FOUND)
                    .build();
        }

        return GraphqlErrorBuilder.newError(env)
                .message("Internal Server Error")
                .errorType(ErrorType.INTERNAL_ERROR)
                .build();
    }
}
