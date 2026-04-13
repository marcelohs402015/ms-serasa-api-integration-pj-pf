package br.com.ftd.msserasaapi.config;

import br.com.ftd.msserasaapi.model.TokenType;
import br.com.ftd.msserasaapi.service.ConsultaPfStrategy;
import br.com.ftd.msserasaapi.service.ConsultaPjStrategy;
import br.com.ftd.msserasaapi.service.ConsultaStrategy;
import java.util.Map;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConsultaStrategyConfig {

    @Bean
    public Map<TokenType, ConsultaStrategy> consultaStrategies(ConsultaPfStrategy consultaPfStrategy, ConsultaPjStrategy consultaPjStrategy) {
        return Map.of(
                TokenType.PF, consultaPfStrategy,
                TokenType.PJ, consultaPjStrategy
        );
    }
}
