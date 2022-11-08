package com.javaclimb.infrastructure.apigateway.filter;

import com.google.gson.JsonObject;
import com.javaclimb.service.base.utils.JwtUtils;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * @Author yaCoding
 * @create 2022-11-08 下午 4:02
 */

@Component
public class AuthGlobalFilter implements GlobalFilter, Ordered {

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        //获取对象
        ServerHttpRequest request = exchange.getRequest();
        String path = request.getURI().getPath();

        //匹配路由
        AntPathMatcher antPathMatcher = new AntPathMatcher();
        // 判断路径中如果含有 /api/**/auth/**,则需要鉴权
        if (antPathMatcher.match("/api/**/auth/**", path)) {
            //获取 token
            List<String> tokenList = request.getHeaders().get("token");
            //没有 token
            if (tokenList == null) {
                ServerHttpResponse response = exchange.getResponse();
                // 鉴权失败
                return this.out(response);
            }
            // token 检验失败
            boolean isCheck = JwtUtils.checkJwtToken(tokenList.get(0));
            if (!isCheck) {
                ServerHttpResponse response = exchange.getResponse();
                // 鉴权失败
                return this.out(response);
            }
        }
        //放行 :使用过滤器链,将请求继续向下传递
        return chain.filter(exchange);

    }


    /**
     * 使用 webflux 输入请求信息
     */
    private Mono<Void> out(ServerHttpResponse response) {
        JsonObject message = new JsonObject();
        message.addProperty("success", false);
        message.addProperty("code", 28004);
        message.addProperty("data", "");
        message.addProperty("message", "鉴权失败");
        byte[] bytes = message.toString().getBytes(StandardCharsets.UTF_8);
        DataBuffer buffer = response.bufferFactory().wrap(bytes);
        //指定编码，否则在浏览器中会中文乱码
        response.getHeaders().add("Content-Type", "application/json;charset=UTF-8");
        //输出http响应
        return response.writeWith(Mono.just(buffer));
    }

    /**
     * 定义当前过滤器的优先级
     *  - 值越小, 优先级越高
     */
    @Override
    public int getOrder() {
        return 0;
    }

}
