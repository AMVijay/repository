# Spring Boot Web App with Interceptor
POC to understand Spring Interceptors.

# Technical Stack
* Java 1.8
* Spring Boot 2.0.5

# Notes
* Spring Interceptor are not same as HttpFilter.
* Spring Interceptor has 3 lifecycle method preHandle, postHandle, afterCompletion. In all these method, we can read HttpResponse generated by Controller 