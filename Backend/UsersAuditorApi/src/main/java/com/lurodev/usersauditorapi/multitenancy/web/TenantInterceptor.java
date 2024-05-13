package com.lurodev.usersauditorapi.multitenancy.web;

import com.lurodev.usersauditorapi.multitenancy.context.TenantContext;
import com.lurodev.usersauditorapi.multitenancy.resolver.HttpHeaderTenantResolver;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Component
@RequiredArgsConstructor
public class TenantInterceptor implements HandlerInterceptor {
    private final HttpHeaderTenantResolver tenantResolver;
    @Value("${multitenancy.http.header-name}")
    String tenantIdHeaderName;

    @Override
    public boolean preHandle(@NonNull  HttpServletRequest request,
                             @NonNull HttpServletResponse response,
                             @NonNull Object handler
    )throws Exception {
        String tenantId = tenantResolver.resolveTenantIdentifier(request);

        if(tenantId == null){
            response.setStatus(HttpStatus.BAD_REQUEST.value());
            response.getWriter().write(tenantIdHeaderName + " value is missing from header");
            return false;
        }

        TenantContext.setTenantId(tenantId);
        return true;
    }

    @Override
    public void postHandle(@NonNull HttpServletRequest request,
                           @NonNull HttpServletResponse response,
                           @NonNull Object handler,
                           @Nullable ModelAndView modelAndView
    ) throws Exception {
        clear();
    }

    @Override
    public void afterCompletion(@NonNull HttpServletRequest request,
                                @NonNull HttpServletResponse response,
                                @NonNull Object handler,
                                @Nullable Exception ex
    ) throws Exception {
        clear();
    }

    private void clear(){
        TenantContext.clear();
    }
}
