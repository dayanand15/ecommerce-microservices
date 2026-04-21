package com.deen.api_gateway.config;

import org.slf4j.MDC;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.UUID;

@Component
public class CorrelationIdFilter implements GlobalFilter, Ordered {
    private static final Logger log=LoggerFactory.getLogger(CorrelationIdFilter.class);
    private static final String TRACE_ID = "traceId";
    private static final String HEADER = "X-Trace-Id";

    public void init(){
      System.out.println("CorrelationIdFilter Loaded");
    }

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

        // get incoming header
        String incomingTraceId = exchange.getRequest().getHeaders().getFirst(HEADER);

        // generate if not present
        String traceId = (incomingTraceId != null)
                ? incomingTraceId
                : UUID.randomUUID().toString();

        // put in MDC
        MDC.put(TRACE_ID, traceId);
        log.info("Incoming request with TraceId: {}", traceId);
        log.info("API GATEWAY TRIGGERED");
        System.out.println("FILTER HIT : " + exchange.getRequest().getURI());


        // forward header to downstream services
        ServerWebExchange mutatedExchange = exchange.mutate()
                .request(r -> r.header(HEADER, traceId))
                .build();

        return chain.filter(mutatedExchange)
                .doFinally(signal -> MDC.remove(TRACE_ID));
    }

    @Override
    public int getOrder() {
        return -1;
    }
}